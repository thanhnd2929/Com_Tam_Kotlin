package com.example.cum_tam_ph45160.Model.Login

data class LoginResponse(
    val token: String,
    val userId: String, // Thêm userId vào đây
    val msg: String
)
