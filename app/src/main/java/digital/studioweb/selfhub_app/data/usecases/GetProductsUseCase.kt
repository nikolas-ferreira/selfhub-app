package digital.studioweb.selfhub_app.data.usecases

import digital.studioweb.selfhub_app.data.models.Product
import digital.studioweb.selfhub_app.data.repositories.home.HomeRepository
import digital.studioweb.selfhub_app.data.utils.Result
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Result<List<Product>> {
        return try {
            val products = homeRepository.getAllProducts()
            Result.Success(products)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
