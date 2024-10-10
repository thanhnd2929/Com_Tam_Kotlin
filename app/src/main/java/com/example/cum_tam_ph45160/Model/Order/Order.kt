package com.example.cum_tam_ph45160.Model.Order

import com.example.cum_tam_ph45160.Model.Checkout.ShippingAddress
import com.example.cum_tam_ph45160.Model.Product.ProductData

data class Order(
    val id: String,                      // ID của đơn hàng
    val user: String,                    // ID người dùng
    val items: List<OrderItem>,          // Danh sách sản phẩm trong đơn hàng
    val totalAmount: Double,              // Tổng giá trị đơn hàng
    val shippingAddress: ShippingAddress, // Địa chỉ giao hàng
    val createdAt: String
)

data class OrderItem(
    val product: ProductData,     // Sản phẩm trong đơn hàng
    val quantity: Int         // Số lượng của sản phẩm
)
