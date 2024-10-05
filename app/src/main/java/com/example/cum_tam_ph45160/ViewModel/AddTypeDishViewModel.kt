package com.example.cum_tam_ph45160.ViewModel

import com.example.cum_tam_ph45160.Model.TypeProduct.TypeProductRequest
import com.example.cum_tam_ph45160.Model.TypeProduct.TypeProductResponse
import com.example.cum_tam_ph45160.Retrofit.RetrofitClient
import com.example.cum_tam_ph45160.Service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddTypeDishViewModel {
    private val apiService: ApiService = RetrofitClient.instance

    fun addTypeDish (
        type_name: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val typeProductRequest = TypeProductRequest(
            type_name = type_name
        )

        apiService.addTypeProduct(typeProductRequest).enqueue(object : Callback<TypeProductResponse?> {
            override fun onResponse(
                call: Call<TypeProductResponse?>,
                response: Response<TypeProductResponse?>
            ) {
                if (response.isSuccessful) {
                    // Thành công
                    onSuccess()
                } else {
                    // Xử lý lỗi từ server
                    onError("Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TypeProductResponse?>, t: Throwable) {
                onError("Failure: ${t.message}")

            }
        })
    }
}