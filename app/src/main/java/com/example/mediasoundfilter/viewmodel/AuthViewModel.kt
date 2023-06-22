package com.example.mediasoundfilter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mediasoundfilter.uistate.AuthUiState
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {
    private val _authUiState = MutableStateFlow(AuthUiState())
    val authUiState: StateFlow<AuthUiState> = _authUiState.asStateFlow()

    private var auth: FirebaseAuth = Firebase.auth

    fun login(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(OnCompleteListener { task ->
                if(task.isSuccessful){
                    _authUiState.value = AuthUiState(email)
                }
                else{
                    //TODO: show error message
                    Log.d("MSF", "Login Failure", task.exception)
                }
            })
    }

    fun createAccount(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(OnCompleteListener { task ->
                if(task.isSuccessful){
                    //TODO: show success message
                }
                else{
                    //TODO: show error message
                    Log.d("MSF", "Sign Up Failure", task.exception)
                }
            })
    }
}