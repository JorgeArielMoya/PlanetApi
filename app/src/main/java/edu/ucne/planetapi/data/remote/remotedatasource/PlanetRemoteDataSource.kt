package edu.ucne.planetapi.data.remote.remotedatasource

import retrofit2.HttpException
import edu.ucne.planetapi.data.remote.DragonBallApi
import edu.ucne.planetapi.data.remote.dto.PlanetDto
import edu.ucne.planetapi.data.remote.dto.PlanetResponseDto
import javax.inject.Inject

class PlanetRemoteDataSource @Inject constructor(
    private val api: DragonBallApi
) {
    suspend fun getPlanets(
        page: Int,
        limit: Int,
        name: String?
    ): Result<PlanetResponseDto> {
        return try {
            val response = api.getPlanets(page, limit, name)
            if (!response.isSuccessful)
                Result.failure(Exception("Error de red ${response.code()}"))
            else
                Result.success(response.body()!!)
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun getPlanetDetail(id: Int): Result<PlanetDto> {
        return try {
            val response = api.getPlanetDetail(id)
            if (!response.isSuccessful)
                Result.failure(Exception("Error de red ${response.code()}"))
            else
                Result.success(response.body()!!)
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }
}