package com.example.product.ui.HomeScreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.product.R
import com.example.product.model.Categories
import com.example.product.model.Product
import com.example.product.ui.theme.White1
import com.example.product.ui.theme.mainScreenTypography


@Composable
fun HomeScreen(
    viewModel: HomeScreenVM = hiltViewModel()
){



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


        Deals(product = viewModel.product)

        Categories(categories = viewModel.categories)

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

@Composable
fun Deals(
    product: Product?
){

    Column(horizontalAlignment = Alignment.Start) {
        Text(text = "Deals"
        , style =  mainScreenTypography.h2,
        modifier = Modifier.padding(bottom = 5.dp))

        product?.let {
            LazyRow{
                items(it){ item ->
                    Box(modifier = Modifier
                        .padding(start = 10.dp)
                        .wrapContentSize()){
                        Card(
                            modifier = Modifier
                                .height(250.dp)
                                .width(175.dp)
                                .clip(RoundedCornerShape(15.dp)),
                            backgroundColor = White1){
                            Column(modifier = Modifier.fillMaxSize()) {
                                Image(painter = rememberAsyncImagePainter(model = item.image)
                                    , contentDescription = "product"
                                    , modifier = Modifier
                                        .fillMaxHeight(0.8f)
                                        .fillMaxWidth())

                                Row(verticalAlignment = Alignment.CenterVertically
                                    ,horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 5.dp, vertical = 5.dp)) {
                                    Text(text = item.title,
                                        maxLines = 2,
                                        modifier = Modifier.fillMaxWidth(0.6f))

                                    Text(text = "$ ${item.price}")
                                }
                            }
                        }
                    }
                }
            }

        }


    }
}



@Composable
fun Categories(
    categories: Categories?
){


    Column(modifier = Modifier.padding(top = 10.dp)) {
        Text(text = "Categories", style = mainScreenTypography.h2)

        categories?.let {
            LazyRow(modifier = Modifier
                .wrapContentSize()
                .padding(top = 5.dp)){
                items(it){ category ->
                    CategoryItem(item = category)
                }
            }
        }

    }


    

}


@Composable
fun CategoryItem(
    item: String,
){
    Box(modifier = Modifier.padding(start = 10.dp, end = 5.dp)){
        Card(modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(15.dp)),
            backgroundColor = White1){
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text = item,
                    style = mainScreenTypography.h2,
                    textAlign = TextAlign.Center)
            }

        }
    }

}