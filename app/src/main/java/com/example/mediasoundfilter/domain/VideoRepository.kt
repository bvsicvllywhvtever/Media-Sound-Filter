package com.example.mediasoundfilter.domain

import com.example.mediasoundfilter.domain.model.Video

interface VideoRepository {

    suspend fun getVideoById(id: String) : Video?

}