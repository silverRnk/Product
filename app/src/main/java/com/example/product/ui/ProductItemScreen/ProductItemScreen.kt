package com.example.product.ui.ProductItemScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProductItemScreen(
    viewModel: ProductItemViewModel = hiltViewModel()
){
    val productItem = viewModel.productItem

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 10.dp)) {

        Image(painter = rememberAsyncImagePainter(model = productItem?.image)
            , contentDescription = "Product Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(horizontal = 15.dp))

        BoxWithConstraints(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)) {
            Column(
                modifier = Modifier.fillMaxWidth()
                ,horizontalAlignment = Alignment.Start) {
                
                Row(modifier = Modifier.fillMaxWidth()
                    ,horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = productItem?.title ?: "",
                        fontSize = 20.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .fillMaxWidth(0.7f))


                    Text(text = "$ " + productItem?.price.toString(),
                    fontSize = 20.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 10.dp))
                }


                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = productItem?.rating?.rate?.toString() ?: " ",
                        fontSize = 10.sp,
                        color = Color.Gray
                    )
                    Text(text = "(" + (productItem?.rating?.count?.toString() ?: " ")  + ")",
                        fontSize = 10.sp,
                        color = Color.Gray)
                }
                
                Spacer(modifier = Modifier.height(20.dp))

                Text(text = productItem?.description ?: "",
                    fontSize = 15.sp)
            }

        }



    }

}