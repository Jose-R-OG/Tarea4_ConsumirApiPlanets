package com.example.tarea4_consumirapiplanets.data.repository

import com.example.tarea4_consumirapiplanets.data.remote.Resource
import com.example.tarea4_consumirapiplanets.data.remote.remotedatasource.PlanetRemoteDataSource
import com.example.tarea4_consumirapiplanets.domain.model.Planets
import com.example.tarea4_consumirapiplanets.domain.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val remoteDataSource: PlanetRemoteDataSource
) : PlanetRepository {

    override fun getPlanets(
        page: Int,
        limit: Int,
        name: String?,
        isDestroyed: Boolean?
    ): Flow<Resource<List<Planets>>> = flow {
        emit(Resource.Loading())

        val response = remoteDataSource.getPlanets(page, limit, name, isDestroyed)
        response.onSuccess { planets ->
            emit(Resource.Success(planets.items.map { it.toDomain() }))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }

    override fun getPlanetDetail(id: Int): Flow<Resource<Planets>> = flow {
        emit(Resource.Loading())

        val response = remoteDataSource.getPlanetDetail(id)
        response.onSuccess { planet ->
            emit(Resource.Success(planet.toDomain()))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }
}