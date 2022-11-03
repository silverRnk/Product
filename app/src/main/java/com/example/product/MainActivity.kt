package com.example.product

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.product.model.ProductItem
import com.example.product.model.Rating
import com.example.product.ui.HomeScreen.HomeScreen
import com.example.product.ui.ProductItemScreen.ProductItemScreen
import com.example.product.ui.ProductScreen.ProductItem
import com.example.product.ui.ProductScreen.ProductScreen
import com.example.product.util.Routes
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
                val navController = rememberNavController()

                NavHost(navController = navController,
                    startDestination = Routes.HomeScreen){
                    composable(route = Routes.HomeScreen){
                        HomeScreen(onNavigate = {
                            navController.navigate(it.route)
                        }) }
                    composable(route = Routes.ProductItemScreen + "?productId={productId}",
                    arguments = listOf(
                        navArgument(name = "productId"){
                            type = NavType.IntType
                            defaultValue = -1
                        }
                    )){
                        ProductItemScreen()
                    }
                    composable(route = Routes.ProductScreen + "?category={category}",
                    arguments = listOf(
                        navArgument(name = "category"){
                            type = NavType.StringType
                            defaultValue = "" })
                    ){
                        ProductScreen(onNavigate = {navController.navigate(it.route)})
                    }

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

    val item = ProductItem(
        id = -1,
        title = "Hello",
        price = 0.00,
        rating = Rating(count = -1, rate = 0.00)
    )
    ProductItem(productItem = item, OnEvent = {})

}