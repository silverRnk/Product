@file:OptIn(ExperimentalFoundationApi::class)

package com.example.product.ui.HomeScreen


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.product.R
import com.example.product.model.Categories
import com.example.product.model.Product
import com.example.product.model.ProductItem
import com.example.product.ui.theme.White1
import com.example.product.ui.theme.cardPlaceHolder
import com.example.product.ui.theme.mainScreenTypography
import com.example.product.util.CategoriesItem
import com.example.product.util.UiEvent

@OptIn(ExperimentalComposeApi::class)
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    onNavigate: (UiEvent.OnNavigate) -> Unit,
    viewModel: HomeScreenVM = hiltViewModel()
){

    LaunchedEffect(key1 = 1){
        viewModel.uiEvent.collect { event ->
            when(event){
                is UiEvent.OnNavigate -> {onNavigate(event) }
                else -> Unit
            }

        }
    }



    Column(modifier = Modifier.fillMaxSize()) {


        BoxWithConstraints(modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 20.dp,
                bottom = 10.dp
            )
            .wrapContentHeight(),
            contentAlignment = Alignment.Center){
            SearchBox(search = viewModel.searchBox,
                OnEvent = { viewModel.onEvent(it) })
        }

        Deals(viewModel = viewModel)

        CategorySelection(categories = viewModel.categories,
            OnClick = {viewModel.onEvent(it)})

    }
}


@Composable
fun SearchBox(
    search: String,
    OnEvent: (HomeScreenEvent) -> Unit
){

    TextField(value = search
        , onValueChange = {
            OnEvent(HomeScreenEvent.OnSearchValueChange(it))
        },
    leadingIcon = { Icon(painter = painterResource(id = R.drawable.search_20),
        contentDescription = "search",
        tint = Color.Gray,
        modifier = Modifier.size(20.dp)) },
        modifier = Modifier)
}

@ExperimentalMaterialApi
@Composable
fun Deals(
    viewModel: HomeScreenVM,
){

    val product = viewModel.product

    Column(horizontalAlignment = Alignment.Start) {
        Text(text = "Deals"
        , style =  mainScreenTypography.h2,
        modifier = Modifier.padding(bottom = 5.dp))

            LazyRow{

                if(viewModel.product.isNullOrEmpty()){
                    items(5){
                        Spacer(modifier = Modifier.width(5.dp))
                        productItemPlaceHolder()
                    }
                }else{
                    items(product){ item ->
                        Spacer(modifier = Modifier.width(5.dp))
                        productItem(productItem = item,
                            OnClick = {viewModel.onEvent(it)})
                    }
                }
                
                item { 
                    Spacer(modifier = Modifier.width(5.dp))
                }

            }
    }
}

@ExperimentalMaterialApi
@Composable
fun productItem(
    productItem: ProductItem,
    OnClick: (HomeScreenEvent) -> Unit
){
    Card(onClick = { OnClick(HomeScreenEvent.OnProductItemSelected(productItem.id))},
    shape = RoundedCornerShape(15.dp),
        backgroundColor = White1,
        modifier = Modifier
            .height(250.dp)
            .width(175.dp)
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            Image(painter = rememberAsyncImagePainter(model = productItem.image)
                , contentDescription = "product"
                , modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .fillMaxWidth())

            Row(verticalAlignment = Alignment.CenterVertically
                ,horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()) {

                Text(text = productItem.title,
                    maxLines = 2,
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(start = 5.dp))

            }

            Text(text = "$ ${productItem.price}",
            modifier = Modifier.padding(end = 5.dp))

        }

    }
}

@ExperimentalMaterialApi
@Composable
fun productItemPlaceHolder(){


    val brush = Brush.horizontalGradient(cardPlaceHolder)

    val textFillerShape = RoundedCornerShape(15.dp)

    Card(onClick = {},
        shape = RoundedCornerShape(15.dp),
        backgroundColor = White1,
        modifier = Modifier
            .height(250.dp)
            .width(175.dp)) {

        Column(Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .background(brush))

            Row(verticalAlignment = Alignment.CenterVertically
                ,horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()) {
                
                
                Column(modifier = Modifier.fillMaxWidth(0.6f)) {
                    Spacer(modifier = Modifier.height(5.dp))

                    Spacer(modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(10.dp)
                        .clip(textFillerShape)
                        .background(brush = brush, shape = textFillerShape))

                    Spacer(modifier = Modifier.height(5.dp))

                    Spacer(modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(10.dp)
                        .clip(textFillerShape)
                        .background(brush = brush, shape = textFillerShape))
                }

                Spacer(modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(10.dp)
                    .clip(textFillerShape)
                    .background(brush = brush, shape = textFillerShape))

            }
        }

    }

}


@OptIn(ExperimentalFoundationApi::class)
@ExperimentalComposeApi
@Composable
fun CategorySelection(
    categories: List<CategoriesItem>,
    OnClick: (HomeScreenEvent) -> Unit
){


    Column(modifier = Modifier.padding(top = 10.dp)) {
        Text(text = "Categories", style = mainScreenTypography.h2)

        LazyVerticalGrid(cells = GridCells.Fixed(2)){
            items(categories){ item ->
                CategoryItem(onClick = { OnClick(it) }, categoryItem = item)
            }

        }
    }
}



@Composable
fun CategoryItem(
    onClick: (HomeScreenEvent) -> Unit,
    categoryItem: CategoriesItem,
){
    Box(modifier = Modifier.padding(start = 10.dp, end = 5.dp)){
        Card(modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(15.dp))
            .clickable { onClick(HomeScreenEvent.OnSelectCategory(categoryItem.categoryName)) },
            backgroundColor = White1){
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {

                Icon(painter = painterResource(id = categoryItem.iconId),
                    contentDescription = categoryItem.categoryName)

                Text(text = stringResource(id = categoryItem.headerId),
                    style = mainScreenTypography.h2,
                    textAlign = TextAlign.Center)
            }
        }
    }

}