package com.example.mediasoundfilter.data.repository

import com.example.mediasoundfilter.data.api.YoutubeApi
import com.example.mediasoundfilter.data.responsemodel.VideoDTO
import com.example.mediasoundfilter.domain.model.Video

object VideoRepository {

    private var currentVideo : Video? = null
    fun getCurrentVideo(): Video? {
        return currentVideo
    }

    suspend fun getVideoById(id: String) {
        val videos = YoutubeApi.apiService.getVideoById(id).body()?.items?.map { mapToVideo(it) } ?: emptyList()

        if(videos.size != 1){
            currentVideo = null
        }
        else{
            currentVideo = videos.get(0)
        }
    }

    private fun mapToVideo(videoDTO: VideoDTO): Video {
        return Video(
            title = videoDTO.snippet.title,
            channelTitle = videoDTO.snippet.channelTitle
        )
    }
}