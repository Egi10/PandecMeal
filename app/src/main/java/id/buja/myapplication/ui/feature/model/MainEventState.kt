package id.buja.myapplication.ui.feature.model

sealed class MainEventState {
    data class SearchOnChange(val search: String): MainEventState()

    object Search : MainEventState()
}
