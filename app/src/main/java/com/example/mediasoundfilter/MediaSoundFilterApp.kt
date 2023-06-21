package com.example.mediasoundfilter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.mediasoundfilter.nav.AuthNavHost
import com.example.mediasoundfilter.nav.MediaSoundFilterNavHost
import com.example.mediasoundfilter.viewmodel.AuthViewModel

@Composable
fun MediaSoundFilterApp() {
    //nav controller
    val navController = rememberNavController()

    //auth view model and state
    val authViewModel: AuthViewModel = viewModel()
    val authUiState = authViewModel.authUiState.collectAsState()

    //check if logged in, navigate to right page
    if(authUiState.value.loggedIn){
        MediaSoundFilterNavHost(navController = navController)
    }
    else{
        AuthNavHost(authViewModel, navController)
    }

}