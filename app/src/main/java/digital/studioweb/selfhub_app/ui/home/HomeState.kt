package digital.studioweb.selfhub_app.ui.home

sealed class HomeState {
    object Loading : HomeState()
    object Success : HomeState()
    object Error : HomeState()
}