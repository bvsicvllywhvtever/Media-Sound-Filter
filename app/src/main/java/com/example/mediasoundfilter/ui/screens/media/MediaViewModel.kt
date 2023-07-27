package com.example.mediasoundfilter.ui.screens.media

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediasoundfilter.data.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MediaViewModel: ViewModel() {
    private val _mediaUiState = MutableStateFlow(MediaUiState())
    val mediaUiState: StateFlow<MediaUiState> = _mediaUiState.asStateFlow()


    fun extractVideoId(link: String){

        //check if link is empty
        if(link == ""){
            _mediaUiState.value = _mediaUiState.value.copy(linkError = "Link field cannot be left empty.")
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
                    _mediaUiState.value = _mediaUiState.value.copy(videoId = id)
                } else {
                    _mediaUiState.value =
                        _mediaUiState.value.copy(linkError = "Youtube link is not valid.")
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
        _mediaUiState.value = _mediaUiState.value.copy(videoId = null)
    }
    fun clearLinkError(){
        _mediaUiState.value = _mediaUiState.value.copy(linkError = null)
    }

    fun setSounds(){
        val speechList = listOf("Babbling", "Shouting", "Whispering", "Crying", "Baby Crying",
            "Whimpering", "Sighing", "Singing", "Humming", "Groaning", "Grunting", "Whistling",
            "Breathing", "Wheezing", "Snoring", "Gasping", "Panting", "Coughing", "Throat Clearing",
            "Eating", "Gargling", "Burping", "Hiccuping")
        val humanList = listOf("Sneezing", "Sniffing", "Farting")
        val movementList = listOf("Footsteps", "Finger Snapping", "Clapping")
        val environmentalList = listOf("Knocking", "Slamming", "Tapping", "Squeaking", "Drawer/Cupboard",
            "Pots and Pans", "Silverware", "Vacuum Cleaner", "Typing", "Clock", "Filing", "Clink",
            "Squish", "Clicking", "TV")
        val phoneList = listOf("Siren", "Phone Ringing", "Phone Dialing", "Dial Tone", "Alarm Clock",
            "Buzzer", "Foghorn", "Whistle", "Beep", "Ding")
        val sounds = mapOf("Speech/Mouth" to speechList, "Other Human Noises" to humanList,
            "Human Movement" to movementList, "Environmental Movement" to environmentalList,
            "Phone/Alarms" to phoneList)

        _mediaUiState.value = _mediaUiState.value.copy(muteSounds = sounds)
    }

    fun setSelectedSounds(item: String){
        val newSelections = _mediaUiState.value.selectedSounds.toMutableMap()
        newSelections[item] = !(newSelections[item] ?: false)

        _mediaUiState.value = _mediaUiState.value.copy(selectedSounds = newSelections)
    }

    fun getMuteTimes(): List<Pair<Double, Double>>{
        //return listOf(Pair(0.0, 10.0), Pair(20.0, 30.0))
        //return listOf(Pair(0.0, 2.13), Pair(4.1567, 5.0), Pair(6.0, 8.0), Pair(7.5679, 8.0))
        return listOf(Pair(0.0, 2.13), Pair(4.1567, 5.0), Pair(6.0, 8.0), Pair(9.0, 11.5))
    }

}