package digital.studioweb.selfhub_app.presentation.features.cart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import digital.studioweb.selfhub_app.R
import digital.studioweb.selfhub_app.domain.features.home.models.CartOrderItemModel
import digital.studioweb.selfhub_app.domain.features.home.models.CustomizationOptionModel
import digital.studioweb.selfhub_app.presentation.features.productdetails.components.ProductCount
import digital.studioweb.selfhub_app.presentation.utils.StringUtils.formatToBRLCurrency

@Composable
fun CartItemComponent(
    item: CartOrderItemModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.app_background)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = item.ratingStar.toString(),
                        tint = Color(0xFFFFB800),
                        modifier = Modifier.size(14.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = item.ratingStar.toString(),
                        tint = Color(0xFFFFB800),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = item.ratingStar.toString(),
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                for (option in item.customizationOptions) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val itemTotalValue = option.additionalPrice * option.quantity
                        Text(
                            text = "${option.quantity}x ${option.name}",
                            color = colorResource(R.color.divider_color),
                            fontSize = 8.sp
                        )
                        Text(
                            text = formatToBRLCurrency(itemTotalValue),
                            color = Color.White,
                            fontSize = 8.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
                if(item.observation.isNotBlank()){
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        Image(
                            painter = painterResource(R.drawable.ic_comment),
                            contentDescription = null,
                            modifier = Modifier.size(8.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Observação:",
                            color = colorResource(R.color.white),
                            fontSize = 8.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.observation,
                    color = colorResource(R.color.white),
                    fontSize = 8.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = formatToBRLCurrency(item.price),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    ProductCount(
                        quantity = 1,
                        onIncrement = { },
                        onDecrement = {}
                    )

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartItemComponentPreview() {
    CartItemComponent(
        item = CartOrderItemModel(
            name = "Xis Coração",
            price = 25.0,
            imageUrl = "https://trxis.com.br/media/trxis%20fil%C3%A9.jpg",
            ratingStar = 4.9,
            observation = "sem milho por favor",
            customizationOptions = listOf(
                CustomizationOptionModel(
                    id = "1",
                    name = "Batata",
                    additionalPrice = 5.0,
                    quantity = 1
                ),
                CustomizationOptionModel(
                    id = "1",
                    name = "Molho",
                    additionalPrice = 5.0,
                    quantity = 1
                ),
                CustomizationOptionModel(
                    id = "1",
                    name = "Cheddar",
                    additionalPrice = 3.82,
                    quantity = 3
                )
            )
        )
    )
}