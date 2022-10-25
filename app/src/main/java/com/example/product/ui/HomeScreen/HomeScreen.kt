package com.example.product.ui.HomeScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.product.R
import com.example.product.model.Categories
import com.example.product.ui.theme.mainScreenTypography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    category: Categories
){


    var searchItem by remember { mutableStateOf("")}

    Box(modifier = Modifier.fillMaxWidth(0.8f)) {


        TextField(value = searchItem,
            onValueChange = {

            },
        leadingIcon =
        Icon(painter = painterResource(id = R.drawable.search_ic),
            contentDescription = "search",
            tint = Color.Gray),
        )

        LazyVerticalGrid(cells = Gri) {
            items(category.size){

            }
        }
    }

}

@Composable
fun categoryItem(
    item: String,
    selected: Boolean
){

    Text(text = item,
    style = mainScreenTypography.h3,
    modifier = Modifier.height(25.dp)
        .background( if(selected) Color.Gray else Color.Blue)
        .clip(RoundedCornerShape(20.dp))
        .clickable {  })
}