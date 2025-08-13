package digital.studioweb.selfhub_app.domain.features.auth

import digital.studioweb.selfhub_app.domain.base.NoParamsBaseUseCase

class GetCNPJUseCase(
    private val repository: AuthRepository
) : NoParamsBaseUseCase<String>() {

    override fun runSync(): String {
        return repository.getCNPJ()
    }
}
