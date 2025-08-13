package digital.studioweb.selfhub_app.presentation.features.activation.models

sealed class ActivationEvents {
    data class OnCnpjChanged(val value: String) : ActivationEvents()
    object OnSubmitCNPJ : ActivationEvents()
    object GoToHome : ActivationEvents()
}
