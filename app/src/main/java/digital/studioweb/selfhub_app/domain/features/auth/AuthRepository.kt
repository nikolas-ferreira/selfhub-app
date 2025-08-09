package digital.studioweb.selfhub_app.domain.features.auth

import digital.studioweb.selfhub_app.domain.features.auth.models.Login
import digital.studioweb.selfhub_app.domain.features.auth.models.LoginRequestModel

interface AuthRepository {
    suspend fun login(loginRequestModel: LoginRequestModel): Login
}