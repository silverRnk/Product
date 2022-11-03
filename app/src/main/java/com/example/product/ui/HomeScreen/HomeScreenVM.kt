package com.example.product.ui.HomeScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.product.model.Categories
import com.example.product.model.Product
import com.example.product.model.repository.ProductApi
import com.example.product.util.Routes
import com.example.product.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenVM @Inject constructor(
    productApi: ProductApi
): ViewModel() {

    var product by mutableStateOf(Product())
        private set

    var categories by mutableStateOf(Categories())
        private set

    init {
        Log.d("TestTag", "Test")
        Log.d("TestTag", productApi.toString())
        viewModelScope.launch {
            val productResponse = productApi.getProducts(5).body()
            Log.i("TestTag", productResponse.toString())
            productResponse?.let {
                product = it
            Log.i("TestTag", "Product" + product.toString())} }

        viewModelScope.launch {
            val categoriesResponse = productApi.getCategories().body()
            Log.i("TestTag", categoriesResponse.toString())
            categoriesResponse?.let {
                categories = it
                Log.i("TestTag", "Categories" + categories.toString())
            }
        }
    }

    var searchBox by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    var uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: HomeScreenEvent){
        when(event){
            is HomeScreenEvent.OnSearchValueChange -> {
                searchBox = event.search
            }
            is HomeScreenEvent.OnProductItemSelected -> {
                sendUiEvent(UiEvent.OnNavigate(Routes.ProductItemScreen
                        + "?productId = ${event.productId}"))
            }

        }
    }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }
}