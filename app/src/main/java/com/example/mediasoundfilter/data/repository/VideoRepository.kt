package com.example.mediasoundfilter.data.repository

import com.example.mediasoundfilter.data.api.YoutubeApi
import com.example.mediasoundfilter.data.repository.VideoRepository.currentVideoMutex
import com.example.mediasoundfilter.data.responsemodel.VideoDTO
import com.example.mediasoundfilter.domain.model.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

object VideoRepository {

    private val currentVideoMutex = Mutex()

    private var currentVideo: Video? = null


    // Mutex to make writes to cached values thread-safe.
//    private val latestNewsMutex = Mutex()
//
//    // Cache of the latest news got from the network.
//    private var latestNews: List<ArticleHeadline> = emptyList()
//
//    suspend fun getLatestNews(refresh: Boolean = false): List<ArticleHeadline> {
//        if (refresh || latestNews.isEmpty()) {
//            val networkResult = newsRemoteDataSource.fetchLatestNews()
//            // Thread-safe write to latestNews
//            latestNewsMutex.withLock {
//                this.latestNews = networkResult
//            }
//        }
//
//        return latestNewsMutex.withLock { this.latestNews }
//    }

    suspend fun getCurrentVideoById(id: String) : Video? {
        val videos = YoutubeApi.apiService.getVideoById(id).body()?.items?.map { mapToVideo(it, id) } ?: emptyList()
        if(videos.size != 1){
            currentVideoMutex.withLock { this.currentVideo = null }
        }
        else{
            currentVideoMutex.withLock { this.currentVideo =  videos.get(0) }
        }
        return currentVideoMutex.withLock { this.currentVideo }
    }

    private fun mapToVideo(videoDTO: VideoDTO, videoId: String): Video {
        return Video(
            id = videoId,
            title = videoDTO.snippet.title,
            channelTitle = videoDTO.snippet.channelTitle
        )
    }
}