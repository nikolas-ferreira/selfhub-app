package digital.studioweb.selfhub_app.data.features.auth

import digital.studioweb.selfhub_app.domain.features.auth.AuthRepository
import digital.studioweb.selfhub_app.data.features.auth.AuthAPI
import digital.studioweb.selfhub_app.domain.features.auth.models.Login
import digital.studioweb.selfhub_app.domain.features.auth.models.LoginRequestModel
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authAPI: AuthAPI
) : AuthRepository {
    override suspend fun login(): Login =
        authAPI.login(
            LoginRequestModel(
                email = "admin@admin",
                password = "12qwaszx"
            )
        ).response.mapTo()
}