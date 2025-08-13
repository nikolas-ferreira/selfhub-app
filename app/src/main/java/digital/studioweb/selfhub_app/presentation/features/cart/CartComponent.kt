package digital.studioweb.selfhub_app.presentation.features.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import digital.studioweb.selfhub_app.R
import digital.studioweb.selfhub_app.presentation.features.cart.components.CartDashedDivider
import digital.studioweb.selfhub_app.presentation.features.cart.components.CartItemComponent
import digital.studioweb.selfhub_app.presentation.features.home.HomeViewModel
import digital.studioweb.selfhub_app.presentation.features.home.models.HomeScreenEvent
import digital.studioweb.selfhub_app.presentation.utils.StringUtils.formatToBRLCurrency

@Composable
fun ConfirmOrderDialog(onEvent: (HomeScreenEvent) -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .width(600.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.app_background))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)

        ) {
            Text("Deseja finalizar seu pedido?", color = White, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Seu pedido começará a ser preparado imediatamente.",
                color = White,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        onEvent(HomeScreenEvent.CloseDialog)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.edit_text_border)
                    ),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    Text(
                        text = "Cancelar",
                        color = White,
                        fontSize = 16.sp
                    )
                }
                Button(
                    onClick = {
                        onEvent(HomeScreenEvent.OnConfirmOrder)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.primary_orange)
                    ),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    Text(
                        text = "Finalizar",
                        color = White,
                        fontSize = 16.sp
                    )
                }

            }
        }
    }
}

@Composable
fun CartComponent(
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState = viewModel.uiState
    val onEvent = viewModel::onEvent
    val order = viewModel.uiState.order

    if (uiState.showConfirmDialog) {
        Dialog(
            onDismissRequest = { onEvent(HomeScreenEvent.CloseDialog) },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                decorFitsSystemWindows = false
            )
        ) {
            ConfirmOrderDialog(onEvent)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.side_bar_background))
    ) {
        /**
         * Table details
         */
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Surface(
                color = colorResource(R.color.primary_orange),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .size(44.dp)
                    .clickable(onClick = {})
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    colorFilter = ColorFilter.tint(colorResource(R.color.white)),
                    contentDescription = "Close",
                    modifier = Modifier.padding(12.dp)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Mesa 043",
                    color = colorResource(R.color.white),
                    fontSize = 22.sp,
                    style = TextStyle(fontFamily = FontFamily.Serif)
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    color = colorResource(R.color.white),
                    text = "Pedido N°: #45122",
                    style = TextStyle(fontFamily = FontFamily.Serif)
                )
            }

            Surface(
                color = colorResource(R.color.primary_orange),
                shape = RoundedCornerShape(50),
                modifier = Modifier.size(44.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_edit),
                    colorFilter = ColorFilter.tint(colorResource(R.color.white)),
                    contentDescription = null,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }

        Spacer(Modifier.height(12.dp))
        HorizontalDivider(
            color = colorResource(R.color.divider_color), thickness = 0.6.dp
        )

        /**
         * Cart content
         */
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            if (order.items.isEmpty()) {
                Spacer(Modifier.height(48.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        color = colorResource(R.color.icon_menu_category_item_card_unselected),
                        text = "Carrinho Vazio",
                        fontSize = 22.sp,
                        style = TextStyle(fontFamily = FontFamily.Serif)
                    )
                }
                Spacer(Modifier.height(48.dp))
                CartDashedDivider()
            } else {
                Spacer(Modifier.height(8.dp))
                for (item in order.items) {
                    CartItemComponent(item = item)
                }
                Spacer(Modifier.height(48.dp))
            }
        }


        /**
         * Footer
         */
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(Modifier.height(8.dp))
            CartDashedDivider()
            Spacer(Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    color = colorResource(R.color.white),
                    text = "Subtotal",
                    fontSize = 18.sp,
                    style = TextStyle(fontFamily = FontFamily.Serif)
                )
                Text(
                    color = colorResource(R.color.white),
                    text = "R$ 0,00",
                    fontSize = 18.sp,
                    style = TextStyle(fontFamily = FontFamily.Serif)
                )
            }
            Spacer(Modifier.height(12.dp))
            CartDashedDivider()
            Spacer(Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    color = colorResource(R.color.white),
                    text = "TOTAL",
                    fontSize = 22.sp,
                    style = TextStyle(fontFamily = FontFamily.Serif)
                )
                Text(
                    color = colorResource(R.color.white),
                    text = formatToBRLCurrency(order.totalValue),
                    fontSize = 22.sp,
                    style = TextStyle(fontFamily = FontFamily.Serif)
                )
            }
            Spacer(Modifier.height(12.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(
                        color = if (uiState.order.items.isEmpty()) colorResource(R.color.edit_text_border) else colorResource(
                            R.color.primary_orange
                        )
                    )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .clickable(onClick = {
                            if (uiState.order.items.isNotEmpty())
                                onEvent(HomeScreenEvent.ShowConfirmDialog)
                        })
                ) {
                    Text(
                        text = "Finalizar pedido",
                        color = White,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CartComponentPreview() {
    CartComponent()
}
