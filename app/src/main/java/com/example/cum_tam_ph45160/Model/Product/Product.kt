package com.example.cum_tam_ph45160.Model.Product

import com.example.cum_tam_ph45160.Model.TypeProduct.TypeProductData

data class ProductRequest(
    val name: String,
    val image_url: String,
    val price: Double,
    val description: String,
    val category: TypeProductData
)

data class ProductResponse(
    val msg: String,
    val product: ProductData
)

data class ProductData(
    val _id: String,
    val name: String,
    val image_url: String,
    val price: Double,
    val description: String,
    val category: TypeProductData
)