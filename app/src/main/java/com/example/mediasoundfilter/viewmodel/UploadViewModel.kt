package com.example.mediasoundfilter.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mediasoundfilter.uistate.UploadUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UploadViewModel: ViewModel() {
    private val _uploadUiState = MutableStateFlow(UploadUiState())
    val uploadUiState: StateFlow<UploadUiState> = _uploadUiState.asStateFlow()


    fun extractVideoId(link: String){
        val pattern1 = Regex("""^https://www\.youtube\.com/watch\?v=([A-Za-z0-9])+${'$'}""")
        val pattern2 = Regex("""^www\.youtube\.com/watch\?v=([A-Za-z0-9])+${'$'}""")
        val pattern3 = Regex("""^youtube\.com/watch\?v=([A-Za-z0-9])+${'$'}""")
        val pattern4 = Regex("""^https://youtu\.be/([A-Za-z0-9])+${'$'}""")
        val pattern5 = Regex("""^youtu\.be/([A-Za-z0-9])+${'$'}""")

        var id: String? = null

        if(pattern1.containsMatchIn(link) || pattern2.containsMatchIn(link) || pattern3.containsMatchIn(link)){
            id = link.substringAfter('=')
        }
        else if(pattern4.containsMatchIn(link) || pattern5.containsMatchIn(link)){
            id = link.substringAfterLast('/')
        }

        _uploadUiState.value = _uploadUiState.value.copy(videoId = id)

        //TODO: check if videoId is valid/null
    }
}