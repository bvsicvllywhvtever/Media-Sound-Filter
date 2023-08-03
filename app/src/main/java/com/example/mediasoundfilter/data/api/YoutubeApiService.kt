package com.example.mediasoundfilter.data.api

import com.example.mediasoundfilter.data.interceptors.YoutubeInterceptor
import com.example.mediasoundfilter.data.responsemodel.VideoList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApiService {
    @GET("videos?part=snippet")
    suspend fun getVideoById(@Query("id") id: String): Response<VideoList>
}

