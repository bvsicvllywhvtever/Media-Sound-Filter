package com.example.mediasoundfilter.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.mediasoundfilter.R

@Composable
fun ErrorText(errorMessage: String){
    Text(
        text = errorMessage,
        color = colorResource(R.color.error)
    )
}