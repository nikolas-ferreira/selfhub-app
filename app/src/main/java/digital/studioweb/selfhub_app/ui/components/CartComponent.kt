package digital.studioweb.selfhub_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import digital.studioweb.selfhub_app.R

@Preview(showBackground = true)
@Composable
fun CartComponent(
    onClose: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Surface(
                    color = colorResource(R.color.app_background),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .size(44.dp)
                        .clickable(onClick = onClose)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_back),
                        colorFilter = ColorFilter.tint(colorResource(R.color.icon_menu_category_item_card_unselected)),
                        contentDescription = "Close",
                        modifier = Modifier.padding(12.dp)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Mesa 043",
                        color = colorResource(R.color.icon_menu_category_item_card_unselected),
                        fontSize = 22.sp,
                        style = TextStyle(
                            fontFamily = FontFamily.Serif
                        )
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        color = colorResource(R.color.icon_menu_category_item_card_unselected),
                        text = "Pedido N°: #45122",
                        style = TextStyle(
                            fontFamily = FontFamily.Serif
                        )
                    )
                }

                Surface(
                    color = colorResource(R.color.app_background),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.size(44.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_edit),
                        colorFilter = ColorFilter.tint(colorResource(R.color.icon_menu_category_item_card_unselected)),
                        contentDescription = null,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
            HorizontalDivider(
                color = colorResource(R.color.divider_color), thickness = 4.dp
            )
            Spacer(Modifier.height(48.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    color = colorResource(R.color.icon_menu_category_item_card_unselected),
                    text = "Carrinho Vazio",
                    fontSize = 22.sp,
                    style = TextStyle(
                        fontFamily = FontFamily.Serif
                    )
                )
            }
            Spacer(Modifier.height(48.dp))
            DashedDivider()
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
        ) {
            DashedDivider()
            Spacer(Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    color = colorResource(R.color.icon_menu_category_item_card_unselected),
                    text = "Subtotal",
                    fontSize = 14.sp,
                    style = TextStyle(
                        fontFamily = FontFamily.Serif
                    )
                )
                Text(
                    color = colorResource(R.color.icon_menu_category_item_card_unselected),
                    text = "R$ 0,00",
                    fontSize = 14.sp,
                    style = TextStyle(
                        fontFamily = FontFamily.Serif
                    )
                )
            }
            Spacer(Modifier.height(12.dp))
            DashedDivider()
            Spacer(Modifier.height(26.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    color = colorResource(R.color.black),
                    text = "TOTAL",
                    fontSize = 14.sp,
                    style = TextStyle(
                        fontFamily = FontFamily.Serif
                    )
                )
                Text(
                    color = colorResource(R.color.black),
                    text = "R$ 0,00",
                    fontSize = 14.sp,
                    style = TextStyle(
                        fontFamily = FontFamily.Serif
                    )
                )
            }
            Spacer(Modifier.height(26.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(
                        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
                        color = colorResource(R.color.icon_menu_category_item_card_selected_background)
                    )
            ) {
                Surface(
                    modifier = Modifier
                        .padding(24.dp)
                ) {

                }
            }
        }
    }
}
