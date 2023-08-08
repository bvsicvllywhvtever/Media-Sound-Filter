package com.example.mediasoundfilter.data.api

import com.example.mediasoundfilter.data.responsemodel.sounds.SoundCategoryDTO
import com.example.mediasoundfilter.data.responsemodel.times.MuteTimeDTO
import com.example.mediasoundfilter.domain.model.MuteTimesRequest
import com.example.mediasoundfilter.domain.model.ProcessVideoRequest
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface MediaSoundFilterApiService{
    @GET("/sounds")
    suspend fun getSupportedSounds(): Response<List<SoundCategoryDTO>>

    @POST("/times")
    suspend fun getMuteTimes(@Body muteTimesRequest: MuteTimesRequest): Response<List<MuteTimeDTO>>

    @POST("/video")
    suspend fun processVideo(@Body videoId: ProcessVideoRequest): Response<Void>
}

private const val BASE_URL = "http://10.0.2.2:5000"

private val client = OkHttpClient.Builder()
    .readTimeout(10, TimeUnit.MINUTES)
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

object MediaSoundFilterApi{
    val apiService: MediaSoundFilterApiService by lazy{
        retrofit.create(MediaSoundFilterApiService::class.java)
    }
}