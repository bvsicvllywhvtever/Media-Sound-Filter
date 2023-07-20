package com.example.mediasoundfilter.ui.screens.upload

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediasoundfilter.data.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UploadViewModel: ViewModel() {
    private val _uploadUiState = MutableStateFlow(UploadUiState())
    val uploadUiState: StateFlow<UploadUiState> = _uploadUiState.asStateFlow()


    fun extractVideoId(link: String){

        //check if link is empty
        if(link == ""){
            _uploadUiState.value = _uploadUiState.value.copy(linkError = "Link field cannot be left empty.")
        }
        //otherwise extract videoId
        else{
            //possible valid regex formats
            val pattern1 = Regex("""^https://www\.youtube\.com/watch\?v=([A-Za-z0-9])+${'$'}""")
            val pattern2 = Regex("""^www\.youtube\.com/watch\?v=([A-Za-z0-9])+${'$'}""")
            val pattern3 = Regex("""^youtube\.com/watch\?v=([A-Za-z0-9])+${'$'}""")
            val pattern4 = Regex("""^https://youtu\.be/([A-Za-z0-9])+${'$'}""")
            val pattern5 = Regex("""^youtu\.be/([A-Za-z0-9])+${'$'}""")

            //check if link matches any of the possible formats and get the videoId
            var id = ""
            //patterns 1-3 are typical youtube urls
            if(pattern1.containsMatchIn(link) || pattern2.containsMatchIn(link) || pattern3.containsMatchIn(link)){
                id = link.substringAfter('=')
            }
            //patterns 4-5 are typical share youtube urls
            else if(pattern4.containsMatchIn(link) || pattern5.containsMatchIn(link)){
                id = link.substringAfterLast('/')
            }

            //check if videoId is valid, and update state accordingly
            viewModelScope.launch {
                if (isVideoIdValid(id)) {
                    _uploadUiState.value = _uploadUiState.value.copy(videoId = id)
                } else {
                    _uploadUiState.value =
                        _uploadUiState.value.copy(linkError = "Youtube link is not valid.")
                }
            }
        }
    }

    private suspend fun isVideoIdValid(id: String): Boolean{
        return VideoRepository.getVideoById(id) != null
    }

    fun resetState(){
        clearVideoId()
        clearLinkError()
    }

    fun clearVideoId(){
        _uploadUiState.value = _uploadUiState.value.copy(videoId = null)
    }
    fun clearLinkError(){
        _uploadUiState.value = _uploadUiState.value.copy(linkError = null)
    }
}