package com.wealthpark.tokyogallery.pankajIn

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object TokyoParkAPIClient {

    private var apiClient: Retrofit? = null
    private const val BASE_URL = "https://api.npoint.io"

    private fun createClient(): Retrofit {
        apiClient = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()

        return apiClient!!
    }

    private fun getOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(200, TimeUnit.SECONDS)
        okHttpClient.readTimeout(200, TimeUnit.SECONDS)
        return okHttpClient.build()

    }

    @JvmStatic
    fun getAPIClient(): Retrofit {
        return apiClient ?: createClient()
    }
}