package com.example.product

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.product.model.*
import com.example.product.ui.HomeScreen.HomeScreen
import com.example.product.ui.HomeScreen.HomeScreenVM
import com.example.product.ui.productScreen.productScreen
import com.example.product.ui.theme.ProductTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import retrofit2.Response

class MainActivity : ComponentActivity() {
    val retService: ProductApi = ProductItemInterface
        .getRetrofitInstance()
        .create(ProductApi::class.java)

    lateinit var viewModel: HomeScreenVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProductTheme {

                var productResponse by remember {
                    mutableStateOf<Response<Product>?>(null)
                }

                var categoriesResponse by remember {
                    mutableStateOf<Response<Categories>?>(null)
                }

                LaunchedEffect(key1 = 1){

                    launch(Dispatchers.IO) {
                        try {
                            productResponse = retService.getProducts(5)
                            categoriesResponse = retService.getCategories()
                        }catch (e: Exception){
                            Log.d("Error", e.message.toString())
                        }

                        viewModel = ViewModelProvider(this@MainActivity)[HomeScreenVM::class.java]
                    }

                }

                HomeScreen(viewModel = viewModel)

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