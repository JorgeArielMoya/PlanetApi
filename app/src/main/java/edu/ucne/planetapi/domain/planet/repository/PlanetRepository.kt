package edu.ucne.planetapi.domain.planet.repository

import edu.ucne.planetapi.data.remote.Resource
import edu.ucne.planetapi.domain.planet.model.Planet
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {
    fun getPlanets(
        page: Int,
        limit: Int,
        name: String?
    ): Flow<Resource<List<Planet>>>

    fun getPlanetDetail(id: Int): Flow<Resource<Planet>>
}