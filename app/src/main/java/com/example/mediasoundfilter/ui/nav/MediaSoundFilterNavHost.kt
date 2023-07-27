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
import com.example.mediasoundfilter.ui.screens.media.MediaScreen
import com.example.mediasoundfilter.ui.screens.media.MediaViewModel
import com.example.mediasoundfilter.ui.screens.media.SoundsScreen
import com.example.mediasoundfilter.ui.screens.media.UploadScreen
import com.example.mediasoundfilter.ui.screens.search.SearchScreen

@Composable
fun MediaSoundFilterNavHost(
    navController: NavHostController
) {

    val mediaViewModel: MediaViewModel = viewModel()

    NavHost(
       navController = navController,
       startDestination = "login"
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
           UploadScreen(mediaViewModel, navController)
       }
       composable("account") {
           AccountScreen(navController)
       }
       composable("search") {
           SearchScreen(navController)
       }
       composable("sounds"){
           SoundsScreen(mediaViewModel, navController)
       }
       composable("media/{videoId}") { backStackEntry ->
           val videoId = backStackEntry.arguments?.getString("videoId")
           MediaScreen(mediaViewModel, navController, videoId)
       }
   }
}
