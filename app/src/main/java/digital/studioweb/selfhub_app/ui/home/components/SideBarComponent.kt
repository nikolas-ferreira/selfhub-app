package digital.studioweb.selfhub_app.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import digital.studioweb.selfhub_app.R

@Composable
fun SideBarComponent(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(color = colorResource(R.color.side_bar_background)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(top = 16.dp, start = 12.dp)
        ) {
            SideBarButtonComponent(
                isSelected = selectedIndex == 0,
                menuCategoryIcon = R.drawable.ic_sidebar_home,
                onClick = { onItemSelected(0) }
            )
            Spacer(Modifier.height(20.dp))
            SideBarButtonComponent(
                isSelected = selectedIndex == 1,
                menuCategoryIcon = R.drawable.ic_sidebar_order,
                onClick = { onItemSelected(1) }
            )
            Spacer(Modifier.height(20.dp))
            SideBarButtonComponent(
                isSelected = selectedIndex == 2,
                menuCategoryIcon = R.drawable.ic_sidebar_waiter,
                onClick = { onItemSelected(2) }
            )
        }

        Column(
            modifier = Modifier
                .padding(bottom = 16.dp, start = 12.dp)
        ) {
            SideBarButtonComponent(
                isSelected = selectedIndex == 3,
                menuCategoryIcon = R.drawable.ic_setting,
                onClick = { onItemSelected(3) }
            )
        }
    }
}


@Composable
private fun SideBarButtonComponent(
    isSelected: Boolean,
    menuCategoryIcon: Int,
    onClick: () -> Unit
) {
    val containerBackgroundColor = if (isSelected) {
        colorResource(id = R.color.app_background)
    } else {
        colorResource(id = R.color.side_bar_background)
    }
    val backgroundColor = if (isSelected) {
        colorResource(id = R.color.primary_orange)
    } else {
        colorResource(id = R.color.side_bar_background)
    }

    val borderColor = if (isSelected) {
        colorResource(id = R.color.primary_orange)
    } else {
        colorResource(id = R.color.side_bar_background)
    }

    val iconColor = if (isSelected) {
        colorResource(id = R.color.white)
    } else {
        colorResource(id = R.color.primary_orange)
    }

    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = containerBackgroundColor,
                shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
            )
            .width(100.dp)
            .height(90.dp)
            .background(
                color = containerBackgroundColor,
                shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
            )
            .clickable(onClick = onClick)
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.Center)
                .size(60.dp)
                .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(12.dp))
                .clickable(onClick = onClick),
            shape = RoundedCornerShape(12.dp),
            color = backgroundColor
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Image(
                    painter = painterResource(menuCategoryIcon),
                    contentDescription = "Imagem",
                    colorFilter = ColorFilter.tint(iconColor),
                    modifier = Modifier.size(26.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF939090)
@Composable
private fun SideBarComponentPreview() {
    SideBarComponent(
        selectedIndex = 0,
        onItemSelected = {}
    )
}
