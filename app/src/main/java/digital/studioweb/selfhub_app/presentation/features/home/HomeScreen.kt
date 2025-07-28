package digital.studioweb.selfhub_app.presentation.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import digital.studioweb.selfhub_app.R
import digital.studioweb.selfhub_app.domain.features.home.models.CartOrderItemModel
import digital.studioweb.selfhub_app.domain.features.home.models.CartOrderModel
import digital.studioweb.selfhub_app.domain.features.home.models.CategoryModel
import digital.studioweb.selfhub_app.domain.features.home.models.CustomizationOptionModel
import digital.studioweb.selfhub_app.domain.features.home.models.ProductModel
import digital.studioweb.selfhub_app.domain.features.models.PaymentMethod
import digital.studioweb.selfhub_app.presentation.features.home.components.HomeOpenCartButton
import digital.studioweb.selfhub_app.presentation.features.cart.CartComponent
import digital.studioweb.selfhub_app.presentation.features.home.components.*
import digital.studioweb.selfhub_app.presentation.features.home.models.*
import digital.studioweb.selfhub_app.presentation.features.home.components.HomeProductComponent

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    val uiState = viewModel.uiState

    HomeScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun HomeScreenContent(
    uiState: HomeUIState,
    onEvent: (HomeScreenEvent) -> Unit
) {
    var showRightDrawer by remember { mutableStateOf(false) }

    HomeDualDrawerLayout(
        showLeftDrawer = false,
        showRightDrawer = showRightDrawer,
        onCloseRightDrawer = { showRightDrawer = false },
        rightDrawerContent = {
            CartComponent()
        },
        content = {
            when {
                uiState.isLoading -> HomeLoadingContent()
                uiState.hasError -> HomeErrorContent()
                uiState.isSuccess -> HomeSuccessContent(
                    uiState = uiState,
                    onEvent = onEvent,
                    onCartClick = { showRightDrawer = true }
                )
            }
        }
    )
}

@Composable
private fun HomeLoadingContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        contentAlignment = Alignment.Center
    ) {
        Text("Loading...")
    }
}

@Composable
private fun HomeErrorContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        contentAlignment = Alignment.Center
    ) {
        Text("Erro ao carregar dados")
    }
}

@Composable
private fun HomeSuccessContent(
    uiState: HomeUIState,
    onEvent: (HomeScreenEvent) -> Unit,
    onCartClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.app_background))
    ) {
        HomeSideBarComponent(
            selectedIndex = uiState.selectedSidebarIndex,
            onItemSelected = { index ->
                onEvent(HomeScreenEvent.OnSidebarItemSelected(index))
            }
        )

        Column(
            modifier = Modifier
                .padding(start = 36.dp, top = 24.dp, end = 24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text("SelfHub Restaurant", color = White, fontSize = 22.sp)
                    Row(
                        Modifier.padding(top = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_clock),
                            colorFilter = ColorFilter.tint(
                                colorResource(id = R.color.primary_orange)
                            ),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(uiState.currentTime.take(10), color = White, fontSize = 14.sp)
                    }
                }
                HomeOpenCartButton(onClick = onCartClick)
            }

            Spacer(Modifier.size(38.dp))

            HomeEditTextComponent(
                value = uiState.searchText,
                onValueChange = {
                    onEvent(HomeScreenEvent.OnSearchTextChanged(it))
                },
                placeholder = stringResource(R.string.home_search_edit_text_placeholder)
            )

            Spacer(Modifier.size(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .horizontalScroll(scrollState)
            ) {
                HomeCategoryItemComponent(
                    isSelected = uiState.selectedCategoryIndex == -1,
                    menuCategoryName = stringResource(R.string.home_category_list_all_items),
                    menuCategoryIcon = R.drawable.hamburguer,
                    onClick = {
                        onEvent(HomeScreenEvent.OnCategorySelected(-1))
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))

                uiState.categories.forEachIndexed { index, category ->
                    HomeCategoryItemComponent(
                        isSelected = uiState.selectedCategoryIndex == index,
                        menuCategoryName = category.name,
                        menuCategoryIcon = R.drawable.hamburguer,
                        onClick = {
                            onEvent(HomeScreenEvent.OnCategorySelected(index))
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(26.dp),
                horizontalArrangement = Arrangement.spacedBy(42.dp)
            ) {
                items(uiState.displayedProducts.size) { index ->
                    HomeProductComponent(productModel = uiState.displayedProducts[index])
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = "spec:parent=medium_tablet,orientation=landscape"
)
@Composable
private fun HomeSuccessContentPreview() {
    val mockCategories = listOf(
        CategoryModel(
            id = "1",
            name = "Lanches",
            iconUrl = "",
            createdAt = "",
            updatedAt = "",
            restaurantId = "1",
            lastEditedById = ""
        ),
        CategoryModel(
            id = "2",
            name = "Bebidas",
            iconUrl = "",
            createdAt = "",
            updatedAt = "",
            restaurantId = "1",
            lastEditedById = ""
        )
    )

    val mockProducts = listOf(
        ProductModel(
            id = "1",
            name = "X-Burger",
            price = 12.99,
            imageUrl = "",
            description = "Delicioso hambúrguer",
            createdAt = "",
            updatedAt = "",
            categoryId = "1",
            createdById = "",
            lastEditedById = "",
            customizationGroupModels = emptyList()
        ),
        ProductModel(
            id = "2",
            name = "Coca-Cola",
            price = 6.50,
            imageUrl = "https://trxis.com.br/media/trxis%20fil%C3%A9.jpg",
            description = "Bebida gelada",
            createdAt = "",
            updatedAt = "",
            categoryId = "2",
            createdById = "",
            lastEditedById = "",
            customizationGroupModels = emptyList()
        )
    )

    val uiState = HomeUIState(
        categories = mockCategories,
        productModels = mockProducts,
        displayedProducts = mockProducts,
        selectedCategoryIndex = -1,
        selectedSidebarIndex = 0,
        searchText = "",
        currentTime = "2025-07-25 14:35",
        isLoading = false,
        isSuccess = true,
        hasError = false
    )

    HomeSuccessContent(
        uiState = uiState,
        onEvent = {},
        onCartClick = {}
    )
}
