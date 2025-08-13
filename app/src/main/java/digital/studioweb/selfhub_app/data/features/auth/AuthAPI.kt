package digital.studioweb.selfhub_app.data.features.auth

import digital.studioweb.selfhub_app.data.base.ApiResponse
import digital.studioweb.selfhub_app.data.features.auth.models.AssociateDeviceRequestDTO
import digital.studioweb.selfhub_app.data.features.auth.models.RestaurantDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthAPI {
    @POST("auth/associate-device")
    suspend fun associateDevice(
        @Body associateDeviceRequest: AssociateDeviceRequestDTO
    ): ApiResponse<RestaurantDTO>

    @GET("restaurant/{restaurantCnpj}")
    suspend fun getRestaurantByCNPJ(
        @Path("restaurantCnpj") cnpj: String
    ): ApiResponse<Unit>
}