package com.example.product.ui.HomeScreenTest

import android.util.Log
import com.example.product.ProductObject
import com.example.product.model.Categories
import com.example.product.model.FakeProductApi
import com.example.product.model.Product
import com.example.product.ui.HomeScreen.Categories
import com.example.product.ui.HomeScreen.HomeScreenVM
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@ExperimentalCoroutinesApi
internal class HomeScreenVMTest{

    lateinit var viewModel: HomeScreenVM

    private val mainSingleThread = newSingleThreadContext("VM thread")

    @Before
    fun setup(){
        Dispatchers.setMain(mainSingleThread)
        viewModel = HomeScreenVM(FakeProductApi())

    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        mainSingleThread.close()
    }

    @Test
    fun `On Init`() = runTest{
        val product = Product()
        product.clear()
        product.addAll(ProductObject.product.subList(0, 5))

        val categories = Categories()
        categories.clear()
        categories.addAll(ProductObject.categories)

        assertThat(viewModel.product).isEqualTo(product)
        assertThat(viewModel.categories).isEqualTo(categories)
    }
}