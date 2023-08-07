package com.example.mediasoundfilter.ui.screens.video.media

import androidx.lifecycle.ViewModel
import com.example.mediasoundfilter.data.repository.TimesRepository

class MediaViewModel : ViewModel(){
    fun getMuteTimes(): List<Pair<Double, Double>>{
        return TimesRepository.getMuteTimes()
    }
}