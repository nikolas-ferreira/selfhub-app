package digital.studioweb.selfhub_app.presentation.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import digital.studioweb.selfhub_app.R
import digital.studioweb.selfhub_app.domain.features.home.models.CategoryModel
import digital.studioweb.selfhub_app.domain.features.home.models.ProductModel
import digital.studioweb.selfhub_app.presentation.features.cart.CartComponent
import digital.studioweb.selfhub_app.presentation.features.home.components.HomeCategoryItemComponent
import digital.studioweb.selfhub_app.presentation.features.home.components.HomeCategoryShimmerComponent
import digital.studioweb.selfhub_app.presentation.features.home.components.HomeDualDrawerLayout
import digital.studioweb.selfhub_app.presentation.features.home.components.HomeEditTextComponent
import digital.studioweb.selfhub_app.presentation.features.home.components.HomeOpenCartButton
import digital.studioweb.selfhub_app.presentation.features.home.components.HomeProductComponent
import digital.studioweb.selfhub_app.presentation.features.home.components.HomeProductShimmerComponent
import digital.studioweb.selfhub_app.presentation.features.home.components.HomeSideBarComponent
import digital.studioweb.selfhub_app.presentation.features.home.models.HomeScreenEvent
import digital.studioweb.selfhub_app.presentation.features.home.models.HomeUIState
import digital.studioweb.selfhub_app.presentation.features.productdetails.ProductDetailsDialogComponent
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    HomeScreenContent(
        uiState = viewModel.uiState,
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
    val shimmerProducts = List(8) {
        ProductModel(
            id = "$it",
            name = "",
            price = 0.0,
            imageUrl = "",
            description = "",
            createdAt = "",
            updatedAt = "",
            categoryId = "",
            createdById = "",
            lastEditedById = "",
            customizationGroupModels = emptyList()
        )
    }

    val shimmerCategories = List(5) {
        CategoryModel(
            id = "$it",
            name = "",
            iconUrl = "",
            createdAt = "",
            updatedAt = "",
            restaurantId = "",
            lastEditedById = ""
        )
    }

    val uiState = HomeUIState(
        categories = shimmerCategories,
        productModels = shimmerProducts,
        displayedProducts = shimmerProducts,
        selectedCategoryIndex = -1,
        selectedSidebarIndex = 0,
        searchText = "",
        currentTime = "",
        isLoading = true,
        isSuccess = false,
        hasError = false
    )

    HomeSuccessContent(
        uiState = uiState,
        onEvent = {},
        onCartClick = {}
    )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeSuccessContent(
    uiState: HomeUIState,
    onEvent: (HomeScreenEvent) -> Unit,
    onCartClick: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackBarHostState.showSnackbar(message)
            }
        }
    }

    if (uiState.showDialog && uiState.selectedProduct != null) {
        val product = uiState.selectedProduct

        key(product.id) {
            Dialog(
                onDismissRequest = { onEvent(HomeScreenEvent.CloseDialog) },
                properties = DialogProperties(
                    usePlatformDefaultWidth = false,
                    decorFitsSystemWindows = false
                )
            ) {
                ProductDetailsDialogComponent(productModel = product)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.app_background))
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            HomeSideBarComponent(
                selectedIndex = uiState.selectedSidebarIndex,
                onItemSelected = { index ->
                    onEvent(HomeScreenEvent.OnSidebarItemSelected(index))
                }
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
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
                Spacer(modifier = Modifier.height(24.dp))
                HomeEditTextComponent(
                    value = uiState.searchText,
                    onValueChange = {
                        onEvent(HomeScreenEvent.OnSearchTextChanged(it))
                    },
                    placeholder = stringResource(R.string.home_search_edit_text_placeholder)
                )

                val state = rememberPullToRefreshState()

                PullToRefreshBox(
                    isRefreshing = uiState.isLoading,
                    onRefresh = { onEvent(HomeScreenEvent.OnRefreshRequested) },
                    state = state,
                    indicator = {
                        Indicator(
                            modifier = Modifier.align(Alignment.TopCenter),
                            containerColor = colorResource(R.color.product_component_card_background),
                            color = colorResource(R.color.primary_orange),
                            state = state,
                            isRefreshing = uiState.isLoading
                        )
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState())
                        ) {
                            if (uiState.isLoading) {
                                repeat(6) {
                                    HomeCategoryShimmerComponent()
                                    Spacer(modifier = Modifier.width(12.dp))
                                }
                            } else {
                                HomeCategoryItemComponent(
                                    isSelected = uiState.selectedCategoryIndex == -1,
                                    menuCategoryName = stringResource(R.string.home_category_list_all_items),
                                    menuCategoryIcon = R.drawable.hamburguer,
                                    onClick = {
                                        onEvent(HomeScreenEvent.OnCategorySelected(-1))
                                    }
                                )
                                Spacer(modifier = Modifier.width(12.dp))
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
                        }

                        Spacer(modifier = Modifier.height(36.dp))

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(4),
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 0.dp, max = 1200.dp),
                            verticalArrangement = Arrangement.spacedBy(26.dp),
                            horizontalArrangement = Arrangement.spacedBy(42.dp),
                            contentPadding = PaddingValues(bottom = 24.dp)
                        ) {
                            items(uiState.displayedProducts.size) { index ->
                                if (uiState.isLoading) {
                                    HomeProductShimmerComponent()
                                } else {
                                    val product = uiState.displayedProducts[index]
                                    HomeProductComponent(
                                        product = product,
                                        uiState = uiState,
                                        onEvent = onEvent
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            snackbar = { data ->
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = colorResource(id = R.color.success_green))
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Sucesso",
                        tint = White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = data.visuals.message,
                        color = White,
                        fontSize = 14.sp
                    )
                }
            }
        )
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
