package com.example.mediasoundfilter.data.repository

import android.util.Log
import com.example.mediasoundfilter.data.api.MediaSoundFilterApi
import com.example.mediasoundfilter.data.responsemodel.times.MuteTimeDTO
import com.example.mediasoundfilter.domain.model.MuteTimesRequest

object TimesRepository {

    private var muteTimes: List<Pair<Double, Double>> = emptyList()

    suspend fun setMuteTimes(videoId: String, sounds: List<String>){
        val request = MuteTimesRequest(videoId, sounds)

        muteTimes = MediaSoundFilterApi.apiService.getMuteTimes(request).body()?.map{
            mapToMuteTime(it)
        } ?: emptyList()

        Log.d("MSF", muteTimes.toString())
    }

    fun getMuteTimes(): List<Pair<Double, Double>>{
        return muteTimes
    }

    private fun mapToMuteTime(muteTimeDTO: MuteTimeDTO): Pair<Double, Double>{
        return Pair(muteTimeDTO.start, muteTimeDTO.end)
    }
}