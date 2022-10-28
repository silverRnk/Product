package com.example.product

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.product.model.*
import com.example.product.ui.HomeScreen.HomeScreen
import com.example.product.ui.productScreen.productScreen
import com.example.product.ui.theme.ProductTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProductTheme {

                val _categories = listOf(
                    "Food",
                    "Clothing",
                    "Electronics"
                )

                var categories = Categories()
                categories.clear()
                categories.addAll(_categories.toTypedArray())

                HomeScreen(category = categories)

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val _categories = listOf(
        "Food",
        "Clothing",
        "Electronics"
    )

    var categories = Categories()
    categories.clear()
    categories.addAll(_categories.toTypedArray())

    HomeScreen(category = categories)

}