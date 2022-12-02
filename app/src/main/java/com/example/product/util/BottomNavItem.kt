package com.example.product.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.product.R

sealed class BottomNavItem(val navItem: String, @StringRes val headerId: Int, @DrawableRes val iconId: Int){
    object Home: BottomNavItem("Home", R.string.navItem1, R.drawable.icon_home)
    object Cart: BottomNavItem("Cart", R.string.navItem2, R.drawable.icon_cart)
    object User: BottomNavItem("User", R.string.navItem3, R.drawable.icon_profile)
}
