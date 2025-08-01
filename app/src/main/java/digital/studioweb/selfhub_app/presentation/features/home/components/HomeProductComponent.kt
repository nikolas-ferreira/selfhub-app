package digital.studioweb.selfhub_app.presentation.features.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import digital.studioweb.selfhub_app.R
import digital.studioweb.selfhub_app.domain.features.home.models.ProductModel
import digital.studioweb.selfhub_app.presentation.features.home.HomeViewModel
import digital.studioweb.selfhub_app.presentation.features.home.models.HomeScreenEvent
import digital.studioweb.selfhub_app.presentation.features.home.models.HomeUIState
import digital.studioweb.selfhub_app.presentation.features.productdetails.ProductDetailsDialogComponent
import digital.studioweb.selfhub_app.presentation.utils.StringUtils.formatToBRLCurrency


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeProductComponent(
    product: ProductModel,
    uiState: HomeUIState,
    onEvent: (HomeScreenEvent) -> Unit
) {

    HomeProductContent(
        productModel = product,
        uiState = uiState,
        onEvent = onEvent
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeProductContent(
    productModel: ProductModel,
    uiState: HomeUIState,
    onEvent: (HomeScreenEvent) -> Unit
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.product_component_card_background),
        ),
        modifier = Modifier
            .width(220.dp)
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
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
                    model = productModel.imageUrl,
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
                    text = productModel.name,
                    color = colorResource(R.color.white),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    text = productModel.description,
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
                        text = formatToBRLCurrency(productModel.price),
                        color = colorResource(R.color.white),
                        fontSize = 16.sp,
                    )
                    Surface(
                        color = colorResource(R.color.product_component_order_button_background),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                onEvent(HomeScreenEvent.ShowDialogWithProduct(productModel))
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
    val mockProductModel = ProductModel(
        id = "1",
        name = "Risoto de Tomate",
        price = 49.99,
        imageUrl = "https://mercadoeconsumo.com.br/wp-content/uploads/2019/04/Que-comida-saud%C3%A1vel-que-nada-brasileiro-gosta-de-fast-food.jpg",
        description = "Risoto à Tomatte com tomates cereja e molho de gorgonzola.",
        createdAt = "2024-01-01T00:00:00Z",
        updatedAt = "2024-01-01T00:00:00Z",
        categoryId = "cat01",
        createdById = "admin01",
        lastEditedById = "admin01",
        customizationGroupModels = emptyList()
    )

    HomeProductComponent(
        product = mockProductModel,
        uiState = HomeUIState(),
        onEvent = {}
    )
}

@Preview
@Composable
fun HomeProductShimmerComponent() {
    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.View)
    Column(
        modifier = Modifier
            .width(220.dp)
            .wrapContentHeight()
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(R.color.product_component_card_background))
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clip(RoundedCornerShape(8.dp))
                .shimmer(shimmerInstance)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(20.dp)
                .clip(RoundedCornerShape(4.dp))
                .shimmer(shimmerInstance)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(RoundedCornerShape(4.dp))
                .shimmer(shimmerInstance)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(12.dp)
                .clip(RoundedCornerShape(4.dp))
                .shimmer(shimmerInstance)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmer(shimmerInstance)
                    .background(Color.Gray)
            )
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(50))
                    .shimmer(shimmerInstance)
                    .background(Color.Gray)
            )
        }
    }
}
