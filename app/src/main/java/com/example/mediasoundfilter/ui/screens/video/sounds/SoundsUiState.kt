package com.example.mediasoundfilter.ui.screens.video.sounds

import com.example.mediasoundfilter.domain.model.SoundCategory

data class SoundsUiState (
    val muteSounds: List<SoundCategory> = emptyList(),
    val selectedSounds: Map<String, Boolean> = emptyMap()
)