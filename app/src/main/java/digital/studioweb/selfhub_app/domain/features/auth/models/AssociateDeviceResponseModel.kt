package digital.studioweb.selfhub_app.domain.features.auth.models

data class AssociateDeviceResponseModel(
    val statusCode: Int,
    val message: String,
    val restaurant: RestaurantModel,
)
