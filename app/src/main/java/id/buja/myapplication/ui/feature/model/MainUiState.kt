package id.buja.myapplication.ui.feature.model

import id.buja.myapplication.domain.model.Meals

sealed interface MainUiState {
    object Idle: MainUiState
    object Loading: MainUiState
    object Empty: MainUiState

    data class Success(val data: List<Meals>): MainUiState
    data class Error(val error: String): MainUiState
}