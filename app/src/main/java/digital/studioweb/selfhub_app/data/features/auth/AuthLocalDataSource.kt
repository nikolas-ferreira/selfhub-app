package digital.studioweb.selfhub_app.data.features.auth

import digital.studioweb.selfhub_app.data.sources.SecureSharedLocalDataSourceInterface
import digital.studioweb.selfhub_app.data.utils.LocalConstants.CNPJ_KEY

interface AuthLocalDataSource {
    fun getCNPJ(): String
    fun saveCNPJ(cnpj: String)
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
}