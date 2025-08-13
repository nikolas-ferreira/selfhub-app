package digital.studioweb.selfhub_app.domain.features.auth

import digital.studioweb.selfhub_app.domain.base.BaseUseCase

class SaveCNPJUseCase(
    private val repository: AuthRepository
) : BaseUseCase<Unit, SaveCNPJUseCase.Params>() {
    data class Params(val cnpj: String)

    override fun runSync(params: Params) {
        return repository.saveCNPJ(params.cnpj)
    }
}
