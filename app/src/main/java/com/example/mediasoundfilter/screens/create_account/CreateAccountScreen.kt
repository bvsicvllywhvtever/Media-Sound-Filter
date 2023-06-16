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
import com.example.mediasoundfilter.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(navigateToLogin: () -> Unit) {
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
        var userValue by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            OutlinedTextField(
                value = userValue,
                onValueChange = { userValue = it },
                label = { Text(stringResource(R.string.username)) },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(10.dp)
            )
            var emailValue by remember { mutableStateOf("") }
            OutlinedTextField(
                value = emailValue,
                onValueChange = { emailValue = it },
                label = { Text(stringResource(R.string.email)) },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(10.dp)
            )
            var passValue by remember { mutableStateOf("") }
            OutlinedTextField(
                value = passValue,
                onValueChange = { passValue = it },
                label = { Text(stringResource(R.string.password)) },
                shape = RoundedCornerShape(10.dp),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.padding(10.dp)
            )
            var confirmValue by remember { mutableStateOf("") }
            OutlinedTextField(
                value = confirmValue,
                onValueChange = { confirmValue = it },
                label = { Text(stringResource(R.string.confirm_password)) },
                shape = RoundedCornerShape(10.dp),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.padding(10.dp)
            )
        }

        Button(
            onClick = navigateToLogin,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.main))
        ) {
            Text(stringResource(R.string.create_account))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateAccountScreenPreview() {
    CreateAccountScreen({})
}