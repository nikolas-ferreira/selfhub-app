package digital.studioweb.selfhub_app.presentation.features.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import digital.studioweb.selfhub_app.R
import digital.studioweb.selfhub_app.presentation.features.splash.models.SplashEvents
import digital.studioweb.selfhub_app.presentation.features.splash.models.SplashUIState

@Composable
fun SplashScreen(
    navController: NavController
) {
    val viewModel: SplashViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.init(navController = navController)
    }

    SplashScreenContent(
        navController = navController,
        uiState = viewModel.uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun SplashScreenContent(
    navController: NavController,
    uiState: SplashUIState,
    onEvent: (SplashEvents) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.app_background)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = null,
            modifier = Modifier.size(180.dp)
        )
    }
}
