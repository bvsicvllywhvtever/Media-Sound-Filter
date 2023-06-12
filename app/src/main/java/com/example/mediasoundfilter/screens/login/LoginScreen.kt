package com.example.mediasoundfilter.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediasoundfilter.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navigateToUpload: () -> Unit, navigateToCreateAccount: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.title),
            fontSize = 25.sp,
            modifier = Modifier
        )
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            TextField(
                value = "",
                onValueChange = {},
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(15.dp)
            )
            TextField(
                value = "",
                onValueChange = {},
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(15.dp)
            )
        }
        Button(
            onClick = navigateToUpload,
            shape = RoundedCornerShape(10.dp)
        ){
            Text(stringResource(R.string.login))
        }
        Row {
            Text(
                text = stringResource(R.string.sign_up_message),
                modifier = Modifier.padding(15.dp)
            )
            Text(
                text = stringResource(R.string.sign_up_link),
                modifier = Modifier.clickable(onClick = navigateToCreateAccount)
            )
        }
    }
}