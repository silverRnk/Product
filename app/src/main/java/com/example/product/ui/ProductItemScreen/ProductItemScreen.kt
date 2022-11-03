package com.example.product.ui.ProductItemScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun productScreen(
){
    Box(modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center){
        Text(text = "Hello")
    }


    /*Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 10.dp)) {

        Image(painter = if(product?.image != null) rememberAsyncImagePainter(model = product?.image) else painterResource(
            id = R.drawable.ic_action_name
        )

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
                    Text(text = product?.title ?: "",
                        fontSize = 20.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .fillMaxWidth(0.7f))

                    
                    Text(text = "$ " + product?.price?.toString(),
                    fontSize = 20.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 10.dp))
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = product?.rating?.rate.toString(),
                        fontSize = 10.sp,
                        color = Color.Gray
                    )
                    Text(text = "(" + product?.rating?.count.toString() + ")",
                        fontSize = 10.sp,
                        color = Color.Gray)
                }
                
                Spacer(modifier = Modifier.height(20.dp))

                Text(text = product?.description ?: "",
                    fontSize = 15.sp)
            }

        }



    }*/

}