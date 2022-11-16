package com.example.product.model

import com.example.product.ProductObject
import com.example.product.model.repository.ProductApi
import retrofit2.Response

class FakeProductApi: ProductApi {



    var categories = Categories()



    override suspend fun getProductItem(productId: Int): Response<ProductItem> {
        val prdItm: ProductItem? = ProductObject.product.find { productItem ->
                productItem.id == productId }
        return Response.success(prdItm)
    }

    override suspend fun getProducts(limit: Int): Response<Product> {
        var product = Product()
        if (product.size >= limit){
            product.clear()
            product.addAll(ProductObject.product.subList(0, limit).toTypedArray())
            return Response.success(product)
        }else{
            product.clear()
            product.addAll(ProductObject.product.toTypedArray())
            return Response.success(product)
        }
    }

    override suspend fun getProducts(): Response<Product> {
        var product = Product()
        product.clear()
        product.addAll(ProductObject.product.toTypedArray())
        return Response.success(product)
    }

    override suspend fun getCategories(): Response<Categories> {
        categories.clear()
        categories.addAll(ProductObject.categories.toTypedArray())
        return Response.success(categories)
    }

    override suspend fun getProductByCategory(category: String): Response<Product> {
        val product = Product()
        product.clear()
        product.addAll(
            ProductObject.product.filter { productItem ->
                productItem.category == category
            }.toTypedArray()
        )
        return Response.success(product)
    }
}