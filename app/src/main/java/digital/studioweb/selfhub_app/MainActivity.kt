package digital.studioweb.selfhub_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import digital.studioweb.selfhub_app.ui.home.HomeScreen
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val database = FirebaseDatabase.getInstance()
            val categoriesRef = database.getReference("loja-xpto")

            val categorias = listOf(
                "Bebidas",
                "Sobremesas",
                "Entradas",
                "Pratos Principais",
                "Saladas"
            )

            for (nome in categorias) {
                val categoryId = categoriesRef.push().key
                val category = mapOf("name" to nome)
                if (categoryId != null) {
                    categoriesRef.child(categoryId).setValue(category)
                }
            }

            HomeScreen()
        }
    }
}

data class Category(
    val categoryName: String? = null,
    val iconUrl: String? = null
)

data class ItemMenu(
    val itemMenuName: String? = null,
    val itemMenuPrice: Double? = null,
    val itemMenuPhotoUrl: String? = null,
    val categoryId: Int? = null
)

