package digital.studioweb.selfhub_app.domain.features.auth.models

data class LoginRequestModel(
    val email: String,
    val password: String
)