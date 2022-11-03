package com.example.product.ui.ProductScreen

sealed class ProductScreenEvent{

    data class OnProductItemSelect(val productId: Int): ProductScreenEvent()
    data class OnValueChange(val searchBox: String) : ProductScreenEvent()
}
