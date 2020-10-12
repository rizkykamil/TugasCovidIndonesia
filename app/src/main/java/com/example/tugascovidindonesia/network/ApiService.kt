package com.example.tugascovidindonesia.network

import com.example.tugascovidindonesia.model.ResponseModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiService{
    @GET("provinsi")
    fun getAllProvince() : Call<ResponseModel>
}

object RetrofitBuilder{
    private val okhttp = OkHttpClient().newBuilder()
        .connectTimeout(12, TimeUnit.SECONDS)
        .readTimeout(12,TimeUnit.SECONDS)
        .writeTimeout(12,TimeUnit.SECONDS)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://indonesia-covid-19.mathdro.id/api/")
        .client(okhttp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

