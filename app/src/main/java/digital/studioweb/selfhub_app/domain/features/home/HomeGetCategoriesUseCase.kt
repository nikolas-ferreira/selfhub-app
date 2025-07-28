package digital.studioweb.selfhub_app.domain.features.home

import digital.studioweb.selfhub_app.domain.base.NoParamsBaseAsyncUseCase
import digital.studioweb.selfhub_app.domain.features.home.models.CategoryModel
import digital.studioweb.selfhub_app.data.base.Result
import javax.inject.Inject

class HomeGetCategoriesUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) : NoParamsBaseAsyncUseCase<Result<List<CategoryModel>>>() {

    override suspend fun runAsync(): Result<List<CategoryModel>> {
        return try {
            val categories = homeRepository.getCategories()
            Result.Success(categories)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
