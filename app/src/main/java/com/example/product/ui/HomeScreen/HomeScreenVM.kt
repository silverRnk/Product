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
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun onEvent(event: HomeScreenEvent){
        when(event){
            is HomeScreenEvent.OnSearchValueChange -> {
                searchBox = event.search
            }

        }
    }
}