package digital.studioweb.selfhub_app.data.dto

import digital.studioweb.selfhub_app.data.models.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("price")
    val price: Double,

    @SerialName("imageUrl")
    val imageUrl: String,

    @SerialName("description")
    val description: String,

    @SerialName("createdAt")
    val createdAt: String,

    @SerialName("updatedAt")
    val updatedAt: String,

    @SerialName("categoryId")
    val categoryId: String,

    @SerialName("createdById")
    val createdById: String,

    @SerialName("lastEditedById")
    val lastEditedById: String
) {
    fun mapTo(): Product {
        return Product(
            id = id,
            name = name,
            price = price,
            imageUrl = imageUrl,
            description = description,
            createdAt = createdAt,
            updatedAt = updatedAt,
            categoryId = categoryId,
            createdById = createdById,
            lastEditedById = lastEditedById
        )
    }
}