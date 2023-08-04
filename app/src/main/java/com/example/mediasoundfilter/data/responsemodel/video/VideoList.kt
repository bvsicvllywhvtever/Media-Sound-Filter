package com.example.mediasoundfilter.data.responsemodel.video

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoList (
    @Json(name = "items") val items: List<VideoDTO> = emptyList()
)