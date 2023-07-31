package com.example.mediasoundfilter.ui.screens.auth.create_account

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mediasoundfilter.ui.MIN_PASSWORD_LENGTH
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateAccountViewModel : ViewModel() {
    private val _createAccountUiState = MutableStateFlow(CreateAccountUiState())
    val createAccountUiState: StateFlow<CreateAccountUiState> = _createAccountUiState.asStateFlow()

    private var auth: FirebaseAuth = Firebase.auth

    /**
     * Creates an account for a user given an email, password, and username.
     */
    fun createAccount(username: String, email: String, p1: String, p2: String){
        val success = createAccountValidation(username, email, p1, p2)

        if(success) {
            auth.createUserWithEmailAndPassword(email, p1)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        auth.currentUser!!.updateProfile(userProfileChangeRequest {
                            displayName = username
                        })
                        _createAccountUiState.value = _createAccountUiState.value.copy(success = true)
                    } else {
                        _createAccountUiState.value = _createAccountUiState.value.copy(
                            fieldErrors = _createAccountUiState.value.fieldErrors + (
                                    "createBottom" to task.exception?.message)
                        )
                        Log.d("MSF", "Sign up error", task.exception)
                        _createAccountUiState.value = _createAccountUiState.value.copy(success = false)
                    }
                }
        }
    }

    private fun createAccountValidation(username: String, email: String, p1: String, p2: String): Boolean{
        validateRequired("createUser", username)
        validateRequired("createEmail", email)
        validateRequired("createPass1", p1)
        validateRequired("createPass2", p2)

        if(_createAccountUiState.value.fieldErrors["createEmail"] == null){
            validateEmail(email)
        }

        if(_createAccountUiState.value.fieldErrors["createPass1"] == null &&
            _createAccountUiState.value.fieldErrors["createPass2"] == null){
            validatePasswordMatch(p1, p2)
            if(_createAccountUiState.value.fieldErrors["createBottom"] == null){
                validatePasswordLength(p1)
            }
        }

        val fieldErrors = _createAccountUiState.value.fieldErrors
        return fieldErrors["createUser"] == null && fieldErrors["createEmail"] == null
                && fieldErrors["createPass1"] == null && fieldErrors["createPass2"] == null
                && fieldErrors["createBottom"] == null
    }


    private fun validateRequired(input: String, inputValue: String){
        val errorText = if (inputValue.isEmpty()) "This field is required." else null
        _createAccountUiState.value = _createAccountUiState.value.copy(
            fieldErrors = _createAccountUiState.value.fieldErrors + (input to errorText))
    }

    private fun validateEmail(email: String){
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$", RegexOption.IGNORE_CASE)
        val errorText = if (!regex.matches(email)) "Please enter a valid email address." else null
            _createAccountUiState.value = _createAccountUiState.value.copy(
                fieldErrors = _createAccountUiState.value.fieldErrors +
                        ("createEmail" to errorText))
    }

    private fun validatePasswordMatch(p1: String, p2: String){
        val errorText = if(p1 != p2) "Passwords do not match." else null
        _createAccountUiState.value = _createAccountUiState.value.copy(fieldErrors = _createAccountUiState.value.fieldErrors +
                        ("createBottom" to errorText))
    }

    private fun validatePasswordLength(password: String){
        val errorText = if (password.length < MIN_PASSWORD_LENGTH)
            "Password must be at least $MIN_PASSWORD_LENGTH characters." else null
            _createAccountUiState.value = _createAccountUiState.value.copy(fieldErrors = _createAccountUiState.value.fieldErrors +
                        ("createBottom" to errorText))
    }

    suspend fun resetState(){
        delay(500)
        _createAccountUiState.value = CreateAccountUiState()
    }
}