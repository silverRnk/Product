package com.example.product.model

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductItemInterface {

    companion object{
        val baseUrl = "https://fakestoreapi.com/"

        fun getRetrofitInstance(): Retrofit {
            return  Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}