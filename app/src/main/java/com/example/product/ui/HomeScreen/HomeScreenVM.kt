package com.example.product.ui.HomeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.product.model.Categories
import com.example.product.model.Product
import retrofit2.Response

class HomeScreenVM(
    products: Response<Product>?,
    categories: Response<Categories>?
): ViewModel() {

    val product = products?.body()

    val categories = categories?.body()

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