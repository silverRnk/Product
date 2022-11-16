package com.example.product.ui.ProductScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class ProductScreenVM @Inject constructor(
    productApi: ProductApi,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var product by mutableStateOf<Product?>(null)

    var searchBox by mutableStateOf("")
        private set

    init {


        var category = savedStateHandle.get<String?>("category")

        if(category != ""){
            viewModelScope.launch {
                val categoryResponse = productApi.getProductByCategory(category!!)
                product = categoryResponse.body()
                Log.d("TestTag", "1" + product.toString())
            }
        }else{
            viewModelScope.launch {
                val productResponse = productApi.getProducts()
                product = productResponse.body()
                Log.d("TestTag", "2" + product.toString())
            }


        }
    }

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ProductScreenEvent){
        when(event){
            is ProductScreenEvent.OnValueChange -> {
                searchBox = event.searchBox
            }
            is ProductScreenEvent.OnProductItemSelect -> {
                sendUiEvent(UiEvent.OnNavigate(Routes.ProductItemScreen +
                        "?productId=${event.productId}"))
            }
        }
    }

    fun sendUiEvent( uiEvent: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }
}