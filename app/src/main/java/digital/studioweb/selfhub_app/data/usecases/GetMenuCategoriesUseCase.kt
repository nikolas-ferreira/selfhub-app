package digital.studioweb.selfhub_app.data.usecases

import digital.studioweb.selfhub_app.data.models.MenuCategoryItem
import digital.studioweb.selfhub_app.data.repositories.HomeRepository
import digital.studioweb.selfhub_app.data.utils.Result
import javax.inject.Inject

/**
 * UseCase responsible for retrieving menu categories.
 * Follows the single responsibility principle by focusing on one domain action.
 */
class GetMenuCategoriesUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Result<List<MenuCategoryItem>> {
        return try {
            val categories = homeRepository.getMenuCategoryItems()
            if (categories.isEmpty()) {
                Result.Success(emptyList())
            } else {
                Result.Success(categories)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
