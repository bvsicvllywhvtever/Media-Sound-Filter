package com.example.mediasoundfilter.screens.upload

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediasoundfilter.R
import com.example.mediasoundfilter.nav.NavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadScreen(navRoutes: Array<() -> Unit>) {
    Scaffold(
        content = { padding ->
            Column (modifier = Modifier.padding(padding)) {
                Text(
                    text = stringResource(R.string.upload_message)
                )
                TextField(
                    value = "",
                    onValueChange = {}
                )
                Button(
                    onClick = {}
                ) {
                    Text(stringResource(R.string.upload))
                }
            }
        },
        bottomBar = {NavBar(navRoutes)}
    )
}

@Preview(showBackground = true)
@Composable
fun UploadScreenPreview() {
    UploadScreen(arrayOf())
}