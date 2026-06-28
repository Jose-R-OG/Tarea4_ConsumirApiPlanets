package com.example.tarea4_consumirapiplanets.presentation.character.list

sealed interface ListCharacterEvent {
    data class UpdateFilters(
        val name: String,
        val gender: String,
        val race: String
    ) : ListCharacterEvent

    data object Search : ListCharacterEvent
}