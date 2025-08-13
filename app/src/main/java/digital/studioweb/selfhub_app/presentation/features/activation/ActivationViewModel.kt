package digital.studioweb.selfhub_app.presentation.features.activation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import digital.studioweb.selfhub_app.domain.features.auth.AssociateDeviceUseCase
import digital.studioweb.selfhub_app.domain.features.auth.GetRestaurantByCNPJUseCase
import digital.studioweb.selfhub_app.domain.features.auth.SaveCNPJUseCase
import digital.studioweb.selfhub_app.presentation.features.activation.models.ActivationEvents
import digital.studioweb.selfhub_app.presentation.features.activation.models.ActivationUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class ActivationViewModel @Inject constructor(
    private val getRestaurantByCNPJUseCase: GetRestaurantByCNPJUseCase,
    private val associateDeviceUseCase: AssociateDeviceUseCase,
    private val saveCNPJUseCase: SaveCNPJUseCase
) : ViewModel() {

    var uiState by mutableStateOf(ActivationUIState())
        private set

    private val _uiEvent = Channel<ActivationEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ActivationEvents) {
        when (event) {
            is ActivationEvents.OnCnpjChanged -> {
                val cleaned = event.value.filter { it.isDigit() }
                val isValid = cleaned.length == 14
                uiState = uiState.copy(
                    cnpj = event.value,
                    isCNPJValid = isValid,
                    isCNPJSubmitted = false,
                )
            }

            ActivationEvents.OnSubmitCNPJ -> {
                uiState = uiState.copy(
                    isLoading = true
                )
                activate()

            }
            ActivationEvents.GoToHome -> goToHome()
        }
    }

    @VisibleForTesting
    fun activate() {
        val params = GetRestaurantByCNPJUseCase.Params(
            cnpj = uiState.cnpj
        )
        viewModelScope.launch(Dispatchers.IO) {
            getRestaurantByCNPJUseCase.runAsync(params)
                .onSuccess { response ->
                    associateDevice()
                }
                .onFailure { exception ->
                    if (exception.message!!.contains("404")) {
                        uiState.copy(
                            errorSnackBarMessage = "Ops! Parece que não temos um restaurante cadastrado com esse CNPJ em nossa base. Entre em contato pelo fone (51) 3365-9407",
                        )
                    }
                    uiState = uiState.copy(
                        isLoading = false,
                        isCNPJValid = false
                    )
                }
        }
    }

    @VisibleForTesting
    fun associateDevice() {
        val params = AssociateDeviceUseCase.Params(
            cnpj = uiState.cnpj,
            macAddress = "mac123" 
        )
        viewModelScope.launch(Dispatchers.IO) {
            associateDeviceUseCase.runAsync(params)
                .onSuccess {
                    val params = SaveCNPJUseCase.Params(
                        cnpj = uiState.cnpj
                    )
                    saveCNPJUseCase.runSync(params)
                    goToHome()
                }
                .onFailure {}

        }
    }

    @VisibleForTesting
    fun goToHome() {
        viewModelScope.launch {
            _uiEvent.send(ActivationEvents.GoToHome)
        }
    }
}
