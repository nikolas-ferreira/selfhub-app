package digital.studioweb.selfhub_app.presentation.features.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import digital.studioweb.selfhub_app.R


@Composable
fun HomeCategoryItemComponent(
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

    val textColor = if (isSelected) {
        colorResource(id = R.color.white)
    } else {
        colorResource(id = R.color.white)
    }

    Surface(
        modifier = Modifier
            .size(120.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        color = backgroundColor
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)

        ) {
            Image(
                painter = painterResource(menuCategoryIcon),
                contentDescription = "Imagem",
                modifier = Modifier.size(80.dp)
            )
            Text(
                fontSize = 16.sp,
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
        HomeCategoryItemComponent(
            isSelected = true,
            menuCategoryIcon = R.drawable.hamburguer,
            menuCategoryName = "Todos Itens",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(20.dp))
        HomeCategoryItemComponent(
            isSelected = false,
            menuCategoryIcon = R.drawable.hamburguer,
            menuCategoryName = "Todos Itens",
            onClick = {}
        )
    }
}

@Preview
@Preview
@Composable
fun HomeCategoryShimmerComponent() {
    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.View)

    Surface(
        modifier = Modifier
            .size(120.dp),
        shape = RoundedCornerShape(12.dp),
        color = colorResource(id = R.color.product_component_card_background)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shimmer(shimmerInstance)
                    .background(Color.Gray.copy(alpha = 0.3f))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth(0.6f)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmer(shimmerInstance)
                    .background(Color.Gray.copy(alpha = 0.3f))
            )
        }
    }
}
