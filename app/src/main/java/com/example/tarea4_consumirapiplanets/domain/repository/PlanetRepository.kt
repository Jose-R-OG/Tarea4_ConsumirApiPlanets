package com.example.tarea4_consumirapiplanets.domain.repository

import com.example.tarea4_consumirapiplanets.data.remote.Resource
import com.example.tarea4_consumirapiplanets.domain.model.Planets
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {
    fun getPlanets(
        page: Int,
        limit: Int,
        name: String?,
        isDestroyed: Boolean?
    ): Flow<Resource<List<Planets>>>

    fun getPlanetDetail(id: Int): Flow<Resource<Planets>>
}