package digital.studioweb.selfhub_app.domain.features.auth

import digital.studioweb.selfhub_app.domain.features.auth.models.Login
import digital.studioweb.selfhub_app.domain.features.auth.models.LoginRequestModel
import digital.studioweb.selfhub_app.data.base.Result
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(loginRequestModel: LoginRequestModel): Result<Login> {
        return try {
            val restaurant = authRepository.login(loginRequestModel)
            Result.Success(restaurant)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}