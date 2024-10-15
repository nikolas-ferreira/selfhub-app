package digital.studioweb.selfhub_app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import digital.studioweb.selfhub_app.R
import digital.studioweb.selfhub_app.ui.home.components.MenuCategoryItemComponent

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    val scrollState = rememberScrollState()
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.app_background))
    ) {
        Row(
            modifier = Modifier
                .padding(start = 12.dp, top = 12.dp)
                .horizontalScroll(scrollState),
        ) {
            MenuCategoryItemComponent(
                isSelected = true,
                menuCategoryName = "Todos Itens",
                menuCategoryCount = 120,
                menuCategoryIcon = R.drawable.ic_menu_category
            )
            Spacer(modifier = Modifier.width(8.dp))
            MenuCategoryItemComponent(
                isSelected = false,
                menuCategoryName = "Hamburguer",
                menuCategoryCount = 120,
                menuCategoryIcon = R.drawable.ic_menu_category
            )
            Spacer(modifier = Modifier.width(8.dp))
            MenuCategoryItemComponent(
                isSelected = false,
                menuCategoryName = "Xis",
                menuCategoryCount = 120,
                menuCategoryIcon = R.drawable.ic_menu_category
            )
            Spacer(modifier = Modifier.width(8.dp))
            MenuCategoryItemComponent(
                isSelected = false,
                menuCategoryName = "Hot Dogs",
                menuCategoryCount = 120,
                menuCategoryIcon = R.drawable.ic_menu_category
            )
        }
    }


}