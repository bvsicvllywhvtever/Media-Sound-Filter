package com.example.mediasoundfilter.ui.screens.video

import androidx.lifecycle.ViewModel
import com.example.mediasoundfilter.data.repository.VideoRepository
import com.example.mediasoundfilter.domain.model.Video

class VideoSharedViewModel: ViewModel() {
    fun getCurrentVideo(): Video?{
        return VideoRepository.getCurrentVideo()
    }
}