package edu.ucne.planetapi.data.repository

import edu.ucne.planetapi.data.remote.Resource
import edu.ucne.planetapi.data.remote.remotedatasource.PlanetRemoteDataSource
import edu.ucne.planetapi.domain.planet.model.Planet
import edu.ucne.planetapi.domain.planet.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val remoteDataSource: PlanetRemoteDataSource
) : PlanetRepository {

    override fun getPlanets(
        page: Int,
        limit: Int,
        name: String?
    ): Flow<Resource<List<Planet>>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getPlanets(page, limit, name)
            .onSuccess { dto ->
                emit(Resource.Success(dto.items.map { it.toDomain() }))
            }.onFailure {
                emit(Resource.Error(it.message ?: "Error desconocido"))
            }
    }

    override fun getPlanetDetail(id: Int): Flow<Resource<Planet>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getPlanetDetail(id)
            .onSuccess { dto ->
                emit(Resource.Success(dto.toDomain()))
            }.onFailure {
                emit(Resource.Error(it.message ?: "Error desconocido"))
            }
    }
}