package com.example.mediasoundfilter.ui.screens.auth.login

data class LoginUiState (
    val currentUser: String? = null,
    val fieldErrors : Map<String, String?> = mapOf(
        "email" to null,
        "pass" to null,
        "bottom" to null
    ),
)