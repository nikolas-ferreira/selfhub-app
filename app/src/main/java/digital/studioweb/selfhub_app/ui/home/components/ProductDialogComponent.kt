package digital.studioweb.selfhub_app.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import digital.studioweb.selfhub_app.R
import digital.studioweb.selfhub_app.data.models.CustomizationGroup
import digital.studioweb.selfhub_app.data.models.CustomizationOption
import digital.studioweb.selfhub_app.data.models.Product
import digital.studioweb.selfhub_app.ui.components.ObsEditTextComponent
import digital.studioweb.selfhub_app.ui.components.ProductCount
import digital.studioweb.selfhub_app.ui.utils.StringUtils.formatToBRLCurrency

@Composable
fun ProductDialogContent(product: Product) {
    Column(
        modifier = Modifier
            .width(600.dp)
            .fillMaxHeight()
            .padding(16.dp)
            .background(colorResource(R.color.app_background), RoundedCornerShape(16.dp))
    ) {
        LazyColumn {
            item {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
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
                            text = product.name,
                            fontSize = 24.sp,
                            color = colorResource(R.color.white_alabaster)
                        )
                        ProductCount()
                    }
                    Text(
                        text = formatToBRLCurrency(49.99),
                        color = Color.White,
                        fontSize = 22.sp,
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                    ProductDetailsComponent()
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        lineHeight = 18.sp,
                        text = product.description,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                }
            }


            for (group in product.customizationGroups) {
                item {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                            Text(
                                text = group.name,
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.dinnext_medium))
                                ),
                                color = colorResource(R.color.white_smoke),
                                modifier = Modifier.padding(top = 26.dp),
                                fontSize = 22.sp
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                    }
                    for (option in group.options) {
                        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                            AddItemCheckComponent(option)
                        }

                    }
                }
            }

            item {
                var observation by remember { mutableStateOf("") }

                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.ic_comment),
                            contentDescription = "",
                            modifier = Modifier
                                .size(14.dp)
                        )
                        Text(
                            text = "Alguma observação?",
                            color = colorResource(R.color.white_smoke),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    Text(
                        text = "${observation.length}/140",
                        color = colorResource(R.color.white_smoke),
                        fontSize = 14.sp,
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = observation,
                    onValueChange = {
                        if (it.length <= 140) observation = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    placeholder = {
                        Text("Ex: sem cebola, ponto da carne, etc.", fontSize = 14.sp)
                    },
                    maxLines = 4,
                    singleLine = false,
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.Gray,
                        focusedPlaceholderColor = Color.Gray,
                        unfocusedPlaceholderColor = Color.Gray,
                        disabledPlaceholderColor = Color.Gray,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    ),
                    textStyle = TextStyle(fontSize = 14.sp)
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(
                    color = colorResource(R.color.divider_color), thickness = 1.dp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.primary_orange)
                    ),
                    contentPadding = PaddingValues(vertical = 14.dp)
                ) {
                    Text(
                        text = "Adicionar ao Carrinho  |  R$ 49,90",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductDialogContentPreview() {
    val mockCustomizationGroups = listOf(
        CustomizationGroup(
            id = "group1",
            name = "Escolha o tamanho",
            minSelections = 1,
            maxSelections = 1,
            isRequired = true,
            options = listOf(
                CustomizationOption(id = "opt1", name = "Pequeno", additionalPrice = 0.0),
                CustomizationOption(id = "opt2", name = "Médio", additionalPrice = 5.0),
                CustomizationOption(id = "opt3", name = "Grande", additionalPrice = 10.0)
            )
        ),
        CustomizationGroup(
            id = "group2",
            name = "Adicionais",
            minSelections = 0,
            maxSelections = 3,
            isRequired = false,
            options = listOf(
                CustomizationOption(id = "opt4", name = "Queijo extra", additionalPrice = 4.0),
                CustomizationOption(id = "opt5", name = "Bacon", additionalPrice = 6.0),
                CustomizationOption(id = "opt6", name = "Molho especial", additionalPrice = 2.0)
            )
        )
    )

    val mockProduct = Product(
        id = "1",
        name = "Risoto de Tomate",
        price = 49.99,
        imageUrl = "https://images.unsplash.com/photo-1604908177522-c29fd8aa64d2",
        description = "Risoto à Tomatte com tomates cereja e molho de gorgonzola.",
        createdAt = "2024-01-01T00:00:00Z",
        updatedAt = "2024-01-01T00:00:00Z",
        categoryId = "cat01",
        createdById = "admin01",
        lastEditedById = "admin01",
        customizationGroups = mockCustomizationGroups
    )

    ProductDialogContent(product = mockProduct)
}
