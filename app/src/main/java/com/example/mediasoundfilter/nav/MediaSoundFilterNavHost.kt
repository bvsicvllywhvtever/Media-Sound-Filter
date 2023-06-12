package com.example.mediasoundfilter.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.mediasoundfilter.screens.create_account.CreateAccountScreen
import com.example.mediasoundfilter.screens.login.LoginScreen
import com.example.mediasoundfilter.screens.upload.UploadScreen

@Composable
fun MediaSoundFilterNavHost(
    navController: NavHostController
) {
   NavHost(
       navController = navController,
       startDestination = "login"
   ) {
       composable("login") {
           LoginScreen (
               {navController.navigate("upload") },
               {navController.navigate("createAccount") }
           )
       }
       composable("createAccount") {
           CreateAccountScreen() {
               navController.navigate("login")
           }
       }
       composable("upload") {
           UploadScreen()
       }
       /*composable("account") {
           AccountScreen()
       }
       composable("search") {
           SearchScreen()
       }*/
   }
}
