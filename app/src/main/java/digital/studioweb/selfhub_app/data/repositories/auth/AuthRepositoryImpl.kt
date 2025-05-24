package digital.studioweb.selfhub_app.data.repositories.auth

import digital.studioweb.selfhub_app.data.models.Login
import digital.studioweb.selfhub_app.data.models.request.LoginRequest
import digital.studioweb.selfhub_app.data.service.ApiService
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiServices: ApiService
) : AuthRepository {
    override suspend fun login(): Login =
        apiServices.login(
            LoginRequest(
                email = "admin@admin",
                password = "12qwaszx"
            )
        ).response.mapTo()
}