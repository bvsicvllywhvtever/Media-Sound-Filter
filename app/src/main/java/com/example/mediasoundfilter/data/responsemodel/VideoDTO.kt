package com.example.mediasoundfilter.data.responsemodel

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoDTO (
    @Json(name = "snippet") val snippet: Snippet
)

@JsonClass(generateAdapter = true)
data class Snippet(
    @Json(name = "title") val title: String,
    @Json(name = "channelTitle") val channelTitle: String
)