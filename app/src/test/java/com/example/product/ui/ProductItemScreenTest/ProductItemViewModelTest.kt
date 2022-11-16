package com.example.product.ui.ProductItemScreenTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.product.ProductObject
import com.example.product.model.FakeProductApi
import com.example.product.ui.ProductItemScreen.ProductItemViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductItemViewModelTest{

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    lateinit var viewModel: ProductItemViewModel

    @Before
    fun setup(){
        val savedStateHandle = SavedStateHandle().apply {
            set("productId", 1)
        }
        Dispatchers.setMain(mainThreadSurrogate)

        viewModel = ProductItemViewModel(FakeProductApi(), savedStateHandle)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `On init product Item is Product 1`() = runTest {
        launch(Dispatchers.Main){
            assertThat(viewModel.productItem).isEqualTo(ProductObject.product.find { productItem ->
                productItem.id == 1
            })

        }
    }
}