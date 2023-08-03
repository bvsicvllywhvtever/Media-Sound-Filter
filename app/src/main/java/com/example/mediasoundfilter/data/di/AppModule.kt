package com.example.mediasoundfilter.data.di

import com.example.mediasoundfilter.data.api.YoutubeApiService
import com.example.mediasoundfilter.data.interceptors.YoutubeInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"

    @Provides
    @Singleton
    fun provideRetrofitService(): YoutubeApiService {
        val logger = HttpLoggingInterceptor().apply{level = HttpLoggingInterceptor.Level.BODY}

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(YoutubeInterceptor())
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .client(client)
            .build()
            .create(YoutubeApiService::class.java)
    }


}