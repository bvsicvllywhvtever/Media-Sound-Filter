package com.example.mediasoundfilter.data.repository

import com.example.mediasoundfilter.data.api.YoutubeApi
import com.example.mediasoundfilter.data.responsemodel.VideoDTO
import com.example.mediasoundfilter.domain.model.Video
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

object VideoRepository {

    private val currentVideoMutex = Mutex()

    private var currentVideo: Video? = null

    fun getCurrentVideo(): Video? {
        return currentVideo
    }

    suspend fun retrieveCurrentVideoById(id: String) {
        val videos = YoutubeApi.apiService.getVideoById(id).body()?.items?.map { mapToVideo(it, id) } ?: emptyList()
        if(videos.size != 1){
            currentVideoMutex.withLock { this.currentVideo = null }
        }
        else{
            currentVideoMutex.withLock { this.currentVideo =  videos.get(0) }
        }
    }

    private fun mapToVideo(videoDTO: VideoDTO, videoId: String): Video {
        return Video(
            id = videoId,
            title = videoDTO.snippet.title,
            channelTitle = videoDTO.snippet.channelTitle
        )
    }
}