package com.example.mediasoundfilter.interceptors

import com.example.mediasoundfilter.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class YoutubeInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKey = BuildConfig.GOOGLE_API_KEY
        val url = chain.request().url.newBuilder().addQueryParameter("key", apiKey).build()
        val newRequest = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return chain.proceed(newRequest)
    }
}