package com.example.mediasoundfilter.ui.screens.media

data class MediaUiState (
    val videoId: String? = null,
    val videoTitle: String? = null,
    val channelTitle: String? = null,
    val linkError: String? = null,
    val muteSounds: Map<String, List<String>> = emptyMap(),
    val selectedSounds: Map<String, Boolean> = emptyMap()
)