package com.example.mediasoundfilter.ui.screens.upload

data class UploadUiState (
    val videoId: String? = null,
    val linkError: String? = null,
    val muteSounds: Map<String, List<String>> = emptyMap(),
    val selectedSounds: Map<String, Boolean> = emptyMap()
)