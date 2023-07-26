package com.example.mediasoundfilter.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.mediasoundfilter.ui.nav.AuthNavHost
import com.example.mediasoundfilter.ui.nav.MediaSoundFilterNavHost
import com.example.mediasoundfilter.ui.screens.auth.AuthViewModel
import com.example.mediasoundfilter.ui.screens.media.MediaViewModel
import com.example.mediasoundfilter.ui.screens.upload.UploadViewModel

@Composable
fun MediaSoundFilterApp() {
    //nav controller
    val navController = rememberNavController()

    //auth view model and state
    val authViewModel: AuthViewModel = viewModel()
    val authUiState = authViewModel.authUiState.collectAsState()

    val uploadViewModel: UploadViewModel = viewModel()
    val mediaViewModel: MediaViewModel = viewModel()

    //check if logged in, navigate to right page
    if(authUiState.value.currentUser != null){
        MediaSoundFilterNavHost(uploadViewModel, mediaViewModel, navController)
    }
    else{
        AuthNavHost(authViewModel, navController)
    }

}