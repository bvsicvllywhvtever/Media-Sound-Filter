package com.example.mediasoundfilter.ui.screens.media

import androidx.lifecycle.ViewModel

class MediaViewModel : ViewModel() {

    fun getMuteTimes(): List<Pair<Double, Double>>{
        //return listOf(Pair(0.0, 10.0), Pair(20.0, 30.0))
        //return listOf(Pair(0.0, 2.13), Pair(4.1567, 5.0), Pair(6.0, 8.0), Pair(7.5679, 8.0))
        return listOf(Pair(0.0, 2.13), Pair(4.1567, 5.0), Pair(6.0, 8.0), Pair(9.0, 11.5))
    }

}