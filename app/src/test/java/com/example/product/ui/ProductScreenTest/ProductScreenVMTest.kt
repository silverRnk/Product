package com.example.product.ui.ProductScreenTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.example.product.ProductObject
import com.example.product.model.FakeProductApi
import com.example.product.model.ProductItem
import com.example.product.model.Rating
import com.example.product.ui.ProductScreen.ProductScreenEvent
import com.example.product.ui.ProductScreen.ProductScreenVM
import com.example.product.util.Routes
import com.example.product.util.Routes.ProductItemScreen
import com.example.product.util.UiEvent
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductScreenVMTest{


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: ProductScreenVM

    private val mainSingleThread = UnconfinedTestDispatcher()

    @Before
    fun setup(){
        Dispatchers.setMain(mainSingleThread)

    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        mainSingleThread.cancel()
    }

    @Test
    fun `On SaveStateHandle is not null return Product of category`() = runTest {
        val savedStateHandle = SavedStateHandle().apply {
            set("category", "electronics")
        }
        viewModel = ProductScreenVM(FakeProductApi(), savedStateHandle)
        launch(Dispatchers.Main) {
            var product = com.example.product.model.Product()
            product.clear()
            product.add(ProductItem(id = 1, price = 1.00, rating = Rating(1, 0.00), category = "electronics"))
            product.add(ProductItem(id = 5, price = 1.00, rating = Rating(1, 0.00), category = "electronics"))

            assertThat(viewModel.product).isEqualTo(product)
        }
    }

    @Test
    fun `On SaveStateHandle is null return all Product`() = runTest {
        val savedStateHandle = SavedStateHandle().apply {
            set("category", "")
        }
        viewModel = ProductScreenVM(FakeProductApi(), savedStateHandle)

        launch(Dispatchers.Main) {
            var product = com.example.product.model.Product()
            product.clear()
            product.addAll(ProductObject.product.toTypedArray())
            assertThat(viewModel.product).isEqualTo(product)
        }
    }

    @Test
    fun `OnValueChange event`() = runTest{
        val savedStateHandle = SavedStateHandle().apply {
            set("category", "")
        }
        viewModel = ProductScreenVM(FakeProductApi(), savedStateHandle)
        launch(Dispatchers.Main) {
            viewModel.onEvent(ProductScreenEvent.OnValueChange("Bag"))
            assertThat(viewModel.searchBox).isEqualTo("Bag")
        }

    }

    @Test
    fun `OnProductItemSelect event`() = runTest(UnconfinedTestDispatcher()){


        val routeCollector = MutableStateFlow<UiEvent?>(null)

        val savedStateHandle = SavedStateHandle().apply {
            set("category", "")
        }
        viewModel = ProductScreenVM(FakeProductApi(), savedStateHandle)

        viewModel.onEvent(ProductScreenEvent.OnProductItemSelect(1))

        viewModel.uiEvent.collect{
            routeCollector.value = it
        }


        assertThat(routeCollector).isEqualTo(UiEvent.OnNavigate(""))

    }
}