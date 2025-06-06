package digital.studioweb.selfhub_app.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import digital.studioweb.selfhub_app.R
import digital.studioweb.selfhub_app.data.models.Category
import digital.studioweb.selfhub_app.data.models.Product
import digital.studioweb.selfhub_app.ui.components.CartButton
import digital.studioweb.selfhub_app.ui.components.CartComponent
import digital.studioweb.selfhub_app.ui.home.components.DualDrawerLayout
import digital.studioweb.selfhub_app.ui.home.components.EditTextComponent
import digital.studioweb.selfhub_app.ui.home.components.MenuCategoryItemComponent
import digital.studioweb.selfhub_app.ui.home.components.ProductComponent
import digital.studioweb.selfhub_app.ui.home.components.SideBarComponent
import digital.studioweb.selfhub_app.ui.utils.StringUtils.formatCurrentDate
import digital.studioweb.selfhub_app.ui.utils.StringUtils.formatCurrentTime
import kotlinx.coroutines.delay

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    viewModel.getCategories()

    var showRightDrawer by remember { mutableStateOf(false) }

    DualDrawerLayout(
        showLeftDrawer = false,
        showRightDrawer = showRightDrawer,
        onCloseRightDrawer = { showRightDrawer = false },
        rightDrawerContent = {
            CartComponent(onClose = { showRightDrawer = false })
        },
        content = {
            when (viewModel.state.value) {
                is HomeState.Loading -> HomeLoadingContent()
                is HomeState.Error -> HomeErrorContent()
                is HomeState.Success -> HomeSuccessContent(
                    categories = viewModel.categoriesData.value ?: emptyList(),
                    allProducts = viewModel.allProducts.value ?: emptyList(),
                    onCartClick = { showRightDrawer = true }
                )
            }
        }
    )
}

@Composable
fun HomeLoadingContent() {
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
fun HomeErrorContent() {
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
fun HomeSuccessContent(
    categories: List<Category>,
    allProducts: List<Product>,
    onCartClick: () -> Unit
) {
    var selectedItemIndex by remember { mutableIntStateOf(-1) }
    val scrollState = rememberScrollState()
    var selectedSidebarIndex by remember { mutableIntStateOf(0) }
    var currentTime by remember { mutableStateOf(formatCurrentTime()) }

    var searchText by remember { mutableStateOf("") }
    var displayedProducts by remember { mutableStateOf(allProducts) }

    LaunchedEffect(Unit) {
        while(true) {
            currentTime = formatCurrentTime()
            delay(1000)
        }
    }

    fun updateDisplayedProducts() {
        displayedProducts = if (searchText.isBlank()) {
            if (selectedItemIndex == -1) allProducts
            else allProducts.filter { it.categoryId == categories.getOrNull(selectedItemIndex)?.id }
        } else {
            val filtered = allProducts.filter {
                it.name.contains(searchText, ignoreCase = true)
            }
            if (selectedItemIndex == -1) filtered
            else filtered.filter { it.categoryId == categories.getOrNull(selectedItemIndex)?.id }
        }
    }


    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.app_background))
    ) {
        SideBarComponent(
            selectedIndex = selectedSidebarIndex,
            onItemSelected = { index ->
                selectedSidebarIndex = index

                when (index) {
                    0 -> { // Home
                        selectedItemIndex = -1
                        displayedProducts = allProducts
                    }

                    1 -> {
                        // Lógica para pedidos
                    }

                    2 -> {
                        // Lógica para garçom
                    }

                    3 -> {
                        // Lógica para configurações
                    }

                    else -> {
                        // Caso padrão ou nada
                    }
                }
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
                            painter = painterResource(id = R.drawable.ic_calendar),
                            colorFilter = ColorFilter.tint(
                                colorResource(
                                    id = R.color.primary_orange
                                )
                            ),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(formatCurrentDate(), color = White, fontSize = 14.sp)
                    }
                    Row(
                        Modifier.padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_clock),
                            colorFilter = ColorFilter.tint(
                                colorResource(
                                    id = R.color.primary_orange
                                )
                            ),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))

                        Text(currentTime, color = White, fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(12.dp))
                    }
//                Row {
//                    CalendarComponent()
//                    Spacer(modifier = Modifier.width(8.dp))
//                    ClockComponent()
//                }
//
                }
                CartButton(onClick = onCartClick)

            }
            Spacer(Modifier.size(38.dp))
            EditTextComponent(
                value = searchText,
                onValueChange = {
                    searchText = it
                    updateDisplayedProducts()
                },
                placeholder = "Buscar produto por nome..."
            )

            Spacer(Modifier.size(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .horizontalScroll(scrollState)
            ) {
                MenuCategoryItemComponent(
                    isSelected = selectedItemIndex == -1,
                    menuCategoryName = "Todos Itens",
                    menuCategoryIcon = R.drawable.hamburguer,
                    onClick = {
                        selectedItemIndex = -1
                        updateDisplayedProducts()
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))

                categories.forEachIndexed { index, category ->
                    MenuCategoryItemComponent(
                        isSelected = selectedItemIndex == index,
                        menuCategoryName = category.name,
                        menuCategoryIcon = R.drawable.hamburguer,
                        onClick = {
                            selectedItemIndex = index
                            updateDisplayedProducts()
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 180.dp),
                contentPadding = PaddingValues(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(displayedProducts.size) { index ->
                    ProductComponent(product = displayedProducts[index])
                }
            }
        }
    }
}

@Preview(
    showBackground = true, widthDp = 1400,
    heightDp = 700
)
@Composable
fun HomeSuccessContentPreview() {
    val mockCategories = listOf(
        Category(
            "1",
            name = "Lanches",
            iconUrl = "",
            createdAt = "",
            updatedAt = "",
            restaurantId = "1",
            lastEditedById = ""
        ),
        Category(
            "2",
            name = "Bebidas",
            iconUrl = "",
            createdAt = "",
            updatedAt = "",
            restaurantId = "1",
            lastEditedById = ""
        )
    )

    val mockProducts = listOf(
        Product(
            "1",
            name = "X-Burger",
            price = 12.99,
            imageUrl = "",
            description = "Delicioso hambúrguer",
            createdAt = "",
            updatedAt = "",
            categoryId = "1",
            createdById = "",
            lastEditedById = "",
            customizationGroups = emptyList()
        ),
        Product(
            "2",
            name = "Coca-Cola",
            price = 6.50,
            imageUrl = "https://trxis.com.br/media/trxis%20fil%C3%A9.jpg",
            description = "Bebida gelada",
            createdAt = "",
            updatedAt = "",
            categoryId = "2",
            createdById = "",
            lastEditedById = "",
            customizationGroups = emptyList()
        )
    )

    HomeSuccessContent(
        categories = mockCategories,
        allProducts = mockProducts,
        onCartClick = {}
    )
}
