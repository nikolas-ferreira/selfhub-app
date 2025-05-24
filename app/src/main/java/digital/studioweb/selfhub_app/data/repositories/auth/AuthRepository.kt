package digital.studioweb.selfhub_app.data.repositories.auth

import digital.studioweb.selfhub_app.data.models.Login

interface AuthRepository {
    suspend fun login(): Login
}