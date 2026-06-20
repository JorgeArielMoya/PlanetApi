package edu.ucne.planetapi.presentation.list

import edu.ucne.planetapi.domain.model.Planet

data class PlanetListUiState(
    val isLoading: Boolean = false,
    val planets: List<Planet> = emptyList(),
    val planetsFiltradas: List<Planet> = emptyList(),
    val error: String? = null,
    val filterName: String = ""
)