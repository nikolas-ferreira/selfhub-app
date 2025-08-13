package digital.studioweb.selfhub_app.presentation.features.activation.models

data class ActivationUIState(
    val isLoading: Boolean = false,
    val isCNPJValid: Boolean = false,
    val isCNPJSubmitted: Boolean = false,
    val errorSnackBarMessage: String = "",
    val cnpj: String = ""
)
