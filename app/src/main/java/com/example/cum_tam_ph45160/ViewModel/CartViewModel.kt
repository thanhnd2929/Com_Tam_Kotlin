package com.example.cum_tam_ph45160.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cum_tam_ph45160.Model.Cart.CartData
import com.example.cum_tam_ph45160.Retrofit.RetrofitClient
import com.example.cum_tam_ph45160.Service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel : ViewModel() {
    private val apiService: ApiService = RetrofitClient.instance

    private val _carts = MutableLiveData<List<CartData>?>()
    val carts: MutableLiveData<List<CartData>?> get() = _carts

    // Trong CartViewModel
    fun updateCartQuantity(productId: String, newQuantity: Int) {
        val updatedCart = _carts.value?.map {
            if (it.product._id == productId) {
                it.copy(quantity = newQuantity)
            } else {
                it
            }
        }
        _carts.value = updatedCart
    }



    fun fetchCart() {
        apiService.getCart().enqueue(object : Callback<List<CartData>?> {
            override fun onResponse(
                call: Call<List<CartData>?>,
                response: Response<List<CartData>?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { cartList ->
                        // Gán danh sách cart trả về vào _carts
                        _carts.value = cartList
                        Log.d("CartData", "Cart data: $cartList")
                    } ?: run {
                        // Xử lý khi không có dữ liệu trả về
                        Log.e("Err", "Response body is null.")
                        _carts.value = emptyList() // Trả về danh sách rỗng nếu không có sản phẩm
                    }
                } else {
                    // Xử lý khi response không thành công
                    Log.e("Err", "Server error: ${response.code()} ${response.message()}")
                    _carts.value = emptyList() // Trả về danh sách rỗng khi có lỗi
                }
            }

            override fun onFailure(call: Call<List<CartData>?>, t: Throwable) {
                _carts.value = emptyList() // Trả về danh sách rỗng khi gọi API thất bại
                Log.e("Err:", "API call failed: ${t.message}")
            }
        })
    }

   fun removeFromCart(productId: String) {
       apiService.deleteFromCart(productId).enqueue(object : Callback<Void?> {
           override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
               if (response.isSuccessful) {
                   val updateCart = _carts.value?.filter { it.product._id != productId }
                   _carts.value = updateCart
                   Log.d("CartData", "Product $productId removed from cart.")
               } else {
                   Log.e("Err", "Failed to remove product: ${response.code()} ${response.message()}")
               }
           }

           override fun onFailure(call: Call<Void?>, t: Throwable) {
               Log.e("Err", "Failed to remove product: ${t.message}")
           }
       })
   }


}