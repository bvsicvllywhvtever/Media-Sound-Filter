package com.example.mediasoundfilter.repository

import com.example.mediasoundfilter.YoutubeApi
import com.example.mediasoundfilter.model.Video
import com.example.mediasoundfilter.responsemodel.VideoDTO

object VideoRepository {
    suspend fun getVideoById(id: String): Video? {
        val videos = YoutubeApi.apiService.getVideoById(id).body()?.items?.map { mapToVideo(it) } ?: emptyList()

        if(videos.size != 1){
            return null
        }
        else{
            return videos.get(0)
        }
    }

    private fun mapToVideo(videoDTO: VideoDTO): Video{
        return Video(
            title = videoDTO.title,
            channelTitle = videoDTO.channelTitle
        )
    }
}