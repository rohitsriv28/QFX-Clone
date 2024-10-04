package com.example.qfxclone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.qfxclone.presentation.AuthViewModel
import com.example.qfxclone.ui.screens.ComingSoon
import com.example.qfxclone.ui.screens.DashboardScreen
import com.example.qfxclone.ui.screens.NowShowing
import com.example.qfxclone.ui.screens.SignInScreen
import com.example.qfxclone.ui.screens.SignupScreen

@Composable
fun AppNavigation(navHost: NavHostController) {
    NavHost(navController = navHost, startDestination = Routes.LOGIN.path) {
        composable(route = Routes.LOGIN.path) {
            val viewModel: AuthViewModel = hiltViewModel()
            SignInScreen(navHost, viewModel)
        }
        composable(route = Routes.SIGNUP.path) {
            val viewModel: AuthViewModel = hiltViewModel()
            SignupScreen(navHost, viewModel)
        }

        navigation(startDestination = Routes.HOME.path, route = "home") {
            /*composable(route = Routes.HOME.path) {
                HomePage(navigate = navHost::customNavigate)
            }*/
            composable(route = Routes.HOME.path) {
                DashboardScreen(navigate = navHost::customNavigate)
            }
            composable(route = Routes.NOW.path) {
                NowShowing(navigate = navHost::customNavigate)
            }
            composable(route = Routes.COMING_SOON.path) {
                ComingSoon()
            }
        }
    }
}


enum class Routes(val path: String) {
    LOGIN("signIn"),
    SIGNUP("signUp"),
    HOME("homePage"),
    NOW("nowShowing"),
    COMING_SOON("comingSoon")

}

fun NavHostController.customNavigate(param: String, popup: Boolean = false) {
    if (popup) {
        this.popBackStack()
    }
    this.navigate(param)
}