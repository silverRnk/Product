package com.example.product.ui.HomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.product.model.Categories
import com.example.product.model.Product
import retrofit2.Response

class HomeScreenVMFactory(
    val products: Response<Product>?,
    val categories: Response<Categories>?
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeScreenVM::class.java)){
            return HomeScreenVM(
                products = products,
                categories = categories) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}