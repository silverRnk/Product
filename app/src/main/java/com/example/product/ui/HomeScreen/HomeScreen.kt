package com.example.product.ui.HomeScreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
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
import com.example.product.util.UiEvent


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

        Categories(viewModel = viewModel)

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
    viewModel: HomeScreenVM,
){

    val product = viewModel.product

    Column(horizontalAlignment = Alignment.Start) {
        Text(text = "Deals"
        , style =  mainScreenTypography.h2,
        modifier = Modifier.padding(bottom = 5.dp))

            LazyRow{
                items(product){ item ->
                    Box(modifier = Modifier
                        .padding(start = 10.dp)
                        .wrapContentSize()){
                        Card(
                            modifier = Modifier
                                .height(250.dp)
                                .width(175.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .clickable {
                                    viewModel.onEvent(
                                        HomeScreenEvent
                                            .OnProductItemSelected(item.id)
                                    )
                                },
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



@Composable
fun Categories(
    viewModel: HomeScreenVM
){

    val categories = viewModel.categories

    Column(modifier = Modifier.padding(top = 10.dp)) {
        Text(text = "Categories", style = mainScreenTypography.h2)

        LazyRow(modifier = Modifier
            .wrapContentSize()
            .padding(top = 5.dp)){
            items(categories){ category ->
                CategoryItem( onClick = {viewModel.onEvent(HomeScreenEvent.OnSelectCategory(category))},
                    item = category)
            }
        }
    }
}



@Composable
fun CategoryItem(
    onClick: () -> Unit,
    item: String,
){
    Box(modifier = Modifier.padding(start = 10.dp, end = 5.dp)){
        Card(modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(15.dp))
            .clickable { onClick() },
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