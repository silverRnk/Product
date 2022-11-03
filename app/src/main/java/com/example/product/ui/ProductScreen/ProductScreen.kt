package com.example.product.ui.ProductScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.product.ui.theme.mainScreenTypography

@Composable
fun ProductScreen(){
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "Product Screen"
        , style = mainScreenTypography.h3)
    }
}