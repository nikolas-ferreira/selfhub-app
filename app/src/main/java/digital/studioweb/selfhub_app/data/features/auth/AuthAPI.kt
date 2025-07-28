package digital.studioweb.selfhub_app.data.features.auth

import digital.studioweb.selfhub_app.data.base.ApiResponse
import digital.studioweb.selfhub_app.data.features.auth.models.LoginDTO
import digital.studioweb.selfhub_app.domain.features.auth.models.LoginRequestModel
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
    @POST("auth/login")
    suspend fun login(@Body loginRequestModel: LoginRequestModel): ApiResponse<LoginDTO>
}