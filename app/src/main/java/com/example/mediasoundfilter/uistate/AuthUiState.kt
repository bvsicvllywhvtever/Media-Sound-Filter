package com.example.mediasoundfilter.uistate

data class AuthUiState (
    val currentUser: String? = null,
    val emailErrorText: String? = null,
    val passErrorText: String? = null,
    val errorText: String? = null
)