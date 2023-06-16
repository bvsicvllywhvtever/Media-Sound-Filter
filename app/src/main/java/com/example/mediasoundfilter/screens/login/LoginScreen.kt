package com.example.mediasoundfilter.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediasoundfilter.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navigateToUpload: () -> Unit, navigateToCreateAccount: () -> Unit) {
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
            var userValue by remember {mutableStateOf("")}
            OutlinedTextField(
                value = userValue,
                onValueChange = { userValue = it },
                label = {Text(stringResource(R.string.username))},
                leadingIcon = {
                    Image(
                        painter = painterResource(R.drawable.account),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(colorResource(R.color.main))
                    )},
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(10.dp)
            )
            var passValue by remember { mutableStateOf("") }
            OutlinedTextField(
                value = passValue,
                onValueChange = {passValue = it},
                label = {Text(stringResource(R.string.password))},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = colorResource(R.color.main)
                    )},
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(10.dp)
            )
        }
        Button(
            onClick = navigateToUpload,
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
                modifier = Modifier.clickable(onClick = navigateToCreateAccount)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen({}, {})
}