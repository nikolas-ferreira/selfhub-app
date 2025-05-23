package digital.studioweb.selfhub_app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import digital.studioweb.selfhub_app.R
import digital.studioweb.selfhub_app.ui.components.CalendarComponent
import digital.studioweb.selfhub_app.ui.components.CartButton
import digital.studioweb.selfhub_app.ui.components.CartComponent
import digital.studioweb.selfhub_app.ui.components.ClockComponent
import digital.studioweb.selfhub_app.ui.components.RoundedSmallShapeComponent
import digital.studioweb.selfhub_app.ui.home.components.MenuCategoryItemComponent
import digital.studioweb.selfhub_app.ui.home.components.ProductComponent
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.getMenuCategoryItems()
    }
    var selectedItemIndex by remember { mutableIntStateOf(-1) }

    val scrollState = rememberScrollState()
    val leftDrawerState = rememberDrawerState(DrawerValue.Closed)
    val rightDrawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = leftDrawerState,
        drawerContent = {
            Surface(
                modifier = Modifier
                    .width(300.dp)
                    .fillMaxHeight(),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Menu",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontFamily = FontFamily.Serif
                        ),
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    HorizontalDivider(
                        color = colorResource(R.color.divider_color),
                        thickness = 2.dp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // Menu items can be added here
                }
            }
        },
        gesturesEnabled = true,
        scrimColor = Color.Black.copy(alpha = 0.3f),
        content = {
            // Nest the right drawer here
            ModalNavigationDrawer(
                drawerState = rightDrawerState,
                gesturesEnabled = true,
                scrimColor = Color.Black.copy(alpha = 0.3f),
                drawerContent = {
                    Surface(
                        modifier = Modifier
                            .width(300.dp)
                            .fillMaxHeight(),
                        color = Color.White
                    ) {
                        CartComponent(
                            onClose = {
                                coroutineScope.launch {
                                    rightDrawerState.close()
                                }
                            }
                        )
                    }
                },
                content = {
                    when (viewModel.state.value) {
                        is HomeState.Loading -> {
                            Text("Loading")
                        }

                        is HomeState.Success -> {
                            val categories = viewModel.categoriesData.value
                            var products = viewModel.productsFromCategory.value

                            Surface(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(colorResource(id = R.color.app_background))
                                        .padding(24.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Row {
                                            RoundedSmallShapeComponent(
                                                icon = R.drawable.ic_hamburger_menu,
                                                onClick = {
                                                    coroutineScope.launch {
                                                        leftDrawerState.open()
                                                    }
                                                }
                                            )
                                            Spacer(modifier = Modifier.width(24.dp))
                                            CalendarComponent()
                                            Spacer(modifier = Modifier.width(8.dp))
                                            ClockComponent()
                                        }

                                        CartButton(
                                            onClick = {
                                                coroutineScope.launch {
                                                    rightDrawerState.open()
                                                }
                                            }
                                        )
                                    }

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 24.dp)
                                            .horizontalScroll(scrollState),
                                    ) {
                                        MenuCategoryItemComponent(
                                            isSelected = selectedItemIndex == -1,
                                            menuCategoryName = "Todos Itens",
                                            menuCategoryIcon = R.drawable.ic_menu_category,
                                            onClick = {
                                                products = viewModel.allProducts.value
                                                selectedItemIndex = -1
                                            }
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        categories?.forEachIndexed { index, category ->
                                            MenuCategoryItemComponent(
                                                isSelected = selectedItemIndex == index,
                                                menuCategoryName = category.name,
                                                menuCategoryIcon = R.drawable.ic_menu_category,
                                                onClick = {
                                                    products =
                                                        viewModel.getProductsFromCategory(category.id)
                                                    selectedItemIndex = index
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
                                        products?.let { products ->
                                            items(products.size) { index ->
                                                ProductComponent(product = products[index])
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        is HomeState.Error -> {
                            Text("Error")
                        }
                    }
                })

        }
    )
}