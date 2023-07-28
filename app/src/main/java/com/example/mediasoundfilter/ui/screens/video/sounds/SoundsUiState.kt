package com.example.mediasoundfilter.ui.screens.video.sounds

data class SoundsUiState (
    val muteSounds: Map<String, List<String>> = emptyMap(),
    val selectedSounds: Map<String, Boolean> = emptyMap(),
    val videoTitle: String? = null,
    val videoId: String? = null
)