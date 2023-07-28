package com.example.mediasoundfilter.ui.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mediasoundfilter.ui.screens.account.AccountScreen
import com.example.mediasoundfilter.ui.screens.auth.create_account.CreateAccountScreen
import com.example.mediasoundfilter.ui.screens.auth.create_account.CreateAccountViewModel
import com.example.mediasoundfilter.ui.screens.auth.login.LoginScreen
import com.example.mediasoundfilter.ui.screens.auth.login.LoginViewModel
import com.example.mediasoundfilter.ui.screens.search.SearchScreen
import com.example.mediasoundfilter.ui.screens.video.VideoViewModel
import com.example.mediasoundfilter.ui.screens.video.media.MediaScreen
import com.example.mediasoundfilter.ui.screens.video.media.MediaViewModel
import com.example.mediasoundfilter.ui.screens.video.sounds.SoundsScreen
import com.example.mediasoundfilter.ui.screens.video.sounds.SoundsViewModel
import com.example.mediasoundfilter.ui.screens.video.upload.UploadScreen
import com.example.mediasoundfilter.ui.screens.video.upload.UploadViewModel

@Composable
fun MediaSoundFilterNavHost(
    navController: NavHostController
) {

    val videoViewModel: VideoViewModel = viewModel()

    NavHost(
       navController = navController,
       startDestination = "upload"
    ) {
       composable("login") {
           val loginViewModel: LoginViewModel = viewModel()
           LoginScreen (loginViewModel, navController)
       }
       composable("createAccount") {
           val createAccountViewModel: CreateAccountViewModel = viewModel()
           CreateAccountScreen(createAccountViewModel, navController)
       }
       composable("upload") {
           val uploadViewModel: UploadViewModel = viewModel()
           UploadScreen(videoViewModel, uploadViewModel, navController)
       }
       composable("account") {
           AccountScreen(navController)
       }
       composable("search") {
           SearchScreen(navController)
       }
       composable("sounds"){
           val soundsViewModel: SoundsViewModel = viewModel()
           SoundsScreen(videoViewModel, soundsViewModel, navController)
       }
       composable("media/{videoId}") { backStackEntry ->
           val mediaViewModel : MediaViewModel = viewModel()
           val videoId = backStackEntry.arguments?.getString("videoId")
           MediaScreen(videoViewModel, mediaViewModel, navController, videoId)
       }
   }
}
