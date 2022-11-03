package com.example.product.ui.ProductScreen

import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.product.R
import com.example.product.model.Product
import com.example.product.model.ProductItem
import com.example.product.ui.HomeScreen.HomeScreenEvent
import com.example.product.ui.theme.White1
import com.example.product.ui.theme.mainScreenTypography
import com.example.product.util.UiEvent

@Composable
fun ProductScreen(
    onNavigate: (UiEvent.OnNavigate) -> Unit,
    viewModel: ProductScreenVM = hiltViewModel()
){

    LaunchedEffect(key1 = 1){
        viewModel.uiEvent.collect{ event ->
            when(event){
                is UiEvent.OnNavigate -> {
                    onNavigate(event)
                }
                else -> Unit
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {


        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 20.dp,
                    bottom = 10.dp
                )
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            SearchBox(search = viewModel.searchBox,
                OnEvent = { event: ProductScreenEvent ->
                    viewModel.onEvent(event)
                })
        }

        ProductList(product = viewModel.product,
            OnEvent = {viewModel.onEvent(it)})
    }
}

@Composable
fun SearchBox(search: String,
              OnEvent: (ProductScreenEvent) -> Unit) {
    TextField(value = search
        , onValueChange = {
            OnEvent(ProductScreenEvent.OnValueChange(it))
        },
        leadingIcon = { Icon(painter = painterResource(id = R.drawable.search_20),
            contentDescription = "search",
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)) },
        modifier = Modifier)
}


@Composable
fun ProductList(
    product: Product?,
    OnEvent: (ProductScreenEvent) -> Unit
){
    
    LazyColumn(){
        product?.let {
            items(it){ item ->
                ProductItem(productItem = item,
                    OnEvent = {event -> OnEvent(event) })
            }
        }
    }
}

@Composable
fun ProductItem(
    productItem: ProductItem,
    OnEvent: (ProductScreenEvent) -> Unit
){
    Box(modifier = Modifier
        .wrapContentSize()
        .padding(horizontal = 10.dp, vertical = 5.dp)) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { OnEvent(ProductScreenEvent.OnProductItemSelect(productItem.id)) },
        backgroundColor = White1,
        shape = RoundedCornerShape(15.dp)) {
            Row(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start) {

                Image(painter = rememberAsyncImagePainter(model = productItem.image)
                    , contentDescription = "product",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(200.dp))

                Column(modifier = Modifier
                    .padding(start = 5.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top) {
                    
                    Spacer(modifier = Modifier.height(25.dp))

                    //Title
                    Text(text = productItem.title,)
                    
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "$" + productItem.price.toString())

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = productItem.rating.rate.toString()
                            + "(" + productItem.rating.count.toString() + ")")


                }
            }
            
        }
        
    }
}
