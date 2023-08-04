package com.example.mediasoundfilter.data.repository

import com.example.mediasoundfilter.data.api.MediaSoundFilterApi
import com.example.mediasoundfilter.data.responsemodel.sounds.SoundCategoryDTO
import com.example.mediasoundfilter.domain.model.SoundCategory

object SoundsRepository{
    suspend fun getSupportedSounds(): List<SoundCategory>{
        return MediaSoundFilterApi.apiService.getSupportedSounds().body()
            ?.map{mapToSoundCategory(it)} ?: emptyList()
    }

    private fun mapToSoundCategory(soundCategory: SoundCategoryDTO): SoundCategory{
        return SoundCategory(
            category = soundCategory.category,
            sounds = soundCategory.sounds
        )
    }
}