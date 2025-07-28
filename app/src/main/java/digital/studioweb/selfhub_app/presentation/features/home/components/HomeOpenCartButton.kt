package digital.studioweb.selfhub_app.presentation.features.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import digital.studioweb.selfhub_app.R

@Preview(showBackground = true)
@Composable
fun HomeOpenCartButton(
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .height(45.dp)
            .background(colorResource(R.color.product_component_card_background), shape = RoundedCornerShape(50.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Row(
            Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Carrinho", color = Color.White)
            Spacer(modifier = Modifier.width(12.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_cart),
                colorFilter = ColorFilter.tint(
                    colorResource(
                        id = R.color.primary_orange
                    )
                ),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
