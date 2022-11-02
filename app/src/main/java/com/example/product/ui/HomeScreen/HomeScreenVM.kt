package com.example.product.ui.HomeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.product.model.Categories
import com.example.product.model.Product
import com.example.product.model.repository.ProductApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenVM @Inject constructor(
    productApi: ProductApi
): ViewModel() {

    var product = Product()

    var categories = Categories()

    init {
        viewModelScope.launch {
            val productResponse = productApi.getProducts(5).body()
            productResponse?.let {
                product = it }

            val categoriesResponse = productApi.getCategories().body()
            categoriesResponse?.let {
                categories = it
            }

        }
    }

    var searchBox by mutableStateOf("")
        private set

    fun onEvent(event: HomeScreenEvent){
        when(event){
            is HomeScreenEvent.OnSearchValueChange -> {
                searchBox = event.search
            }

        }
    }
}