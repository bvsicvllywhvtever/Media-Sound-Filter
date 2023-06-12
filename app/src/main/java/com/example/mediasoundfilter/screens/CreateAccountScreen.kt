package com.example.mediasoundfilter.screens

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.mediasoundfilter.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(navigateToLogin: () -> Unit) {
    Text(
        text = stringResource(R.string.title)
    )
    TextField(
        value = "",
        onValueChange = {}
    )
    TextField(
        value = "",
        onValueChange = {}
    )
    TextField(
        value = "",
        onValueChange = {}
    )
    TextField(
        value = "",
        onValueChange = {}
    )
    Button(
        onClick = {}
    ) {
        Text(stringResource(R.string.create_account))
    }
}