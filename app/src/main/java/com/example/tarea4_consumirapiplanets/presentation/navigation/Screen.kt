package com.example.tarea4_consumirapiplanets.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object ListScreen

@Serializable
data class DetailScreen(val id: Int)

@Serializable
object CharacterListScreen
@Serializable
data class CharacterDetailScreen(val id: Int)