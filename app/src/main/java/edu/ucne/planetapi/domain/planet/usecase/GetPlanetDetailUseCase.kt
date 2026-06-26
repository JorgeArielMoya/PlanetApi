package edu.ucne.planetapi.domain.planet.usecase

import edu.ucne.planetapi.domain.planet.repository.PlanetRepository
import jakarta.inject.Inject

class GetPlanetDetailUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    operator fun invoke(id: Int) = repository.getPlanetDetail(id)
}