package com.example.mediasoundfilter.screens.create_account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mediasoundfilter.R
import com.example.mediasoundfilter.screens.error.BottomErrorText
import com.example.mediasoundfilter.screens.error.ErrorText
import com.example.mediasoundfilter.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(authViewModel: AuthViewModel, navController: NavHostController) {
    var userValue by remember { mutableStateOf("") }
    var emailValue by remember { mutableStateOf("") }
    var passValue by remember { mutableStateOf("") }
    var confirmValue by remember { mutableStateOf("") }

    val authUiState = authViewModel.authUiState.collectAsState()

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        Text(
            text = stringResource(R.string.title),
            fontSize = 25.sp
        )


        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            OutlinedTextField(
                value = userValue,
                onValueChange = { userValue = it },
                isError = authUiState.value.fieldErrors["createUser"] != null,
                label = { Text(stringResource(R.string.username)) },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(10.dp)
            )
            authUiState.value.fieldErrors["createUser"]?.let{ ErrorText(it) }

            OutlinedTextField(
                value = emailValue,
                onValueChange = { emailValue = it },
                isError = authUiState.value.fieldErrors["createEmail"] != null,
                label = { Text(stringResource(R.string.email)) },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(10.dp)
            )
            authUiState.value.fieldErrors["createEmail"]?.let { ErrorText(it) }

            OutlinedTextField(
                value = passValue,
                onValueChange = { passValue = it },
                isError = authUiState.value.fieldErrors["createPass1"] != null,
                label = { Text(stringResource(R.string.password)) },
                shape = RoundedCornerShape(10.dp),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.padding(10.dp)
            )
            authUiState.value.fieldErrors["createPass1"]?.let{ ErrorText(it) }

            OutlinedTextField(
                value = confirmValue,
                onValueChange = { confirmValue = it },
                isError = authUiState.value.fieldErrors["createPass2"] != null,
                label = { Text(stringResource(R.string.confirm_password)) },
                shape = RoundedCornerShape(10.dp),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.padding(10.dp)
            )
            authUiState.value.fieldErrors["createPass2"]?.let{ ErrorText(it) }
        }

        Button(
            onClick = {
                authViewModel.createAccount(userValue, emailValue, passValue, confirmValue)
                if (authUiState.value.createAccountSuccess){
                    navController.navigate("login")
                }
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.main))
        ) {
            Text(stringResource(R.string.create_account))
        }

        authUiState.value.fieldErrors["createBottom"]?.let{ BottomErrorText(it) }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateAccountScreenPreview() {
    //CreateAccountScreen({})
}