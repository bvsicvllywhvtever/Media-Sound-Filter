package com.example.mediasoundfilter.data.responsemodel.times

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MuteTimeDTO (
    @Json(name = "start_time") val start: Double,
    @Json(name = "end_time") val end: Double
)