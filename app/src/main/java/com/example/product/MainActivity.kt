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
import com.example.product.ui.HomeScreen.HomeScreenVMFactory
import com.example.product.ui.productScreen.productScreen
import com.example.product.ui.theme.ProductTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import retrofit2.Response

class MainActivity : ComponentActivity() {
    val retService: ProductApi = ProductItemInterface
        .getRetrofitInstance()
        .create(ProductApi::class.java)

    lateinit var viewModelFactory: HomeScreenVMFactory

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

                    val job1 = launch(Dispatchers.IO) {
                        try {
                            productResponse = retService.getProducts(5)
                        }catch (e: Exception){
                            Log.d("Error", e.message.toString())
                        }
                    }

                    val job2 = launch(Dispatchers.IO) {
                        try {
                            categoriesResponse = retService.getCategories()
                        }catch (e: Exception){
                            Log.d("Error", e.message.toString())
                        }
                    }

                }

                viewModelFactory = HomeScreenVMFactory(productResponse, categoriesResponse)

                viewModel = ViewModelProvider(this@MainActivity, viewModelFactory).get(HomeScreenVM::class.java)

                viewModel.getProduct(productResponse)
                viewModel.getCategories(categoriesResponse)

                HomeScreen(viewModel = viewModel)

            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            val categories = retService.getCategories()
            Log.d("Test", "test2" + categories.message().toString())
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