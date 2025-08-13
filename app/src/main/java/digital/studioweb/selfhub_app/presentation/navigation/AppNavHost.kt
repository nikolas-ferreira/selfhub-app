package digital.studioweb.selfhub_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import digital.studioweb.selfhub_app.presentation.features.activation.ActivationScreen
import digital.studioweb.selfhub_app.presentation.features.home.HomeScreen
import digital.studioweb.selfhub_app.presentation.features.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.name,
        modifier = modifier
    ) {
        composable(AppScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(AppScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(AppScreens.ActivationScreen.name) {
            ActivationScreen(navController = navController)
        }
    }
}