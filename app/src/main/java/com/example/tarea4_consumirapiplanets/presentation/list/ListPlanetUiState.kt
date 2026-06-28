package com.example.tarea4_consumirapiplanets.presentation.list

import com.example.tarea4_consumirapiplanets.domain.model.Planets

data class ListPlanetUiState (
    val isLoading: Boolean = false,
    val planets: List<Planets> = emptyList(),
    val error: String? = null,
    val nameFilter: String = "",
    val isDestroyedFilter: Boolean? = null
)