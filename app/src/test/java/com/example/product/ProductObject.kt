package com.example.product

import com.example.product.model.ProductItem
import com.example.product.model.Rating

object ProductObject {
    val product = listOf(
        ProductItem(id = 1, price = 1.00, rating = Rating(1, 0.00), category = "electronics"),
        ProductItem(id = 2, price = 1.00, rating = Rating(1, 0.00), category = "men's clothing"),
        ProductItem(id = 3, price = 1.00, rating = Rating(1, 0.00), category = "jewelry"),
        ProductItem(id = 4, price = 1.00, rating = Rating(1, 0.00), category = "women clothing"),
        ProductItem(id = 5, price = 1.00, rating = Rating(1, 0.00), category = "electronics"),
        ProductItem(id = 6, price = 1.00, rating = Rating(1, 0.00), category = "men's clothing")
    )

    val categories = listOf("electronics","jewelery","men's clothing","women's clothing")
}