package digital.studioweb.selfhub_app.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import digital.studioweb.selfhub_app.R


@Composable
fun MenuCategoryItemComponent(
    isSelected: Boolean,
    menuCategoryIcon: Int,
    menuCategoryName: String,
    menuCategoryCount: Int,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) {
        colorResource(id = R.color.menu_category_item_card_selected_background)
    } else {
        colorResource(id = R.color.menu_category_item_card_unselected_background)
    }

    val borderColor = if (isSelected) {
        colorResource(id = R.color.menu_category_item_card_selected_border)
    } else {
        colorResource(id = R.color.menu_category_item_card_unselected_border)
    }

    Card(
        border = BorderStroke(1.dp, borderColor),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        modifier = Modifier
            .size(100.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            IconMenuCategoryItemComponent(isSelected, menuCategoryIcon)
            Column {
                Text(
                    text = menuCategoryName,
                    color = Color.Black,
                    fontSize = 10.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$menuCategoryCount itens",
                    color = Color.Gray,
                    fontSize = 8.sp
                )
            }
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
            menuCategoryCount = 120,
            onClick = {}
        )
        Spacer(modifier = Modifier.height(20.dp))
        MenuCategoryItemComponent(
            isSelected = false,
            menuCategoryIcon = R.drawable.ic_menu_category,
            menuCategoryName = "Todos Itens",
            menuCategoryCount = 120,
            onClick = {}
        )
    }
}