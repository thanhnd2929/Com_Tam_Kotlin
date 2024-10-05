package com.example.cum_tam_ph45160.Service

import com.example.cum_tam_ph45160.Model.Cart.CartData
import com.example.cum_tam_ph45160.Model.Cart.CartRequest
import com.example.cum_tam_ph45160.Model.Cart.CartResponse
import com.example.cum_tam_ph45160.Model.Login.LoginRequest
import com.example.cum_tam_ph45160.Model.Login.LoginResponse
import com.example.cum_tam_ph45160.Model.Product.ProductData
import com.example.cum_tam_ph45160.Model.Product.ProductRequest
import com.example.cum_tam_ph45160.Model.Product.ProductResponse
import com.example.cum_tam_ph45160.Model.Register.RegisterRequest
import com.example.cum_tam_ph45160.Model.Register.RegisterResponse
import com.example.cum_tam_ph45160.Model.TypeProduct.TypeProductData
import com.example.cum_tam_ph45160.Model.TypeProduct.TypeProductRequest
import com.example.cum_tam_ph45160.Model.TypeProduct.TypeProductResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("users/reg")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @POST("users/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("products/add")
    fun addProduct(@Body productRequest: ProductRequest): Call<ProductResponse>

    @GET("products")
    fun getProducts(): Call<List<ProductData>>

    @GET("products/getOne/{id}")
    fun getProductById(@Path("id") productId: String): Call<ProductData>


    @POST("type_products/add_type")
    fun addTypeProduct(@Body TypeProductRequest: TypeProductRequest): Call<TypeProductResponse>

    // Cập nhật phương thức GET
    @GET("type_products")
    fun getTypeProducts(): Call<List<TypeProductData>> // Trả về danh sách các loại sản phẩm

    @GET("products/search")
    fun searchProducts(@Query("q") query: String): Call<List<ProductData>>

    @POST("products/cart")
    fun addToCart(@Body cartRequest: CartRequest) : Call<CartResponse>

//    @GET("products/get-cart")
//    fun getCart() : Call<CartResponse>

    @GET("products/get-cart")
    fun getCart() : Call<List<CartData>>

}