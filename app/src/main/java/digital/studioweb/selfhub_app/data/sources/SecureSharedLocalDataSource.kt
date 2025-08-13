package digital.studioweb.selfhub_app.data.sources

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import digital.studioweb.selfhub_app.data.sources.encryption.EncryptionManagerInterface
import com.google.gson.GsonBuilder
import kotlin.reflect.KClass

class SecureSharedLocalDataSource(
    private val context: Context,
    private val encryptionManager: EncryptionManagerInterface
) : SecureSharedLocalDataSourceInterface {
    private val preferencesName = "CeleroSecurePrefs"

    private val masterKey =
        MasterKey
            .Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

    private val gson =
        GsonBuilder()
            .create()

    private val sharedPreferences: SharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            context,
            preferencesName,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun <Value> store(
        key: String,
        value: Value
    ) {
        val encryptedValue = encryptionManager.encrypt(gson.toJson(value))
        sharedPreferences.edit { putString(key, encryptedValue) }
    }

    override fun <Value : Any> retrieve(
        key: String,
        clazz: KClass<Value>
    ): Value? =
        sharedPreferences.getString(key, "")?.let {
            try {
                val decryptedValue = encryptionManager.decrypt(data = it)
                gson.fromJson(decryptedValue, clazz.java)
            } catch (e: Exception) {
                null
            }
        }

    override fun remove(key: String) {
        sharedPreferences.edit { remove(key) }
    }

    override fun contains(key: String): Boolean = sharedPreferences.contains(key)
}

interface SecureSharedLocalDataSourceInterface {
    fun <Value> store(
        key: String,
        value: Value
    )

    fun <Value : Any> retrieve(
        key: String,
        clazz: KClass<Value>
    ): Value?

    fun remove(key: String)

    fun contains(key: String): Boolean
}

inline fun <reified Value : Any> SecureSharedLocalDataSourceInterface.retrieve(
    key: String
): Value? =
    retrieve(key, Value::class)
