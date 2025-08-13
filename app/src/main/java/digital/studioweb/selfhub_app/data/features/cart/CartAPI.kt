package digital.studioweb.selfhub_app.data.features.cart

import digital.studioweb.selfhub_app.data.base.ApiResponse
import digital.studioweb.selfhub_app.data.features.cart.models.CartCreateOrderDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface CartAPI {
    @POST("orders")
    suspend fun createOrder(
        @Body cartCreateOrderDTO: CartCreateOrderDTO
    ): ApiResponse<Unit>
}