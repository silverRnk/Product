package com.example.product.ui.HomeScreen

sealed class HomeScreenEvent{

    data class OnSearchValueChange(val search: String): HomeScreenEvent()

}
