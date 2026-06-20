package com.example.tarea4_consumirapiplanets.presentation.list

interface ListPlanetUiEvent {
    data class UpdateFilters(
        val name: String,
        val isDestroyed: Boolean?
    ): ListPlanetUiEvent

    data object Search: ListPlanetUiEvent
}