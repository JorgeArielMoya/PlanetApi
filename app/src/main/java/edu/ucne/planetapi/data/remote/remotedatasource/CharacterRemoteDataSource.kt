package edu.ucne.planetapi.data.remote.remotedatasource

import edu.ucne.planetapi.data.remote.DragonBallApi
import edu.ucne.planetapi.data.remote.dto.CharacterDto
import edu.ucne.planetapi.data.remote.dto.CharacterResponseDto
import retrofit2.HttpException
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val api: DragonBallApi
) {
    suspend fun getCharacters(
        page: Int,
        limit: Int,
        name: String?
    ): Result<CharacterResponseDto> {
        return try {
            val response = api.getCharacters(page, limit, name)
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

    suspend fun getCharacterDetail(id: Int): Result<CharacterDto> {
        return try {
            val response = api.getCharacterDetail(id)
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