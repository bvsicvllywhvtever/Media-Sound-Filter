package com.example.mediasoundfilter.ui.screens.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    //TODO move this to a dependency class
    private var auth: FirebaseAuth = Firebase.auth

    /**
     * Logs in a user given an email and password. Sets AuthUiState's currentUser to the
     * given email. Setting the current user to a nonnull value switches the navhost.
     */
    fun login(email: String, password: String){
        //perform validation
        val loginSuccess = loginValidation(email, password)

        //if no errors, login
        if(loginSuccess) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _loginUiState.value = LoginUiState(currentUser = email)
                    } else {
                        _loginUiState.value = _loginUiState.value.copy(
                            fieldErrors = _loginUiState.value.fieldErrors + ("loginBottom" to "Invalid email or password.")
                        )
                        Log.d("MSF", "Login Failure", task.exception)
                    }
                }
        }
    }

    private fun loginValidation(email: String, password: String): Boolean{
        validateRequired( "loginEmail", email)
        validateRequired("loginPass", password)

        val fieldErrors = _loginUiState.value.fieldErrors
        return fieldErrors["loginEmail"] == null && fieldErrors["loginPass"] == null
                && fieldErrors["loginBottom"] == null
    }

    private fun validateRequired(input: String, inputValue: String){
        val errorText = if (inputValue.isEmpty()) "This field is required." else null
        _loginUiState.value = _loginUiState.value.copy(
            fieldErrors = _loginUiState.value.fieldErrors + (input to errorText))
    }

    suspend fun resetState(){
        delay(500)
        _loginUiState.value = LoginUiState()
    }
}