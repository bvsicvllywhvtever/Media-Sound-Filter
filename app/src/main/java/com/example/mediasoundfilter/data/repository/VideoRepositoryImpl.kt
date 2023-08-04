package com.example.mediasoundfilter.data.repository

import com.example.mediasoundfilter.data.api.YoutubeApiService
import com.example.mediasoundfilter.data.responsemodel.VideoDTO
import com.example.mediasoundfilter.domain.VideoRepository
import com.example.mediasoundfilter.domain.model.Video
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val youtubeApi: YoutubeApiService
) : VideoRepository {
    override suspend fun getVideoById(id: String): Video? {
        val videos = youtubeApi.getVideoById(id).body()?.items?.map { mapToVideo(it) } ?: emptyList()

        if(videos.size != 1){
            return null
        }
        else{
            return videos.get(0)
        }
    }

    private fun mapToVideo(videoDTO: VideoDTO): Video {
        return Video(
            title = videoDTO.snippet.title,
            channelTitle = videoDTO.snippet.channelTitle
        )
    }
}