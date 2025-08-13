package digital.studioweb.selfhub_app.domain.features.auth

import digital.studioweb.selfhub_app.domain.base.BaseUseCase

class SaveRestaurantIdUseCase(
    private val repository: AuthRepository
) : BaseUseCase<Unit, SaveRestaurantIdUseCase.Params>() {
    data class Params(val restaurantId: String)

    override fun runSync(params: Params) {
        repository.saveRestaurantId(params.restaurantId)
    }
}
