package digital.studioweb.selfhub_app.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import digital.studioweb.selfhub_app.R


@Composable
fun IconMenuCategoryItemComponent(
    isSelected: Boolean,
    menuCategoryIcon: Int
) {
    val backgroundColor = if (isSelected) {
        colorResource(id = R.color.icon_menu_category_item_card_selected_background)
    } else {
        colorResource(id = R.color.icon_menu_category_item_card_unselected_background)
    }

    val iconColor = if (isSelected) {
        colorResource(id = R.color.icon_menu_category_item_card_selected)
    } else {
        colorResource(id = R.color.icon_menu_category_item_card_unselected)
    }

    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(50),
        modifier = Modifier.size(32.dp)
    ) {
        Image(
            painter = painterResource(id = menuCategoryIcon),
            colorFilter = ColorFilter.tint(iconColor),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .padding(6.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Column(
        Modifier
            .padding(20.dp)
            .background(Color.Gray)) {
        IconMenuCategoryItemComponent(true, R.drawable.ic_menu_category)
        Spacer(modifier = Modifier.height(20.dp))
        IconMenuCategoryItemComponent(false, R.drawable.ic_menu_category)
    }
}