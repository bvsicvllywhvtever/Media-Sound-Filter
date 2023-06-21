package com.example.mediasoundfilter.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mediasoundfilter.screens.create_account.CreateAccountScreen
import com.example.mediasoundfilter.screens.login.LoginScreen
import com.example.mediasoundfilter.viewmodel.AuthViewModel

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
            CreateAccountScreen() {
                navController.navigate("login")
            }
        }
    }
}