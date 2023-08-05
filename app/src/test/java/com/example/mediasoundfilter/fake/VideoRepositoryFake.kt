package com.example.mediasoundfilter.fake

import com.example.mediasoundfilter.domain.VideoRepository
import com.example.mediasoundfilter.domain.model.Video

class VideoRepositoryFake: VideoRepository {
    override suspend fun getVideoById(id: String): Video? {
        // if string is x z return cases

        if(id.equals("ErhtJdsIJdM")){
            return Video("123","test")
        } else {
            return null
        }

    }
}