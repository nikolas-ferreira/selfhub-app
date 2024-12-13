package digital.studioweb.selfhub_app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DrawerValue
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import digital.studioweb.selfhub_app.R
import digital.studioweb.selfhub_app.ui.components.CalendarComponent
import digital.studioweb.selfhub_app.ui.components.CallWaiterComponent
import digital.studioweb.selfhub_app.ui.components.CartComponent
import digital.studioweb.selfhub_app.ui.components.ClockComponent
import digital.studioweb.selfhub_app.ui.components.RoundedSmallShapeComponent
import digital.studioweb.selfhub_app.ui.home.components.MenuCategoryItemComponent
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.getMenuCategoryItems()
    }
    var selectedItemIndex by remember { mutableIntStateOf(-1) }

    val scrollState = rememberScrollState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
//            CartComponent()
        },
        content = {
            when (viewModel.state.value) {
                is HomeState.Loading -> {
                    Text("Loading")
                }

                is HomeState.Success -> {
                    val categories = viewModel.categoriesData.value
                    val products = viewModel.allProducts.value

                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .systemBarsPadding()
                            .background(colorResource(id = R.color.app_background))
                    ) {
                        Column(
                            Modifier.padding(24.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Row {
                                    RoundedSmallShapeComponent(
                                        icon = R.drawable.ic_cart,
                                        onClick = {
                                            coroutineScope.launch {
                                                drawerState.open()
                                            }
                                        }
                                    )
                                    Spacer(modifier = Modifier.width(24.dp))
                                    CalendarComponent()
                                    Spacer(modifier = Modifier.width(8.dp))
                                    ClockComponent()
                                }

                                CallWaiterComponent()
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
                                    menuCategoryCount = products?.size ?: 0,
                                    menuCategoryIcon = R.drawable.ic_menu_category,
                                    onClick = {
                                        selectedItemIndex = -1
                                    }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                categories?.forEachIndexed { index, category ->
                                    MenuCategoryItemComponent(
                                        isSelected = selectedItemIndex == index,
                                        menuCategoryName = category.name,
                                        menuCategoryCount = viewModel.getCategoryProductsCount(
                                            category
                                        ),
                                        menuCategoryIcon = R.drawable.ic_menu_category,
                                        onClick = {
                                            selectedItemIndex = index
                                        }
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                }
                            }
                            Spacer(modifier = Modifier.height(28.dp))

                        }
                    }
                }

                is HomeState.Error -> {
                    Text("Error")
                }
            }

        }
    )
}

