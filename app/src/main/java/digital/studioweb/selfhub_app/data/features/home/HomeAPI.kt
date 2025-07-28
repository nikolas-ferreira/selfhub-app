package digital.studioweb.selfhub_app.data.features.home

import digital.studioweb.selfhub_app.data.base.ApiResponse
import digital.studioweb.selfhub_app.data.features.home.models.CategoryDTO
import digital.studioweb.selfhub_app.data.features.home.models.ProductDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeAPI {
    @GET("categories")
    suspend fun getCategories(
        @Query("restaurantId") restaurantId: String
    ): ApiResponse<List<CategoryDTO>>

    @GET("products")
    suspend fun getProducts(
        @Query("restaurantId") restaurantId: String
    ): ApiResponse<List<ProductDTO>>
}