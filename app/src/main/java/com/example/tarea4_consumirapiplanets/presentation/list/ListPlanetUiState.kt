package com.example.tarea4_consumirapiplanets.presentation.list

import com.example.tarea4_consumirapiplanets.data.remote.dtos.PlanetDto


data class ListPlanetUiState (
    val isLoading: Boolean = false,
    val planets: List<PlanetDto> = emptyList(),
    val error: String? = null,
    val nameFilter: String = "",
    val isDestroyedFilter: Boolean? = null
)