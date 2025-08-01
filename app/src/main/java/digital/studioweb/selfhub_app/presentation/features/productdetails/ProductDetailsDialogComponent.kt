package digital.studioweb.selfhub_app.presentation.features.productdetails

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import digital.studioweb.selfhub_app.R
import digital.studioweb.selfhub_app.domain.features.home.models.CustomizationGroupModel
import digital.studioweb.selfhub_app.domain.features.home.models.CustomizationOptionModel
import digital.studioweb.selfhub_app.domain.features.home.models.CartOrderItemModel
import digital.studioweb.selfhub_app.domain.features.home.models.ProductModel
import digital.studioweb.selfhub_app.presentation.features.home.HomeViewModel
import digital.studioweb.selfhub_app.presentation.features.home.models.HomeScreenEvent
import digital.studioweb.selfhub_app.presentation.features.productdetails.components.ProductCount
import digital.studioweb.selfhub_app.presentation.features.productdetails.components.AddItemCheckComponent
import digital.studioweb.selfhub_app.presentation.features.productdetails.components.ProductDetailsObservationInputComponent
import digital.studioweb.selfhub_app.presentation.features.productdetails.models.ProductDetailsEvent
import digital.studioweb.selfhub_app.presentation.features.productdetails.models.ProductDetailsUIState
import digital.studioweb.selfhub_app.presentation.utils.StringUtils.formatToBRLCurrency
import kotlinx.coroutines.launch
import java.util.UUID

@Composable
fun ProductDetailsDialogComponent(
    productModel: ProductModel
) {
    val viewModel: ProductDetailsViewModel = hiltViewModel()
    val homeViewModel: HomeViewModel = hiltViewModel()

    val uiState = viewModel.uiState

    LaunchedEffect(Unit) {
        viewModel.onEvent(ProductDetailsEvent.Init(productModel))
    }

    ProductDetailsDialogContent(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        homeViewModel = homeViewModel
    )
}

@Composable
private fun ProductDetailsDialogContent(
    uiState: ProductDetailsUIState,
    onEvent: (ProductDetailsEvent) -> Unit,
    homeViewModel: HomeViewModel? = null
) {
    val product = uiState.productModel ?: return

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
            .width(600.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.app_background))
    ) {
        LazyColumn {
            /**
             * Image
             */
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

            /**
             * Description
             */
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
                        ProductCount(
                            quantity = uiState.productQuantity,
                            onIncrement = { onEvent(ProductDetailsEvent.IncrementProductQuantity) },
                            onDecrement = { onEvent(ProductDetailsEvent.DecrementProductQuantity) }
                        )
                    }
                    Text(
                        text = formatToBRLCurrency(product.price),
                        color = Color.White,
                        fontSize = 22.sp,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        lineHeight = 18.sp,
                        text = product.description,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                }
            }

            /**
             * Customization Group
             */
            for (group in product.customizationGroupModels) {
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

                        /**
                         * Each option for one group
                         */
                        group.options.forEach { option ->
                            val quantity = uiState.optionQuantities[option.id] ?: 0
                            AddItemCheckComponent(
                                addItem = option,
                                quantity = quantity,
                                onIncrement = {
                                    onEvent(ProductDetailsEvent.IncrementOption(group, option.id))
                                },
                                onDecrement = {
                                    onEvent(ProductDetailsEvent.DecrementOption(group, option.id))
                                }
                            )
                        }

                        /**
                         * Missing group validation
                         */
                        if (uiState.groupValidations[group.id] == false) {
                            Text(
                                text = stringResource(
                                    R.string.product_detail_dialog_missing_group_validation,
                                    group.min.toString(),
                                    group.max.toString()
                                ),
                                color = colorResource(R.color.primary_orange),
                                fontSize = 12.sp,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }
                }
            }

            /**
             * Observations
             */
            item {
                ProductDetailsObservationInputComponent(
                    observation = uiState.observation,
                    onObservationChange = { onEvent(ProductDetailsEvent.ChangeObservation(it)) }
                )
            }

            /**
             * Button
             */
            item {
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(
                    color = colorResource(R.color.divider_color), thickness = 0.5.dp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (uiState.isAllValid) {
                            val product = uiState.productModel
                            val cartOrderItemModel = CartOrderItemModel(
                                orderItemId = UUID.randomUUID().toString(),
                                productId = product.id,
                                name = product.name,
                                price = product.price,
                                imageUrl = product.imageUrl,
                                observation = uiState.observation,
                                customizationOptions = uiState.selectedCustomizations,
                                quantity = uiState.productQuantity
                            )
                            homeViewModel!!.onEvent(HomeScreenEvent.AddToCart(cartOrderItemModel))
                            onEvent(ProductDetailsEvent.CloseDialog)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (uiState.isAllValid)
                            colorResource(R.color.primary_orange)
                        else
                            colorResource(R.color.side_bar_icon_unselected_text_color)
                    ),
                    contentPadding = PaddingValues(vertical = 14.dp)
                ) {
                    Text(
                        text = "Adicionar ao Carrinho  |  ${formatToBRLCurrency(uiState.finalPrice)}",
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
private fun ProductDetailsDialogComponentPreview() {
    val mockCustomizationGroupModels = listOf(
        CustomizationGroupModel(
            id = "group1",
            name = "Escolha o tamanho",
            min = 1,
            max = 1,
            isRequired = true,
            options = listOf(
                CustomizationOptionModel(id = "opt1", name = "Pequeno", additionalPrice = 0.0),
                CustomizationOptionModel(id = "opt2", name = "Médio", additionalPrice = 5.0),
                CustomizationOptionModel(id = "opt3", name = "Grande", additionalPrice = 10.0)
            )
        ),
        CustomizationGroupModel(
            id = "group2",
            name = "Adicionais",
            min = 0,
            max = 3,
            isRequired = false,
            options = listOf(
                CustomizationOptionModel(id = "opt4", name = "Queijo extra", additionalPrice = 4.0),
                CustomizationOptionModel(id = "opt5", name = "Bacon", additionalPrice = 6.0),
                CustomizationOptionModel(
                    id = "opt6",
                    name = "Molho especial",
                    additionalPrice = 2.0
                )
            )
        )
    )

    val mockProductModel = ProductModel(
        id = "1",
        name = "Risoto de Tomate",
        price = 49.99,
        imageUrl = "https://images.unsplash.com/photo-1604908177522-c29fd8aa64d2",
        description = "Risoto à Tomate com tomates cereja e molho de gorgonzola.",
        createdAt = "2024-01-01T00:00:00Z",
        updatedAt = "2024-01-01T00:00:00Z",
        categoryId = "cat01",
        createdById = "admin01",
        lastEditedById = "admin01",
        customizationGroupModels = mockCustomizationGroupModels
    )

    val previewState = remember {
        mutableStateOf(ProductDetailsUIState(productModel = mockProductModel))
    }

    ProductDetailsDialogContent(
        uiState = previewState.value,
        onEvent = {}
    )
}
