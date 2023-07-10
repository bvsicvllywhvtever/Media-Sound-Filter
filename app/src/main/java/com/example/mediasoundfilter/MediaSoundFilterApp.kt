package com.example.mediasoundfilter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.mediasoundfilter.nav.AuthNavHost
import com.example.mediasoundfilter.nav.MediaSoundFilterNavHost
import com.example.mediasoundfilter.viewmodel.AuthViewModel
import com.example.mediasoundfilter.viewmodel.UploadViewModel

@Composable
fun MediaSoundFilterApp() {
    //nav controller
    val navController = rememberNavController()

    //auth view model and state
    val authViewModel: AuthViewModel = viewModel()
    val authUiState = authViewModel.authUiState.collectAsState()

    val uploadViewModel: UploadViewModel = viewModel()

    //check if logged in, navigate to right page
    if(authUiState.value.currentUser != null){
        MediaSoundFilterNavHost(uploadViewModel, navController)
    }
    else{
        AuthNavHost(authViewModel, navController)
    }

}