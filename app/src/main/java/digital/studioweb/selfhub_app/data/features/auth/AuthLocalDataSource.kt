package digital.studioweb.selfhub_app.data.features.auth

import digital.studioweb.selfhub_app.data.sources.SecureSharedLocalDataSourceInterface
import digital.studioweb.selfhub_app.data.utils.LocalConstants.CNPJ_KEY
import digital.studioweb.selfhub_app.data.utils.LocalConstants.RESTAURANT_ID_KEY

interface AuthLocalDataSource {
    fun getCNPJ(): String
    fun saveCNPJ(cnpj: String)
    fun getRestaurantId(): String
    fun saveRestaurantId(restaurantId: String)
}

class AuthLocalDataSourceImpl(
    private val secureSharedLocalDataSource: SecureSharedLocalDataSourceInterface
) : AuthLocalDataSource {
    override fun getCNPJ(): String {
        return secureSharedLocalDataSource.retrieve(
            key = CNPJ_KEY,
            clazz = String::class
        ) ?: ""
    }

    override fun saveCNPJ(cnpj: String) {
        secureSharedLocalDataSource.store(
            key = CNPJ_KEY,
            value = cnpj
        )
    }

    override fun getRestaurantId(): String {
        return secureSharedLocalDataSource.retrieve(
            key = RESTAURANT_ID_KEY,
            clazz = String::class
        ) ?: ""
    }

    override fun saveRestaurantId(restaurantId: String) {
        secureSharedLocalDataSource.store(
            key = RESTAURANT_ID_KEY,
            value = restaurantId
        )
    }
}