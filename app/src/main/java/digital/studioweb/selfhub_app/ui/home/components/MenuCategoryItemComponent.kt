package digital.studioweb.selfhub_app.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Surface
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


@Composable
fun MenuCategoryItemComponent(
    isSelected: Boolean,
    menuCategoryIcon: Int,
    menuCategoryName: String,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) {
        colorResource(id = R.color.menu_category_item_card_selected_background)
    } else {
        colorResource(id = R.color.menu_category_item_card_unselected_background)
    }

    val iconTint = if (isSelected) {
        colorResource(id = R.color.white)
    } else {
        colorResource(id = R.color.dark_color)
    }

    val textColor = if (isSelected) {
        colorResource(id = R.color.white)
    } else {
        colorResource(id = R.color.dark_color)
    }

    Surface(
        modifier = Modifier
            .width(160.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(6.dp),
        color = backgroundColor
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)

        ) {
            Image(
                painter = painterResource(menuCategoryIcon),
                contentDescription = "Imagem",
                colorFilter = ColorFilter.tint(iconTint),
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                fontSize = 10.sp,
                text = menuCategoryName,
                maxLines = 1,
                color = textColor
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    Column(Modifier.padding(20.dp)) {
        MenuCategoryItemComponent(
            isSelected = true,
            menuCategoryIcon = R.drawable.ic_menu_category,
            menuCategoryName = "Todos Itens",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(20.dp))
        MenuCategoryItemComponent(
            isSelected = false,
            menuCategoryIcon = R.drawable.ic_menu_category,
            menuCategoryName = "Todos Itens",
            onClick = {}
        )
    }
}