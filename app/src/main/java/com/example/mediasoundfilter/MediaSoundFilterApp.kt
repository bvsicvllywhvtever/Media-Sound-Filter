package com.example.mediasoundfilter

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.mediasoundfilter.nav.MediaSoundFilterNavHost

@Composable
fun MediaSoundFilterApp() {
    val navController = rememberNavController()
    MediaSoundFilterNavHost(navController = navController)
}