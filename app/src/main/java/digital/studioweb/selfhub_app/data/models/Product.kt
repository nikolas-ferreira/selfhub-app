package digital.studioweb.selfhub_app.data.models

import com.google.firebase.firestore.DocumentReference

data class Product(
    var id: String = "",
    val category: DocumentReference? = null,
    val iconUrl: String = "",
    val name: String = "",
    val description: String = "",
    var addItems: List<AddItem>? = emptyList(),
    val price: Double = 0.0,
)

data class AddItem(
    val name: String = "",
    val price: Double = 0.0
)