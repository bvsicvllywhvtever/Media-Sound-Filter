package com.example.mediasoundfilter.domain.model

data class MuteTimesRequest (
    val videoId: String,
    val sounds: List<String>
)