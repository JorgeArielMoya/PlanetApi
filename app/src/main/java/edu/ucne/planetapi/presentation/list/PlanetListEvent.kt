package edu.ucne.planetapi.presentation.list

sealed interface PlanetListEvent {
    data class UpdateName(val name: String) : PlanetListEvent
    data object Search : PlanetListEvent
}