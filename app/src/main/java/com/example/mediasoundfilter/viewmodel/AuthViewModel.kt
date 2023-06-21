package com.example.mediasoundfilter.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mediasoundfilter.uistate.AuthUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {
    private val _authUiState = MutableStateFlow(AuthUiState())
    val authUiState: StateFlow<AuthUiState> = _authUiState.asStateFlow()

    fun login(){
        _authUiState.value = AuthUiState(true)
    }
}