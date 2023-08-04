package com.example.mediasoundfilter.data.responsemodel.video

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoDTO (
    @Json(name = "id") val id: String,
    @Json(name = "snippet") val snippet: Snippet
)

@JsonClass(generateAdapter = true)
data class Snippet(
    @Json(name = "title") val title: String,
    @Json(name = "channelTitle") val channelTitle: String
)