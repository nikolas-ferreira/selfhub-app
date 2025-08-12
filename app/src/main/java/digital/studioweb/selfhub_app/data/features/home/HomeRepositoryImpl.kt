package digital.studioweb.selfhub_app.data.features.home

import digital.studioweb.selfhub_app.domain.features.home.HomeRepository
import digital.studioweb.selfhub_app.domain.features.home.models.CartOrderModel
import digital.studioweb.selfhub_app.domain.features.home.models.CategoryModel
import digital.studioweb.selfhub_app.domain.features.home.models.ProductModel
import digital.studioweb.selfhub_app.domain.features.home.models.CartOrderItemModel
import digital.studioweb.selfhub_app.domain.features.home.models.CustomizationOptionModel
import digital.studioweb.selfhub_app.data.features.home.models.OrderDTO
import digital.studioweb.selfhub_app.data.features.home.models.OrderItemDTO
import digital.studioweb.selfhub_app.data.features.home.models.CustomizationOptionDTO
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeAPI: HomeAPI
) : HomeRepository {
    override suspend fun getCategories(): List<CategoryModel> {
        return homeAPI.getCategories(
            restaurantId = "6825ffeba5460eebd9b0f8ec"
        ).response.map { it.mapTo() }
    }

    override suspend fun getAllProducts(): List<ProductModel> {
        return homeAPI.getProducts(
            restaurantId = "6825ffeba5460eebd9b0f8ec"
        ).response.map { it.mapTo() }
    }

    override suspend fun createOrder(order: CartOrderModel) {
        homeAPI.createOrder(order.mapToDto())
    }

    private fun CartOrderModel.mapToDto(): OrderDTO {
        return OrderDTO(
            orderNumber = orderNumber.toIntOrNull(),
            tableNumber = 5,
            waiterNumber = 7,
            paymentMethod = paymentMethod,
            totalValue = totalValue,
            restaurantId = "6825ffeba5460eebd9b0f8ec",
            items = items.map { it.mapToDto() }
        )
    }

    private fun CartOrderItemModel.mapToDto(): OrderItemDTO {
        return OrderItemDTO(
            productId = productId,
            quantity = quantity,
            observation = observation,
            ratingStar = ratingStar,
            imageUrl = imageUrl,
            customizationOptions = customizationOptions.map { it.mapToDto() }
        )
    }

    private fun CustomizationOptionModel.mapToDto(): CustomizationOptionDTO {
        return CustomizationOptionDTO(
            name = name,
            additionalPrice = additionalPrice,
            quantity = quantity
        )
    }
}