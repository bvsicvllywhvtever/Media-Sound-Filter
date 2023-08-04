package com.example.mediasoundfilter.data.responsemodel.sounds

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SoundCategoryDTO(
    @Json(name = "category") val category: String,
    @Json(name = "sounds") val sounds: List<String>
)