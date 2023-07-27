package com.example.mediasoundfilter.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mediasoundfilter.ui.screens.account.AccountScreen
import com.example.mediasoundfilter.ui.screens.media.MediaScreen
import com.example.mediasoundfilter.ui.screens.media.MediaViewModel
import com.example.mediasoundfilter.ui.screens.media.SoundsScreen
import com.example.mediasoundfilter.ui.screens.media.UploadScreen
import com.example.mediasoundfilter.ui.screens.search.SearchScreen

@Composable
fun MediaSoundFilterNavHost(
    mediaViewModel: MediaViewModel,
    navController: NavHostController
) {
   NavHost(
       navController = navController,
       startDestination = "upload"
   ) {
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
