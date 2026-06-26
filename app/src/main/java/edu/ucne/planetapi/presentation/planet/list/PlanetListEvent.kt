package edu.ucne.planetapi.presentation.planet.list

sealed interface PlanetListEvent {
    data class UpdateName(val name: String) : PlanetListEvent
}