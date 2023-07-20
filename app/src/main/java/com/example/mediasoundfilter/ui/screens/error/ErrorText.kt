package com.example.mediasoundfilter.ui.screens.error

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.mediasoundfilter.R

@Composable
fun ErrorText(errorMessage: String){
    Text(
        text = errorMessage,
        color = colorResource(R.color.error),
        modifier = Modifier.padding(10.dp, 0.dp)
    )
}