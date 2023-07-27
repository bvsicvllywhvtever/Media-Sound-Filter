package com.example.mediasoundfilter.ui.screens.auth.create_account

data class CreateAccountUiState (
    val fieldErrors : Map<String, String?> = mapOf(
        "user" to null,
        "email" to null,
        "pass1" to null,
        "pass2" to null,
        "bottom" to null
    ),
    val success: Boolean = false
)