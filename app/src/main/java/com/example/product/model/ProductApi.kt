package com.example.product.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("products/{id}")
    suspend fun getProductItem(@Path("id") productId : Int): Response<ProductItem>

}