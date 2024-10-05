package com.example.cum_tam_ph45160.ViewModel

import android.annotation.SuppressLint
import com.example.cum_tam_ph45160.Model.Product.ProductRequest
import com.example.cum_tam_ph45160.Model.Product.ProductResponse
import com.example.cum_tam_ph45160.Model.TypeProduct.TypeProductData
import com.example.cum_tam_ph45160.Retrofit.RetrofitClient
import com.example.cum_tam_ph45160.Service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddDishViewModel {
    private val apiService: ApiService = RetrofitClient.instance



    // Hàm để gọi API thêm sản phẩm
    @SuppressLint("SuspiciousIndentation")
    fun addDish(
        loaiMon: TypeProductData,
        gia: Double,
        tenMon: String,
        imageUri: String,
        moTa: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        // Chuẩn bị dữ liệu gửi lên server
      val productRequest = ProductRequest(
          name = tenMon,
          image_url = imageUri,
          price = gia.toDouble(),
          description = moTa,
          category = loaiMon
      )


        apiService.addProduct(productRequest).enqueue(object : Callback<ProductResponse?> {
            override fun onResponse(
                call: Call<ProductResponse?>,
                response: Response<ProductResponse?>
            ) {
                if (response.isSuccessful) {
                    // Thành công
                    onSuccess()
                } else {
                    // Xử lý lỗi từ server
                    onError("Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ProductResponse?>, t: Throwable) {
                onError("Failure: ${t.message}")
            }
        })


    }
}