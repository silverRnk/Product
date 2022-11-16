package com.example.product.ui.HomeScreen

sealed class HomeScreenEvent{

    data class OnSearchValueChange(val search: String): HomeScreenEvent()
    data class OnProductItemSelected(val productId: Int): HomeScreenEvent()
    data class OnSelectCategory(val category: String) : HomeScreenEvent()
    object OnViewAllProducts: HomeScreenEvent()

}
