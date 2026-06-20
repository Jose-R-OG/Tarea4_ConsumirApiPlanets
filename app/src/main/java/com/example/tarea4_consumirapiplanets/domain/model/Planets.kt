package com.example.tarea4_consumirapiplanets.domain.model

data class Planets(
    val id: Int,
    val name: String,
    val isDestroyed: Boolean,
    val description: String,
    val image: String,
)