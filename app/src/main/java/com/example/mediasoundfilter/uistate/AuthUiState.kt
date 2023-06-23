package com.example.mediasoundfilter.uistate

data class AuthUiState (
    val currentUser: String? = null,
    val fieldErrors : Map<String, String?> = mapOf(
        "loginEmail" to null,
        "loginPass" to null,
        "loginBottom" to null,
        "createUser" to null,
        "createEmail" to null,
        "createPass1" to null,
        "createPass2" to null,
        "createBottom" to null
    )
)