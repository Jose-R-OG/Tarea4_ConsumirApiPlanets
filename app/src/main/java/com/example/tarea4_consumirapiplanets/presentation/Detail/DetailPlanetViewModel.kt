package com.example.tarea4_consumirapiplanets.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.tarea4_consumirapiplanets.data.remote.Resource
import com.example.tarea4_consumirapiplanets.domain.usecase.Planet.GetPlanetDetailUseCase
import com.example.tarea4_consumirapiplanets.presentation.navigation.DetailScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPlanetViewModel @Inject constructor(
    private val getPlanetDetailUseCase: GetPlanetDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailPlanetUiState())
    val state = _state.asStateFlow()

    init {
        val args = savedStateHandle.toRoute<DetailScreen>()
        loadPlanet(args.id)
    }

    private fun loadPlanet(id: Int) {
        viewModelScope.launch {
            getPlanetDetailUseCase(id).collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> _state.update {
                        it.copy(
                            isLoading = false,
                            planet = result.data
                        )
                    }
                    is Resource.Error -> _state.update {
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