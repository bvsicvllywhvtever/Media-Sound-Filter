package com.example.mediasoundfilter.ui.screens.video.upload

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UploadViewModel: ViewModel() {
    private val _uploadUiState = MutableStateFlow(UploadUiState())
    val uploadUiState = _uploadUiState.asStateFlow()
}