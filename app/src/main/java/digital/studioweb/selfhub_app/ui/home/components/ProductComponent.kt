package digital.studioweb.selfhub_app.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import digital.studioweb.selfhub_app.R
import digital.studioweb.selfhub_app.data.models.Product
import digital.studioweb.selfhub_app.ui.utils.StringUtils.formatToBRLCurrency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductComponent(product: Product) {
    var isBottomSheetVisible by remember { mutableStateOf(false) }

    if (isBottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { isBottomSheetVisible = false }
        ) {
            BottomSheetContent(product)
        }
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.product_component_card_background),
        ),
        modifier = Modifier
            .width(180.dp)
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    painter = painterResource(R.drawable.risoto),
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    product.name,
                    color = colorResource(R.color.dark_color),
                    fontSize = 12.sp,
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = product.description,
                    minLines = 2,
                    maxLines = 2,
                    color = Color.Gray,
                    fontSize = 8.sp
                )
                Spacer(Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = formatToBRLCurrency(product.price),
                        color = colorResource(R.color.dark_color),
                        fontSize = 14.sp,
                    )
                    Surface(
                        color = colorResource(R.color.product_component_order_button_background),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .size(18.dp)

                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(1.dp)
                                .clickable {
                                    isBottomSheetVisible = true
                                }

                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomSheetContent(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            letterSpacing = 2.sp,
            text = product.name,
            fontSize = 32.sp,
            fontFamily = FontFamily(Font(R.font.calif_regular)),
            color = Color.DarkGray,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = formatToBRLCurrency(product.price),
                fontFamily = FontFamily(Font(R.font.dinnext_medium)),
                color = colorResource(R.color.dark_color),
                fontSize = 18.sp,
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = colorResource(R.color.product_component_order_button_background),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .size(16.dp)

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_remove),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(1.dp)
                            .clickable { }

                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "1",
                    color = colorResource(R.color.dark_color),
                    fontSize = 12.sp,
                )
                Spacer(modifier = Modifier.width(12.dp))
                Surface(
                    color = colorResource(R.color.product_component_order_button_background),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .size(16.dp)

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(1.dp)
                            .clickable { }

                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            lineHeight = 18.sp,
            text = "Risoto à Tomatto, uma verdadeira especialidade da casa.\nCoberto de tomates cereja ao molho de gorgonzola, com páprica picante no ponto certo. Acompanha um cordeiro, bife ao ponto, sem mais nem menos.\nExcelente escolha para quem quer um jantar sofisticado e leve.\nServe até 2 pessoas.",
            color = Color.Gray,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(24.dp))

        product.addItems?.let { addItems ->
            Text(
                text = "ADICIONAIS",
                fontFamily = FontFamily(Font(R.font.dinnext_medium)),
                color = colorResource(R.color.dark_color),
                fontSize = 18.sp
            )
            LazyColumn {
                items(addItems.size) { index ->
                    val addItem = addItems[index]
                    AddItemCheckComponent(addItem)
                    if (index < addItems.lastIndex) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(0.5.dp)
                                .background(colorResource(R.color.dark_color))
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "OBSERVAÇÕES",
            fontFamily = FontFamily(Font(R.font.dinnext_medium)),
            color = colorResource(R.color.dark_color),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        var isFocused by remember { mutableStateOf(false) }
        var text by remember { mutableStateOf("") }
        TextField(
            placeholder = { Text(text = "Digite aqui...", color = Color.Gray) },
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .width(450.dp)
                .border(
                    width = 1.dp,
                    color = if (isFocused) colorResource(R.color.primary_orange) else colorResource(
                        R.color.dark_color
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .onFocusChanged { isFocused = it.isFocused },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(6.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {

            },
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.primary_orange)
            )
        ) {
            Text(
                text = "Adicionar ao carrinho",
                color = Color.White
            )
        }
    }
}