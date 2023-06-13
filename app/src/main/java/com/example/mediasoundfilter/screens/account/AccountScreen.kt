package com.example.mediasoundfilter.screens.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediasoundfilter.R
import com.example.mediasoundfilter.nav.NavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen() {
    Scaffold(
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                Text(
                    text = "insert dynamic content here"
                )
                Text(
                    text = stringResource(R.string.saved)
                )
                //dynamic block
                Row() {
//            Image(
//                painter = painterResource(null),
//                contentDescription = null
//            )
                    Text("insert more dynamic content here")
                }
            }
        },
        bottomBar = {NavBar()}
    )
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    AccountScreen()
}