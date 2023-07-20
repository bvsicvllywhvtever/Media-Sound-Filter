package com.example.mediasoundfilter.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mediasoundfilter.ui.screens.auth.create_account.CreateAccountScreen
import com.example.mediasoundfilter.ui.screens.auth.login.LoginScreen
import com.example.mediasoundfilter.ui.screens.auth.AuthViewModel

@Composable
fun AuthNavHost(authViewModel: AuthViewModel, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ){
        composable("login") {
            LoginScreen (authViewModel, navController)
        }
        composable("createAccount") {
            CreateAccountScreen(authViewModel, navController)
        }
    }
}