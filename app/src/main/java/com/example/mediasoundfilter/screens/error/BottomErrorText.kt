package com.example.mediasoundfilter.screens.error

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mediasoundfilter.R

@Composable
fun BottomErrorText(errorMessage: String){
    Text(
        text = errorMessage,
        textAlign = TextAlign.Center,
        color = colorResource(R.color.error),
        modifier = Modifier.padding(20.dp, 10.dp)
    )
}