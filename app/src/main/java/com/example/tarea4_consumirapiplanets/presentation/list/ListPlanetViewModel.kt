package com.example.tarea4_consumirapiplanets.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarea4_consumirapiplanets.data.remote.Resource
import com.example.tarea4_consumirapiplanets.domain.usecase.Planet.GetPlanetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListPlanetViewModel @Inject constructor(
    private val getPlanetUseCase: GetPlanetUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ListPlanetUiState())
    val state = _state.asStateFlow()

    init {
        loadPlanets()
    }

    fun onEvent(event: ListPlanetUiEvent) {
        when (event) {
            is ListPlanetUiEvent.UpdateFilters -> {
                _state.update {
                    it.copy(
                        nameFilter = event.name,
                        isDestroyedFilter = event.isDestroyed
                    )
                }
            }
            ListPlanetUiEvent.Search -> loadPlanets()
        }
    }

    private fun loadPlanets() {
        viewModelScope.launch {
            val current = _state.value

            getPlanetUseCase(
                name = current.nameFilter.takeIf { it.isNotBlank() },
                isDestroyed = current.isDestroyedFilter
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                planets = result.data ?: emptyList()
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                }
            }
        }
    }
}