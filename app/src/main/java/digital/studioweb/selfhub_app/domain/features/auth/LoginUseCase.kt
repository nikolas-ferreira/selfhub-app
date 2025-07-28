package digital.studioweb.selfhub_app.domain.features.auth

import digital.studioweb.selfhub_app.domain.features.auth.models.Login
import digital.studioweb.selfhub_app.data.base.Result
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<Login> {
        return try {
            val restaurant = authRepository.login()
            Result.Success(restaurant)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}