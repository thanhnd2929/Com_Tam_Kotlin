package com.example.cum_tam_ph45160.Retrofit

import com.example.cum_tam_ph45160.Service.ApiService
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    var token: String? = null

    //10.24.48.182
    //192.168.1.2
    //10.24.35.201

    private const val BASE_URL = "http://10.24.32.182:3000/"
    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request: Request.Builder = chain.request().newBuilder()

                // Lấy token từ SharedPreferences
                val token = RetrofitClient.token

                // Nếu token không null, thêm vào header
                if (token != null) {
                    request.addHeader("Authorization", "Bearer $token")
                }

                chain.proceed(request.build())
            }
            .build()
    }

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .client(createOkHttpClient())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}
