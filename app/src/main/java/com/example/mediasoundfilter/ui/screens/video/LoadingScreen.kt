package com.example.mediasoundfilter.ui.screens.video

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.mediasoundfilter.ui.screens.video.upload.UploadViewModel

@Composable
fun LoadingScreen(uploadViewModel: UploadViewModel, navController: NavController){

    val uploadUiState = uploadViewModel.uploadUiState.collectAsState()

    val uploadSuccessful = uploadUiState.value.uploadSuccessful
    LaunchedEffect(uploadSuccessful){
        if(uploadSuccessful){
            navController.navigate("sounds")
            uploadViewModel.resetState()
        }
    }

    Text(
        text = "Please wait while your selected video is scanned for trigger sounds."
    )
}