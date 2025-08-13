package digital.studioweb.selfhub_app.presentation.features.activation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import digital.studioweb.selfhub_app.R
import digital.studioweb.selfhub_app.presentation.features.activation.components.ActivationEditText
import digital.studioweb.selfhub_app.presentation.features.activation.models.ActivationEvents
import digital.studioweb.selfhub_app.presentation.features.activation.models.ActivationUIState
import digital.studioweb.selfhub_app.presentation.navigation.AppScreens
import kotlinx.coroutines.launch

@Composable
fun ActivationScreen(
    navController: NavController
) {
    val viewModel: ActivationViewModel = hiltViewModel()
    ActivationScreenContent(
        uiState = viewModel.uiState,
        onEvent = viewModel::onEvent
    )

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is ActivationEvents.GoToHome -> {
                    navController.navigate(AppScreens.HomeScreen.name) {
                        popUpTo(AppScreens.SplashScreen.name) { inclusive = true }
                        launchSingleTop = true
                    }
                }

                else -> {}
            }
        }
    }
}

@Composable
private fun ActivationScreenContent(
    onEvent: (ActivationEvents) -> Unit,
    uiState: ActivationUIState
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.errorSnackBarMessage) {
        if (uiState.errorSnackBarMessage.isNotBlank()) {
            coroutineScope.launch {
                snackBarHostState.showSnackbar(uiState.errorSnackBarMessage)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.app_background)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 24.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.app_background)
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Digite o CNPJ para ativar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.white)
                )

                Spacer(modifier = Modifier.height(16.dp))

                ActivationEditText(
                    value = uiState.cnpj,
                    onValueChange = { onEvent(ActivationEvents.OnCnpjChanged(it)) },
                    isError = (uiState.cnpj.isNotEmpty() && !uiState.isCNPJValid),
                    enabled = !uiState.isLoading
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onEvent(ActivationEvents.OnSubmitCNPJ) },
                    enabled = uiState.isCNPJValid && !uiState.isLoading,
                    modifier = Modifier.widthIn(min = 160.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.primary_orange)
                    ),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    Text(
                        text = "Ativar",
                        color = colorResource(R.color.white),
                        fontSize = 16.sp
                    )
                }
            }
        }

        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            snackbar = { data ->
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = colorResource(id = R.color.error_wine))
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Erro",
                        tint = colorResource(R.color.white),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = data.visuals.message,
                        color = colorResource(R.color.white),
                        fontSize = 14.sp
                    )
                }
            }
        )
    }
}
