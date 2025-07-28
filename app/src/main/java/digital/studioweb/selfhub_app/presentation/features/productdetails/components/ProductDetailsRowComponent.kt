package digital.studioweb.selfhub_app.presentation.features.productdetails.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import digital.studioweb.selfhub_app.R

@Preview(showBackground = true, backgroundColor = 0xFF172134)
@Composable
fun ProductDetailsComponent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 46.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Item(title = "Avaliações", description = "4.8", icon = R.drawable.ic_star)
        Item(title = "Tempo", description = "15 min", icon = R.drawable.ic_cronometer)
        Item(title = "Calorias", description = "547 kcal", icon = R.drawable.ic_fire)
    }
}

@Composable
private fun Item(
    title: String,
    description: String,
    icon: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = colorResource(R.color.white_smoke),
            fontSize = 12.sp
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(icon),
                contentDescription = "",
                colorFilter = ColorFilter.tint(colorResource(R.color.product_component_order_button_background)),
                modifier = Modifier
                    .size(16.dp)
                    .padding(end = 4.dp)
            )
            Text(
                text = description,
                color = colorResource(R.color.white_smoke), fontSize = 12.sp
            )
        }
    }
}