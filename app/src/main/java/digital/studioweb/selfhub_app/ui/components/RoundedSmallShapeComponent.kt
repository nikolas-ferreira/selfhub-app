package digital.studioweb.selfhub_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import digital.studioweb.selfhub_app.R


@Composable
fun RoundedSmallShapeComponent(
    icon: Int,
    colorFilter: Int? = null,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(45.dp)
            .clip(CircleShape)
            .background(Color.White)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = icon),
            colorFilter = ColorFilter.tint(
                colorResource(
                    id = colorFilter ?: R.color.icon_menu_category_item_card_selected_background
                )
            ),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    RoundedSmallShapeComponent(icon = R.drawable.ic_cart, onClick = {})
}

