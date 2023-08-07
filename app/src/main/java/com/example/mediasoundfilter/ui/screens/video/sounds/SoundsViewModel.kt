package com.example.mediasoundfilter.ui.screens.video.sounds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediasoundfilter.data.repository.SoundsRepository
import com.example.mediasoundfilter.data.repository.TimesRepository
import com.example.mediasoundfilter.data.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SoundsViewModel : ViewModel() {
    private val _soundsUiState = MutableStateFlow(SoundsUiState())
    val soundsUiState = _soundsUiState.asStateFlow()

    fun setSounds(){
        viewModelScope.launch {
            val sounds = SoundsRepository.getSupportedSounds()
            _soundsUiState.value = _soundsUiState.value.copy(muteSounds = sounds)
        }
    }

    fun setSelectedSounds(item: String){
        val newSelections = _soundsUiState.value.selectedSounds.toMutableMap()
        newSelections[item] = !(newSelections[item] ?: false)

        _soundsUiState.value = _soundsUiState.value.copy(selectedSounds = newSelections)
    }

    fun setMuteTimes(){
        val sounds = mutableListOf<String>()
        for (sound in _soundsUiState.value.selectedSounds.keys){
            if(_soundsUiState.value.selectedSounds[sound] == true){
                sounds.add(sound)
            }
        }

        viewModelScope.launch {
            VideoRepository.getCurrentVideo()?.id?.let { TimesRepository.setMuteTimes(it, sounds) }
            _soundsUiState.value = _soundsUiState.value.copy(muteSoundsLoaded = true)
        }
    }
}