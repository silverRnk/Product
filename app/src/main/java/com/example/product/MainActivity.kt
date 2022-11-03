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
import com.example.product.ui.HomeScreen.HomeScreen
import com.example.product.ui.ProductItemScreen.ProductItemScreen
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
                    composable(route = Routes.ProductScreen){
                        ProductScreen()
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


}