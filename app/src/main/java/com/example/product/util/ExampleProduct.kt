package com.example.product.util

import com.example.product.model.Product
import com.example.product.model.ProductItem
import com.example.product.model.Rating

object ExampleProduct {

    val productsList = listOf<ProductItem>(
        ProductItem(
            id = 1,
            title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            price = 109.95,
            description = "Your perfect pack for everyday " +
                    "use and walks in the forest. Stash your laptop" +
                    " (up to 15 inches) in the padded sleeve, your everyday",
            category = "men's clothing",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            rating = Rating(count = 120, rate = 3.9),),
        ProductItem(
            id = 1,
            title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            price = 109.95,
            description = "Your perfect pack for everyday " +
                    "use and walks in the forest. Stash your laptop" +
                    " (up to 15 inches) in the padded sleeve, your everyday",
            category = "men's clothing",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            rating = Rating(count = 120, rate = 3.9),),
        ProductItem(
            id = 1,
            title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            price = 109.95,
            description = "Your perfect pack for everyday " +
                    "use and walks in the forest. Stash your laptop" +
                    " (up to 15 inches) in the padded sleeve, your everyday",
            category = "men's clothing",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            rating = Rating(count = 120, rate = 3.9),),
        ProductItem(
            id = 1,
            title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            price = 109.95,
            description = "Your perfect pack for everyday " +
                    "use and walks in the forest. Stash your laptop" +
                    " (up to 15 inches) in the padded sleeve, your everyday",
            category = "men's clothing",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            rating = Rating(count = 120, rate = 3.9),),
        ProductItem(
            id = 1,
            title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            price = 109.95,
            description = "Your perfect pack for everyday " +
                    "use and walks in the forest. Stash your laptop" +
                    " (up to 15 inches) in the padded sleeve, your everyday",
            category = "men's clothing",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            rating = Rating(count = 120, rate = 3.9),)
    )

    val categories = listOf(
        "electronics","jewelery","men's clothing","women's clothing"
    )

}