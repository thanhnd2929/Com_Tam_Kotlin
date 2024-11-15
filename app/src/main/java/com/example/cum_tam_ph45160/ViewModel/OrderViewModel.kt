package com.example.cum_tam_ph45160.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cum_tam_ph45160.Model.Order.Order
import com.example.cum_tam_ph45160.Retrofit.RetrofitClient
import com.example.cum_tam_ph45160.Service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderViewModel : ViewModel() {
    private val apiService: ApiService = RetrofitClient.instance

    private val _orders = MutableLiveData<List<Order>?>()
    val orders: MutableLiveData<List<Order>?> get() = _orders

    fun fetchOrder() {
        apiService.getOrders().enqueue(object : Callback<List<Order>> {
            override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
                if (response.isSuccessful) {
                    val orderList = response.body()
                    if (orderList != null) {
                        Log.d("fetchOrder", "Orders fetched successfully: $orderList")
                        _orders.value = orderList
                    } else {
                        Log.e("fetchOrder", "Response body is null")
                        _orders.value = emptyList()
                    }
                } else {
                    Log.e("fetchOrder", "Failed to fetch orders: ${response.errorBody()?.string()}")
                    _orders.value = emptyList() // Xử lý khi không có kết quả
                }
            }

            override fun onFailure(call: Call<List<Order>?>, t: Throwable) {
                // Có thể cập nhật giá trị orders thành null hoặc log lỗi
                _orders.value = emptyList() // Hoặc _orders.value = null
                // Ghi log lỗi nếu cần
                Log.e("OrderViewModel", "Error fetching orders", t)
            }
        })
    }
}