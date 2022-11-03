package com.example.product.util

sealed class UiEvent{

    object OnPopBackStack: UiEvent()
    data class OnNavigate(val route: String) : UiEvent()
}
