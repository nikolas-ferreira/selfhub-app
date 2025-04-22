package digital.studioweb.selfhub_app.data.usecases

import digital.studioweb.selfhub_app.data.models.Product
import digital.studioweb.selfhub_app.data.utils.Result
import javax.inject.Inject

/**
 * UseCase responsible for filtering products by category.
 * Follows the single responsibility principle by focusing on one domain action.
 */
class FilterProductsByCategoryUseCase @Inject constructor() {
    operator fun invoke(products: List<Product>, categoryId: String): Result<List<Product>> {
        return try {
            val filteredProducts = products.filter { it.category?.id == categoryId }
            Result.Success(filteredProducts)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
