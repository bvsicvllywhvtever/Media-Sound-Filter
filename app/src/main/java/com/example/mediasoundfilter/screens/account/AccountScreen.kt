package com.example.mediasoundfilter.screens.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.mediasoundfilter.R

@Composable
fun AccountScreen() {
    Column() {
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
}