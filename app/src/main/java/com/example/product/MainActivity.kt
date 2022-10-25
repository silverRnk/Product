package com.example.product

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.product.model.*
import com.example.product.ui.productScreen.productScreen
import com.example.product.ui.theme.ProductTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retService: ProductApi = ProductItemInterface
            .getRetrofitInstance()
            .create(ProductApi::class.java)


        setContent {
            ProductTheme {
                var response by remember {
                    mutableStateOf<Response<ProductItem>?>(null)
                }


                var product1 by remember {
                    mutableStateOf<ProductItem?>( ProductItem(
                     category = "",
                     description = "",
                     id = -1,
                    image = "",
                    price = 0.00,
                    rating = Rating( rate = -1.00, count = -1),
                    title = "this"
                ))}

                LaunchedEffect(key1 = 1){

                    launch(Dispatchers.IO) {
                        try {
                            response = retService.getProductItem(1)
                        }catch (e: Exception){
                            Log.d("Test_Message", e.message.toString())
                        }

                        Log.d("Test_Message", "Response: " + response.toString())
                        response?.let {
                            try {
                                Log.d("Test_Message", "ResponseBody: " + it.body().toString())
                                val product = it.body()
                                product1 = product
                                Log.d("Test_Message", product?.title + "1")
                                } catch (e: Exception){
                                Log.d("Test_Message", it.errorBody().toString())
                            }
                            Log.d("Test_Message", "${product1?.title}2")
                        }
                    }

                }

                productScreen(product = product1)




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
    ProductTheme {
        val product1 = ProductItem(
            id = 1,
            description = "!!!",
            title =  "!!!",
            price = 10.00,
            category = "!!!",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            rating = Rating( rate = 3.00, count = 10)

        )

        productScreen(product = product1)
    }
}