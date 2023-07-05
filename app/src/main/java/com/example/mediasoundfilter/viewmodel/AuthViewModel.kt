package com.example.mediasoundfilter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mediasoundfilter.uistate.AuthUiState
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
        val loginSuccess = loginValidation(email, password)

        //if no errors, login
        if(loginSuccess) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _authUiState.value = AuthUiState(currentUser = email)
                    } else {
                        _authUiState.value = _authUiState.value.copy(
                            fieldErrors = _authUiState.value.fieldErrors + ("loginBottom" to "Invalid email or password.")
                        )
                        Log.d("MSF", "Login Failure", task.exception)
                    }
                }
        }
    }

    /**
     * Creates an account for a user given an email, password, and username.
     */
    fun createAccount(username: String, email: String, p1: String, p2: String): Boolean{
        var createAccountSuccess = createAccountValidation(username, email, p1, p2)

        if(createAccountSuccess) {
            auth.createUserWithEmailAndPassword(email, p1)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        auth.currentUser!!.updateProfile(userProfileChangeRequest {
                            displayName = username
                        })
                    } else {
                        //TODO: show network error message
                        createAccountSuccess = false
                    }
                }
        }
        return createAccountSuccess
    }

    private fun loginValidation(email: String, password: String): Boolean{
        validateRequired( "loginEmail", email)
        validateRequired("loginPass", password)

        val fieldErrors = _authUiState.value.fieldErrors
        return fieldErrors["loginEmail"] == null && fieldErrors["loginPass"] == null
                && fieldErrors["loginBottom"] == null
    }

    private fun createAccountValidation(username: String, email: String, p1: String, p2: String): Boolean{
        validateRequired("createUser", username)
        validateRequired("createEmail", email)
        validateRequired("createPass1", p1)
        validateRequired("createPass2", p2)

        if(_authUiState.value.fieldErrors["createEmail"] == null){
            validateEmail(email)
        }

        if(_authUiState.value.fieldErrors["createPass1"] == null &&
            _authUiState.value.fieldErrors["createPass2"] == null){
            validatePasswordMatch(p1, p2)
            if(_authUiState.value.fieldErrors["createBottom"] == null){
                validatePasswordLength(p1)
            }
        }

        val fieldErrors = _authUiState.value.fieldErrors
        return fieldErrors["createUser"] == null && fieldErrors["createEmail"] == null
                && fieldErrors["createPass1"] == null && fieldErrors["createPass2"] == null
                && fieldErrors["createBottom"] == null
    }


    private fun validateRequired(input: String, inputValue: String){
        val errorText = if (inputValue.isEmpty()) "This field is required." else null
        _authUiState.value = _authUiState.value.copy(
            fieldErrors = _authUiState.value.fieldErrors + (input to errorText))
    }

    private fun validateEmail(email: String){
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$", RegexOption.IGNORE_CASE)
        val errorText = if (!regex.matches(email)) "Please enter a valid email address." else null
            _authUiState.value = _authUiState.value.copy(
                fieldErrors = _authUiState.value.fieldErrors +
                        ("createEmail" to errorText))
    }

    private fun validatePasswordMatch(p1: String, p2: String){
        val errorText = if(p1 != p2) "Passwords do not match." else null
        _authUiState.value = _authUiState.value.copy(fieldErrors = _authUiState.value.fieldErrors +
                        ("createBottom" to errorText))
    }

    private fun validatePasswordLength(password: String){
        val errorText = if (password.length < MIN_PASSWORD_LENGTH)
            "Password must be at least $MIN_PASSWORD_LENGTH characters." else null
            _authUiState.value = _authUiState.value.copy(fieldErrors = _authUiState.value.fieldErrors +
                        ("createBottom" to errorText))
    }
}