package com.example.mediasoundfilter.ui

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.mediasoundfilter.ui.nav.MediaSoundFilterNavHost
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MediaSoundFilterApp : Application(){}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    MediaSoundFilterNavHost(navController)
}