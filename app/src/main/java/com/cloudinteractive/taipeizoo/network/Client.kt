package com.cloudinteractive.taipeizoo.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Client {

    val zooApiService: ZooApiService

    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                val requestBuilder = it.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .url(it.request().url())
                it.proceed(requestBuilder.build())
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()



        val retrofit = Retrofit.Builder()
            .baseUrl("https://data.taipei")
            .addCallAdapterFactory(ApiResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        zooApiService = retrofit.create(ZooApiService::class.java)
    }


}