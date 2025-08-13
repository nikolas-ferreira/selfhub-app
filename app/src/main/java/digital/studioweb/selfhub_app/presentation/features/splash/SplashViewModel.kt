package digital.studioweb.selfhub_app.presentation.features.splash

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import digital.studioweb.selfhub_app.domain.features.auth.GetCNPJUseCase
import digital.studioweb.selfhub_app.presentation.features.splash.models.SplashEvents
import digital.studioweb.selfhub_app.presentation.features.splash.models.SplashUIState
import digital.studioweb.selfhub_app.presentation.navigation.AppScreens
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getCNPJUseCase: GetCNPJUseCase
) : ViewModel() {
    //region Properties

    var uiState by mutableStateOf(SplashUIState())
        private set

    //endregion

    //region Public Functions

    suspend fun init(navController: NavController) {
        getCNPJ(navController = navController)
    }

    fun onEvent(event: SplashEvents) {

    }

    //endregion

    //region Visible for Testing

    @VisibleForTesting
    suspend fun getCNPJ(navController: NavController) {
        delay(2000)
        val cnpj = getCNPJUseCase.runSync()
        if (cnpj.isNotEmpty()) {
            goToHome(navController = navController)
        } else {
            goToActivation(navController = navController)
        }
    }

    private fun goToActivation(navController: NavController) {
        navController.navigate(AppScreens.ActivationScreen.name) {
            popUpTo(AppScreens.SplashScreen.name) { inclusive = true }
            launchSingleTop = true
        }
    }

    private fun goToHome(navController: NavController) {
        navController.navigate(AppScreens.HomeScreen.name) {
            popUpTo(AppScreens.SplashScreen.name) { inclusive = true }
            launchSingleTop = true
        }
    }


    //endregion
}