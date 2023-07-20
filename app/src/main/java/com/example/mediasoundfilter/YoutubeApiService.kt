package com.example.mediasoundfilter

import com.example.mediasoundfilter.interceptors.YoutubeInterceptor
import com.example.mediasoundfilter.responsemodel.VideoList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApiService {
    @GET("videos?part=snippet")
    suspend fun getVideoById(@Query("id") id: String): Response<VideoList>
}

private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"

val client: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(YoutubeInterceptor())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(
        MoshiConverterFactory.create(
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        )
    )
    .client(client)
    .build()

object YoutubeApi{
    val apiService: YoutubeApiService by lazy{
        retrofit.create(YoutubeApiService::class.java)
    }
}