package id.buja.myapplication.ui.feature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buja.myapplication.domain.usecase.GetMealsByCategoryUseCase
import id.buja.myapplication.ui.feature.model.MainEventState
import id.buja.myapplication.ui.feature.model.MainUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val getMealsByCategoryUseCase: GetMealsByCategoryUseCase
) : ViewModel() {
    var search by mutableStateOf("")
        private set

    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.Idle)
    val uiState get() = _uiState.asStateFlow()

    fun onEvent(event: MainEventState) {
        when(event) {
            is MainEventState.SearchOnChange -> {
                search = event.search
            }

            MainEventState.Search -> {
                getMealsByCategory()
            }
        }
    }

    private fun getMealsByCategory() {
        viewModelScope.launch {
            getMealsByCategoryUseCase.invoke(
                category = search
            ).onStart {
                _uiState.update {
                    MainUiState.Loading
                }
            }.catch { throwable ->
                _uiState.update {
                    MainUiState.Error(
                        error = throwable.message ?: "Data Error"
                    )
                }
            }.collectLatest { data ->
                if (data.isEmpty()) {
                    _uiState.update {
                        MainUiState.Empty
                    }
                } else {
                    _uiState.update {
                        MainUiState.Success(
                            data = data
                        )
                    }
                }
            }
        }
    }
}