package digital.studioweb.selfhub_app.data.service

import digital.studioweb.selfhub_app.data.dto.CategoryDTO
import digital.studioweb.selfhub_app.data.dto.LoginDTO
import digital.studioweb.selfhub_app.data.dto.ProductDTO
import digital.studioweb.selfhub_app.data.models.ApiResponse
import digital.studioweb.selfhub_app.data.models.Login
import digital.studioweb.selfhub_app.data.models.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): ApiResponse<LoginDTO>

    @GET("categories")
    suspend fun getCategories(
        @Query("restaurantId") restaurantId: String
    ): ApiResponse<List<CategoryDTO>>

    @GET("products")
    suspend fun getProducts(
        @Query("restaurantId") restaurantId: String
    ): ApiResponse<List<ProductDTO>>
}
