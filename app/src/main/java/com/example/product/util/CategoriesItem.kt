package com.example.product.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.product.R

sealed class CategoriesItem(val categoryName: String,
                            @StringRes val headerId: Int,
                            @DrawableRes val iconId : Int){
    object Electronics: CategoriesItem("electronics",
        R.string.category1, R.drawable.icon_gamepad)
    object MensClothing: CategoriesItem("mens_clothing",
        R.string.category2, R.drawable.icon_hanger)
    object WomensClothing: CategoriesItem("women_clothing",
        R.string.category3, R.drawable.icon_hanger)
    object Jewelry: CategoriesItem("jewelry", R.string.category4,
        R.drawable.icon_diamond)

}
