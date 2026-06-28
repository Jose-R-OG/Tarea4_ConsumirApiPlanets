package com.example.tarea4_consumirapiplanets.data.remote.remotedatasource

import com.example.tarea4_consumirapiplanets.data.remote.DragonBallApi
import com.example.tarea4_consumirapiplanets.data.remote.dtos.PlanetDto
import com.example.tarea4_consumirapiplanets.data.remote.dtos.PlanetResponseDto
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class PlanetRemoteDataSource @Inject constructor(
    private val api: DragonBallApi
) {
    suspend fun getPlanets(
        page: Int,
        limit: Int,
        name: String?,
        isDestroyed: Boolean?
    ): Result<PlanetResponseDto> {
        try {
            val response = if (name != null) {
                val list = api.searchPlanetsByName(name).body() ?: emptyList()
                Response.success(PlanetResponseDto(list))
            } else {
                api.getPlanets(page, limit, isDestroyed)
            }

            if (!response.isSuccessful) {
                return Result.failure(Exception("Error de red ${response.code()}"))
            }
            return Result.success(response.body()!!)
        } catch (e: HttpException) {
            return Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            return Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun getPlanetDetail(id: Int): Result<PlanetDto> {
        try {
            val response = api.getPlanetDetail(id)
            if (!response.isSuccessful) {
                return Result.failure(Exception("Error de red ${response.code()}"))
            }
            return Result.success(response.body()!!)
        } catch (e: HttpException) {
            return Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            return Result.failure(Exception("Error desconocido", e))
        }
    }
}