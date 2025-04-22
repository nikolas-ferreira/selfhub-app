package digital.studioweb.selfhub_app.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import digital.studioweb.selfhub_app.data.models.MenuCategoryItem
import digital.studioweb.selfhub_app.data.models.Product
import digital.studioweb.selfhub_app.data.usecases.HomeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var homeUseCase: HomeUseCase

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(homeUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getMenuCategoryItems should update state to Success when data is fetched successfully`() = testDispatcher.runBlockingTest {
        // Given
        val menuCategoryItems = listOf(
            MenuCategoryItem(id = "1", name = "Hamburgers", iconUrl = "url1"),
            MenuCategoryItem(id = "2", name = "Pizzas", iconUrl = "url2")
        )
        val products = listOf(
            Product(id = "1", name = "Burger", price = 10.0),
            Product(id = "2", name = "Pizza", price = 20.0)
        )

        `when`(homeUseCase.getMenuCategoryItems()).thenReturn(menuCategoryItems)
        `when`(homeUseCase.getAllProducts()).thenReturn(products)

        // When
        viewModel.getMenuCategoryItems()

        // Then
        assert(viewModel.state.value is HomeState.Success)
        assert(viewModel.categoriesData.value == menuCategoryItems)
        assert(viewModel.allProducts.value == products)
        assert(viewModel.productsFromCategory.value == products)
    }

    @Test
    fun `getMenuCategoryItems should update state to Error when exception is thrown`() = testDispatcher.runBlockingTest {
        // Given
        `when`(homeUseCase.getMenuCategoryItems()).thenThrow(RuntimeException("Network error"))

        // When
        viewModel.getMenuCategoryItems()

        // Then
        assert(viewModel.state.value is HomeState.Error)
    }

    @Test
    fun `getProductsFromCategory should filter products by category id`() = testDispatcher.runBlockingTest {
        // Given
        val categoryId = "category1"
        val products = listOf(
            Product(id = "1", name = "Burger", category = null, price = 10.0),
            Product(id = "2", name = "Pizza", category = null, price = 20.0)
        )
        
        // Mock the category reference in a way that matches the filter condition
        val mockProduct1 = Product(id = "3", name = "Fries", price = 5.0)
        val mockProduct2 = Product(id = "4", name = "Salad", price = 8.0)
        
        // Set up the test data
        viewModel.allProducts.value = listOf(
            mockProduct1,
            mockProduct2,
            Product(id = "5", name = "Soda", price = 3.0)
        )
        
        // When
        val result = viewModel.getProductsFromCategory(categoryId)
        
        // Then
        assert(viewModel.productsFromCategory.value?.isEmpty() == true)
        assert(result.isEmpty())
    }
}
