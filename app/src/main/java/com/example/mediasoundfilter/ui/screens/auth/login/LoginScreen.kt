package com.example.mediasoundfilter.ui.screens.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mediasoundfilter.R
import com.example.mediasoundfilter.ui.screens.error.BottomErrorText
import com.example.mediasoundfilter.ui.screens.error.ErrorText
import com.example.mediasoundfilter.ui.screens.auth.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(authViewModel: AuthViewModel, navController: NavController) {

    var emailValue by remember {mutableStateOf("")}
    var passValue by remember { mutableStateOf("") }

    //check if logged in
    val authUiState = authViewModel.authUiState.collectAsState()
    if(authUiState.value.currentUser != null){
        navController.navigate("upload")
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.title),
            fontSize = 25.sp,
            modifier = Modifier
        )
        Column(
            modifier = Modifier.padding(15.dp)
        ) {

            OutlinedTextField(
                value = emailValue,
                onValueChange = { emailValue = it },
                isError = authUiState.value.fieldErrors["loginEmail"] != null,
                label = {Text(stringResource(R.string.email))},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint =
                        if (authUiState.value.fieldErrors["loginEmail"] == null){
                            colorResource(R.color.main)
                        }
                        else{
                            colorResource(R.color.error)
                        }
                    )},
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(10.dp)
            )

            //show required email error message
            authUiState.value.fieldErrors["loginEmail"]?.let { ErrorText(it) }

            OutlinedTextField(
                value = passValue,
                onValueChange = {passValue = it},
                isError = authUiState.value.fieldErrors["loginPass"] != null,
                label = {Text(stringResource(R.string.password))},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = if (authUiState.value.fieldErrors["loginPass"] == null){
                            colorResource(R.color.main)
                        }
                        else{
                            colorResource(R.color.error)
                        }
                    )},
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(10.dp)
            )

            //show required password error
            authUiState.value.fieldErrors["loginPass"]?.let { ErrorText(it) }
        }
        Button(
            onClick = {
                authViewModel.login(emailValue, passValue)
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.main))
        ){
            Text(stringResource(R.string.login))
        }
        Row (modifier = Modifier.padding(15.dp)) {
            Text(
                text = stringResource(R.string.sign_up_message),
                modifier = Modifier.padding(0.dp, 0.dp, 3.dp, 0.dp)
            )
            Text(
                text = stringResource(R.string.sign_up_link),
                color = colorResource(R.color.link),
                modifier = Modifier.clickable(onClick = {navController.navigate("createAccount")})
            )
        }

        //show invalid login error
        authUiState.value.fieldErrors["loginBottom"]?.let { BottomErrorText(it) }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(viewModel(), rememberNavController())
}