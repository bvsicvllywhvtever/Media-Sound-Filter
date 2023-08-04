package com.example.mediasoundfilter.data.repository

import com.example.mediasoundfilter.data.api.YoutubeApi
import com.example.mediasoundfilter.data.responsemodel.video.VideoDTO
import com.example.mediasoundfilter.domain.model.Video
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

object VideoRepository {

    private var currentVideo: Video? = null
    private val currentVideoMutex = Mutex()

    suspend fun getVideoById(id: String): Video? {
        val videos = YoutubeApi.apiService.getVideoById(id).body()
            ?.items?.map { mapToVideo(it) } ?: emptyList()

        if(videos.size == 1){
            currentVideoMutex.withLock { currentVideo = videos[0] }
        }
        else{
            currentVideoMutex.withLock { currentVideo = null }
        }

        return currentVideo
    }

    private fun mapToVideo(videoDTO: VideoDTO): Video {
        return Video(
            id = videoDTO.id,
            title = videoDTO.snippet.title,
            channelTitle = videoDTO.snippet.channelTitle
        )
    }

    fun getCurrentVideo(): Video?{
        return currentVideo
    }
}