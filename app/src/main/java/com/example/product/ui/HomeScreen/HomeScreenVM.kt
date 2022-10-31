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

    var product = products?.body()

    var categories = categories?.body()

    var searchBox by mutableStateOf("")
        private set

    fun onEvent(event: HomeScreenEvent){
        when(event){
            is HomeScreenEvent.OnSearchValueChange -> {
                searchBox = event.search
            }

        }
    }

    fun getProduct(products: Response<Product>?){
        this.product = products?.body()
    }

    fun getCategories(categories: Response<Categories>?){
        this.categories = categories?.body()
    }

}