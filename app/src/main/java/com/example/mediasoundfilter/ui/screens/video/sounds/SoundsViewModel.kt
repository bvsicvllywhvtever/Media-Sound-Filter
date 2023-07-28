package com.example.mediasoundfilter.ui.screens.video.sounds

import androidx.lifecycle.ViewModel
import com.example.mediasoundfilter.data.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SoundsViewModel : ViewModel() {
    private val _soundsUiState = MutableStateFlow(SoundsUiState())
    val soundsUiState = _soundsUiState.asStateFlow()

    init{
        getVideoId()
        getVideoTitle()
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

        _soundsUiState.value = _soundsUiState.value.copy(muteSounds = sounds)
    }

    fun setSelectedSounds(item: String){
        val newSelections = _soundsUiState.value.selectedSounds.toMutableMap()
        newSelections[item] = !(newSelections[item] ?: false)

        _soundsUiState.value = _soundsUiState.value.copy(selectedSounds = newSelections)
    }

    fun getVideoId() {
        _soundsUiState.value = _soundsUiState.value.copy(
            videoId = VideoRepository.getCurrentVideo()?.id
        )
    }

    fun getVideoTitle() {
        _soundsUiState.value = _soundsUiState.value.copy(
            videoTitle = VideoRepository.getCurrentVideo()?.title
        )
    }
}