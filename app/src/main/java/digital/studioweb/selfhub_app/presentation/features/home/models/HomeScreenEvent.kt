package digital.studioweb.selfhub_app.presentation.features.home.models

sealed class HomeScreenEvent {
    data class OnCategorySelected(val index: Int) : HomeScreenEvent()
    data class OnSearchTextChanged(val text: String) : HomeScreenEvent()
    data class OnSidebarItemSelected(val index: Int) : HomeScreenEvent()
    object OnCartClick : HomeScreenEvent()
}
