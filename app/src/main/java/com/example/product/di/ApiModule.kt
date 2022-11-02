package com.example.product.di

import com.example.product.model.Categories
import com.example.product.model.Product
import com.example.product.model.repository.ProductApi
import com.example.product.model.repository.ProductItemInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun providesProductApi() : ProductApi{
        return ProductItemInterface
            .getRetrofitInstance()
            .create(ProductApi::class.java)
    }

}