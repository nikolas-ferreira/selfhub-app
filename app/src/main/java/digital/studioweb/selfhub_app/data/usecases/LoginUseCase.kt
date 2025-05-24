package digital.studioweb.selfhub_app.data.usecases

import digital.studioweb.selfhub_app.data.models.Login
import digital.studioweb.selfhub_app.data.repositories.auth.AuthRepository
import digital.studioweb.selfhub_app.data.utils.Result
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
