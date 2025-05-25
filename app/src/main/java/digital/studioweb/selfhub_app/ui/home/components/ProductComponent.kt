package digital.studioweb.selfhub_app.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import digital.studioweb.selfhub_app.R
import digital.studioweb.selfhub_app.data.models.Product
import digital.studioweb.selfhub_app.ui.utils.StringUtils.formatToBRLCurrency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductComponent(product: Product) {
    val openDialog = remember { mutableStateOf(false) }

    fun showModal() {
        openDialog.value = true
    }

    fun closeModal() {
        openDialog.value = false
    }

    if (openDialog.value) {
        Dialog(
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                decorFitsSystemWindows = false
            ),
            onDismissRequest = { closeModal() }
        ) {
            ProductDialogContent(product)
        }
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.product_component_card_background),
        ),
        modifier = Modifier
            .width(220.dp)
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                )
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = product.name,
                    color = colorResource(R.color.white),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    text = product.description,
                    minLines = 2,
                    maxLines = 2,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Spacer(Modifier.height(26.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = formatToBRLCurrency(product.price),
                        color = colorResource(R.color.white),
                        fontSize = 16.sp,
                    )
                    Surface(
                        color = colorResource(R.color.product_component_order_button_background),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                showModal()
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(1.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductComponentPreview() {
    val mockProduct = Product(
        id = "1",
        name = "Risoto de Tomate",
        price = 49.99,
        imageUrl = "https://images.unsplash.com/photo-1604908177522-c29fd8aa64d2", // imagem real para o AsyncImage
        description = "Risoto à Tomatte com tomates cereja e molho de gorgonzola.",
        createdAt = "2024-01-01T00:00:00Z",
        updatedAt = "2024-01-01T00:00:00Z",
        categoryId = "cat01",
        createdById = "admin01",
        lastEditedById = "admin01",
        customizationGroups = emptyList()
    )

    ProductComponent(product = mockProduct)
}