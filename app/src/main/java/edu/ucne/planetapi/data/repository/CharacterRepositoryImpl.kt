package edu.ucne.planetapi.data.repository

import edu.ucne.planetapi.data.remote.Resource
import edu.ucne.planetapi.data.remote.remotedatasource.CharacterRemoteDataSource
import edu.ucne.planetapi.domain.character.model.Character
import edu.ucne.planetapi.domain.character.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource
) : CharacterRepository {

    override fun getCharacters(
        page: Int,
        limit: Int,
        name: String?
    ): Flow<Resource<List<Character>>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getCharacters(page, limit, name)
            .onSuccess { dto ->
                emit(Resource.Success(dto.items.map { it.toDomain() }))
            }.onFailure {
                emit(Resource.Error(it.message ?: "Error desconocido"))
            }
    }

    override fun getCharacterDetail(id: Int): Flow<Resource<Character>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getCharacterDetail(id)
            .onSuccess { dto ->
                emit(Resource.Success(dto.toDomain()))
            }.onFailure {
                emit(Resource.Error(it.message ?: "Error desconocido"))
            }
    }
}