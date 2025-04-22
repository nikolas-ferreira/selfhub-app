package digital.studioweb.selfhub_app.data.models

import com.google.firebase.firestore.DocumentReference

data class MenuCategoryItem(
    var id: String = "",
    val iconUrl: String = "",
    val name: String = "",
    val restaurant: DocumentReference? = null
)