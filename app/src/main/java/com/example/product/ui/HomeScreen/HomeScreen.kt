package com.example.product.ui.HomeScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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

    var basicTextField by remember {
        mutableStateOf("")
    }

    var onBasicTextChangeValue by remember {
        mutableStateOf(true)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(value = searchItem,
            onValueChange = { searchItem = it },
            placeholder = { Text(text = "Place",
                style = mainScreenTypography.h4) },
            textStyle = mainScreenTypography.h4,
            modifier = Modifier.clip(RoundedCornerShape(10.dp)))
        
        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier
            .wrapContentSize()
            .background(Color.Green)
            .clip(RoundedCornerShape(topEnd = 200.dp))
            .clickable { onBasicTextChangeValue = false }) {
            BasicTextField(value = basicTextField,
                onValueChange = {basicTextField = it
                                onBasicTextChangeValue = false},
                textStyle = mainScreenTypography.h4,
                modifier = Modifier
                    .padding(
                        horizontal = 30.dp,
                        vertical = 5.dp
                    )
                    .fillMaxWidth(0.9f)){
                if(onBasicTextChangeValue){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painter = painterResource(id = R.drawable.search_ic), contentDescription = "search",
                        modifier = Modifier.size(15.dp))
                        Text(text = "Place Text",
                            style = mainScreenTypography.h4)
                    }

                }else{
                    it()
                }

            }

        }

        Box(modifier = Modifier
            .padding(10.dp)
            .height(20.dp)
            .width(100.dp)
            .clip(RoundedCornerShape(50.dp)))

    }



}

@Composable
fun shopByCategories(
    categories: Categories
){

    var selectedItem by remember {
        mutableStateOf(0)
    }

    LazyRow(){
        items(categories.size){



        }

    }
}

@Composable
fun categoryItem(
    item: String,
    selected: Boolean,
    onSelect: () -> Unit
){

    Text(text = item,
    style = mainScreenTypography.h3,
    modifier = Modifier
        .height(25.dp)
        .background(if (selected) Color.Gray else Color.Blue)
        .clip(RoundedCornerShape(20.dp))
        .clickable { onSelect })
}