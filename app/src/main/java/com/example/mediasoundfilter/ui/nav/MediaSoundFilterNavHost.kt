package com.example.mediasoundfilter.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mediasoundfilter.ui.screens.account.AccountScreen
import com.example.mediasoundfilter.ui.screens.media.MediaScreen
import com.example.mediasoundfilter.ui.screens.media.MediaViewModel
import com.example.mediasoundfilter.ui.screens.search.SearchScreen
import com.example.mediasoundfilter.ui.screens.upload.SoundsScreen
import com.example.mediasoundfilter.ui.screens.upload.UploadScreen
import com.example.mediasoundfilter.ui.screens.upload.UploadViewModel

@Composable
fun MediaSoundFilterNavHost(
    uploadViewModel: UploadViewModel,
    mediaViewModel: MediaViewModel,
    navController: NavHostController
) {
   NavHost(
       navController = navController,
       startDestination = "upload"
   ) {
       composable("upload") {
           UploadScreen(uploadViewModel, navController)
       }
       composable("account") {
           AccountScreen(navController)
       }
       composable("search") {
           SearchScreen(navController)
       }
       composable("sounds"){
           SoundsScreen(uploadViewModel, navController)
       }
       composable("media/{videoId}") { backStackEntry ->
           val videoId = backStackEntry.arguments?.getString("videoId")
           MediaScreen(mediaViewModel, navController, videoId)
       }
   }
}
