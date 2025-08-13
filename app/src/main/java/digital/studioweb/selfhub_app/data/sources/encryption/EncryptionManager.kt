package digital.studioweb.selfhub_app.data.sources.encryption

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import digital.studioweb.selfhub_app.data.utils.LocalConstants.ANDROID_KEY_STORE
import digital.studioweb.selfhub_app.data.utils.LocalConstants.KEYSTORE_TRANSFORMATION
import digital.studioweb.selfhub_app.data.utils.LocalConstants.SELF_HUB_SECURE_KEY
import org.jetbrains.annotations.VisibleForTesting
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

class EncryptionManager : EncryptionManagerInterface {
    private val ivLength = 12
    private val tagLength = 16

    @get:VisibleForTesting
    val keyStore: KeyStore by lazy {
        KeyStore.getInstance(ANDROID_KEY_STORE).apply {
            load(null)
        }
    }

    init {
        if (generateKeyOnStart) {
            generateKey()
        }
    }

    @VisibleForTesting
    fun generateKey() {
        if (isKeyGenerated()) return
        val keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES,
            ANDROID_KEY_STORE
        )
        keyGenerator.init(getBuilder())
        keyGenerator.generateKey()
    }

    @VisibleForTesting
    fun isKeyGenerated(): Boolean = keyStore.containsAlias(SELF_HUB_SECURE_KEY)

    @VisibleForTesting
    fun getSecretKey(): SecretKey = keyStore.getKey(SELF_HUB_SECURE_KEY, null) as SecretKey

    override fun encrypt(data: String): String {
        val cipher = Cipher.getInstance(KEYSTORE_TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey())

        val iv = cipher.iv
        val encryptedData = cipher.doFinal(data.toByteArray(Charsets.UTF_8))

        val combined = iv + encryptedData
        return Base64.encodeToString(combined, Base64.DEFAULT)
    }

    override fun decrypt(data: String): String? = try {
        val combined = Base64.decode(data, Base64.DEFAULT)

        val iv = combined.sliceArray(0 until ivLength)
        val encryptedData = combined.sliceArray(ivLength until combined.size)

        val cipher = Cipher.getInstance(KEYSTORE_TRANSFORMATION)
        val spec = GCMParameterSpec(tagLength * 8, iv)
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), spec)

        val decryptedData = cipher.doFinal(encryptedData)
        String(decryptedData, Charsets.UTF_8)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    @VisibleForTesting
    fun getBuilder(): KeyGenParameterSpec = KeyGenParameterSpec.Builder(
        SELF_HUB_SECURE_KEY,
        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
    ).setBlockModes(KeyProperties.BLOCK_MODE_GCM)
        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
        .setRandomizedEncryptionRequired(true).build()

    companion object {
        internal var generateKeyOnStart = true
    }
}

interface EncryptionManagerInterface {
    fun encrypt(data: String): String

    fun decrypt(data: String): String?
}
