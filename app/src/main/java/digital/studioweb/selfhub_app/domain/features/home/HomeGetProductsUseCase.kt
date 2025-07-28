package digital.studioweb.selfhub_app.domain.features.home

import digital.studioweb.selfhub_app.domain.base.NoParamsBaseAsyncUseCase
import digital.studioweb.selfhub_app.domain.features.home.models.ProductModel
import digital.studioweb.selfhub_app.data.base.Result
import javax.inject.Inject

class HomeGetProductsUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) : NoParamsBaseAsyncUseCase<Result<List<ProductModel>>>() {

    override suspend fun runAsync(): Result<List<ProductModel>> {
        return try {
            val products = homeRepository.getAllProducts()
            Result.Success(products)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
