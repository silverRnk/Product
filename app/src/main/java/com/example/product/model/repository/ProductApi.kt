package com.example.product.model.repository

import com.example.product.model.Categories
import com.example.product.model.Product
import com.example.product.model.ProductItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("products/{id}")
    suspend fun getProductItem(@Path("id") productId : Int): Response<ProductItem>

    @GET("products")
    suspend fun getProducts(@Query("limit") limit: Int): Response<Product>

    @GET("products/categories")
    suspend fun getCategories(): Response<Categories>

    @GET("products/category/{category}")
    suspend fun getProductByCategory(@Path("category") category: String): Response<Product>

}