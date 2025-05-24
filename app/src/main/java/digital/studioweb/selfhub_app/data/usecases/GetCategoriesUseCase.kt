package digital.studioweb.selfhub_app.data.usecases

import digital.studioweb.selfhub_app.data.models.Category
import digital.studioweb.selfhub_app.data.repositories.home.HomeRepository
import digital.studioweb.selfhub_app.data.utils.Result
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Result<List<Category>> {
        return try {
            val categories = homeRepository.getCategories()
            Result.Success(categories)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
