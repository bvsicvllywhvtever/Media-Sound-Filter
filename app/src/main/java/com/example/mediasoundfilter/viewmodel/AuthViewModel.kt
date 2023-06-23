package com.example.mediasoundfilter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mediasoundfilter.uistate.AuthUiState
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {
    private val _authUiState = MutableStateFlow(AuthUiState())
    val authUiState: StateFlow<AuthUiState> = _authUiState.asStateFlow()

    private var auth: FirebaseAuth = Firebase.auth

    /**
     * Logs in a user given an email and password. Sets AuthUiState's currentUser to the
     * given email. Setting the current user to a nonnull value switches the navhost.
     */
    fun login(email: String, password: String){
        //perform validation
        validateEmailRequired(email)
        validatePassRequired(password)

        //if no errors, login
        if(_authUiState.value.emailErrorText == null && _authUiState.value.passErrorText == null) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _authUiState.value = _authUiState.value.copy(currentUser = email)
                    } else {
                        _authUiState.value = _authUiState.value.copy(errorText = "Invalid username or password.")
                        Log.d("MSF", "Login Failure", task.exception)
                    }
                })
        }
    }

    /**
     * Creates an account for a user given an email, password, and username.
     */
    fun createAccount(email: String, password: String, username: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(OnCompleteListener { task ->
                if(task.isSuccessful){
                    //TODO: show success message
                    auth.currentUser!!.updateProfile(userProfileChangeRequest { displayName = username })
                }
                else{
                    //TODO: show error message
                    Log.d("MSF", "Sign Up Failure", task.exception)
                }
            })
    }

    fun validateEmailRequired(email: String): String?{
        _authUiState.value = _authUiState.value.copy(emailErrorText =  if (email.isEmpty()) "This field is required." else null)
        return authUiState.value.emailErrorText
    }
    fun validatePassRequired(password: String): String?{
        _authUiState.value = _authUiState.value.copy(passErrorText =  if (password.isEmpty()) "This field is required." else null)
        return authUiState.value.passErrorText
    }
}