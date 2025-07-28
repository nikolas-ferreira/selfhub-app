package digital.studioweb.selfhub_app.domain.features.auth

import digital.studioweb.selfhub_app.domain.features.auth.models.Login

interface AuthRepository {
    suspend fun login(): Login
}