package com.example.mediasoundfilter.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mediasoundfilter.screens.account.AccountScreen
import com.example.mediasoundfilter.screens.media.MediaScreen
import com.example.mediasoundfilter.screens.search.SearchScreen
import com.example.mediasoundfilter.screens.upload.UploadScreen
import com.example.mediasoundfilter.viewmodel.UploadViewModel

@Composable
fun MediaSoundFilterNavHost(
    uploadViewModel: UploadViewModel,
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
       composable("media/{videoId}") { backStackEntry ->
           val videoId = backStackEntry.arguments?.getString("videoId")
           MediaScreen(navController, videoId)
       }
   }
}
