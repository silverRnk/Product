package com.example.product.ui.ProductItemScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.product.model.ProductItem
import com.example.product.model.repository.ProductApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductItemViewModel @Inject constructor(
    productApi: ProductApi,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var productItem by mutableStateOf<ProductItem?>(null)
        private set

    init {
        val productId = savedStateHandle.get<Int>("productId")!!
        if (productId != -1){
            viewModelScope.launch {
                val productItemResponse = productApi.getProductItem(productId)
                productItem = productItemResponse.body()
            }
        }
    }


}