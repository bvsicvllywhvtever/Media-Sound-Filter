package com.example.mediasoundfilter.data.responsemodel

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class VideoDTO {
    @Json(name = "snippet.title") val title: String = ""
    @Json(name = "snippet.channelTitle") val channelTitle: String = ""
}