package com.example.mediasoundfilter.ui.screens.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediasoundfilter.data.repository.VideoRepository
import com.example.mediasoundfilter.domain.model.Video
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VideoViewModel: ViewModel() {
    private val _videoUiState = MutableStateFlow(VideoUiState())
    val videoUiState: StateFlow<VideoUiState> = _videoUiState.asStateFlow()

//    fun extractVideoId(link: String){
//
//        //check if link is empty
//        if(link == ""){
//            _videoUiState.value = _videoUiState.value.copy(linkError = "Link field cannot be left empty.")
//        }
//        //otherwise extract videoId
//        else{
//            //possible valid regex formats
//            val pattern1 = Regex("""^https://www\.youtube\.com/watch\?v=([A-Za-z0-9])+${'$'}""")
//            val pattern2 = Regex("""^www\.youtube\.com/watch\?v=([A-Za-z0-9])+${'$'}""")
//            val pattern3 = Regex("""^youtube\.com/watch\?v=([A-Za-z0-9])+${'$'}""")
//            val pattern4 = Regex("""^https://youtu\.be/([A-Za-z0-9])+${'$'}""")
//            val pattern5 = Regex("""^youtu\.be/([A-Za-z0-9])+${'$'}""")
//
//            //check if link matches any of the possible formats and get the videoId
//            var id = ""
//            //patterns 1-3 are typical youtube urls
//            if(pattern1.containsMatchIn(link) || pattern2.containsMatchIn(link) || pattern3.containsMatchIn(link)){
//                id = link.substringAfter('=')
//            }
//            //patterns 4-5 are typical share youtube urls
//            else if(pattern4.containsMatchIn(link) || pattern5.containsMatchIn(link)){
//                id = link.substringAfterLast('/')
//            }
//
//            //check if videoId is valid, and update state accordingly
//            viewModelScope.launch {
//                val video = getVideoFromId(id)
//                if (video != null) {
//                    _videoUiState.value = _videoUiState.value.copy(videoId = id, videoTitle = video.title, channelTitle = video.channelTitle)
//                } else {
//                    _videoUiState.value =
//                        _videoUiState.value.copy(linkError = "Youtube link is not valid.")
//                }
//            }
//        }
//    }

//    private suspend fun getVideoFromId(id: String): Video? {
//        return VideoRepository.getVideoById(id)
//    }
}