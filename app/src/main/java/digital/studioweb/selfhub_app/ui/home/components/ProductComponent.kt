package digital.studioweb.selfhub_app.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import digital.studioweb.selfhub_app.R
import digital.studioweb.selfhub_app.data.models.Product
import digital.studioweb.selfhub_app.ui.utils.StringUtils.formatToBRLCurrency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductComponent(product: Product) {
    val openDialog = remember { mutableStateOf(false) }

    fun showModal() {
        openDialog.value = true
    }

    fun closeModal() {
        openDialog.value = false
    }

    if (openDialog.value) {
        Dialog(
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                decorFitsSystemWindows = false
            ),
            onDismissRequest = { closeModal() }
        ) {
            DialogContent(product)
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
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                )
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    product.name,
                    color = colorResource(R.color.dark_color),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
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
                            .size(24.dp)
                            .clickable {
                                showModal()
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(1.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DialogContent(product: Product) {
    Column(
        modifier = Modifier
            .width(600.dp)
            .fillMaxHeight()
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
    ) {
        LazyColumn {
            item {
                Image(
                    painter = painterResource(R.drawable.risoto),
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .height(350.dp)
                )
            }
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Spacer(modifier = Modifier.height(26.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Risoto de Tomatte",
                            fontSize = 24.sp,
                            color = Color.DarkGray
                        )
//                        ProductCount()
                    }
                    Text(
                        text = formatToBRLCurrency(49.99),
                        color = colorResource(R.color.dark_color),
                        fontSize = 22.sp,
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                    ProductDetailsComponent()
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        lineHeight = 18.sp,
                        text = "Risoto à Tomatto, uma verdadeira especialidade da casa.\nCoberto de tomates cereja ao molho de gorgonzola, com páprica picante no ponto certo. Acompanha um cordeiro, bife ao ponto, sem mais nem menos.\nExcelente escolha para quem quer um jantar sofisticado e leve.",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                }
            }

//            product.addItems?.let { addItems ->
//                item {
//                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
//                        Text(
//                            text = "ADICIONAIS",
//                            style = TextStyle(
//                                fontFamily = FontFamily(Font(R.font.dinnext_medium))
//                            ),
//                            color = colorResource(R.color.dark_color),
//                            fontSize = 18.sp
//                        )
//                        Spacer(modifier = Modifier.height(12.dp))
//                    }
//                }

//                items(addItems.size) { index ->
//                    val addItem = addItems[index]
//                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
//                        AddItemCheckComponent(addItem)
//                    }
//                }
        }
//
//            item {
//                var observation by remember { mutableStateOf("") }
//
//                Spacer(modifier = Modifier.height(24.dp))
//                Row(
//                    modifier = Modifier
//                        .padding(horizontal = 16.dp)
//                        .fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        Image(
//                            painter = painterResource(R.drawable.ic_comment),
//                            contentDescription = "",
//                            modifier = Modifier
//                                .size(14.dp)
//                        )
//                        Text(
//                            text = "Alguma observação?",
//                            color = colorResource(R.color.dark_color),
//                            fontSize = 14.sp,
//                            modifier = Modifier.padding(start = 8.dp)
//                        )
//                    }
//                    Text(
//                        text = "${observation.length}/140",
//                        color = colorResource(R.color.dark_color),
//                        fontSize = 14.sp,
//                    )
//                }
//                Spacer(modifier = Modifier.height(12.dp))
//                OutlinedTextField(
//                    value = observation,
//                    onValueChange = {
//                        if (it.length <= 140) observation = it
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp),
//                    placeholder = {
//                        Text("Ex: sem cebola, ponto da carne, etc.", fontSize = 14.sp)
//                    },
//                    maxLines = 4,
//                    singleLine = false,
//                    shape = RoundedCornerShape(8.dp),
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedBorderColor = Color.LightGray,
//                        unfocusedBorderColor = Color.LightGray,
//                        focusedTextColor = Color.DarkGray,
//                        unfocusedTextColor = Color.DarkGray,
//                        cursorColor = Color.Gray,
//                        focusedPlaceholderColor = Color.Gray,
//                        unfocusedPlaceholderColor = Color.Gray,
//                        disabledPlaceholderColor = Color.Gray,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent
//                    ),
//                    textStyle = TextStyle(fontSize = 14.sp)
//                )
//            }
//            item {
//                Spacer(modifier = Modifier.height(16.dp))
//                HorizontalDivider(
//                    color = colorResource(R.color.divider_color), thickness = 1.dp
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                Button(
//                    onClick = {
//
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp),
//                    shape = RoundedCornerShape(12.dp),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = colorResource(R.color.primary_orange)
//                    ),
//                    contentPadding = PaddingValues(vertical = 14.dp)
//                ) {
//                    Text(
//                        text = "Adicionar ao Carrinho  |  R$ 49,90",
//                        color = Color.White,
//                        fontSize = 16.sp
//                    )
//                }
//                Spacer(modifier = Modifier.height(32.dp))
//
//            }
    }
}
