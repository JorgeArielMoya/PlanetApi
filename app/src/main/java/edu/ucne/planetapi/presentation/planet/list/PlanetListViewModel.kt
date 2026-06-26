package edu.ucne.planetapi.presentation.planet.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.planetapi.data.remote.Resource
import edu.ucne.planetapi.domain.planet.model.Planet
import edu.ucne.planetapi.domain.planet.usecase.GetPlanetsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetListViewModel @Inject constructor(
    private val getPlanetsUseCase: GetPlanetsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PlanetListUiState())
    val state = _state.asStateFlow()

    init {
        loadPlanets()
    }

    fun onEvent(event: PlanetListEvent) {
        when (event) {
            is PlanetListEvent.UpdateName -> {
                _state.update { it.copy(filterName = event.name) }
                filtrar()
            }
        }
    }

    private fun filtrar() {
        val nombre = _state.value.filterName.trim().lowercase()
        val filtrados = _state.value.planets.filter { planet ->
            nombre.isEmpty() || planet.name.contains(nombre, true)
        }
        _state.update { it.copy(planetsFiltradas = filtrados) }
    }

    private fun loadPlanets() {
        viewModelScope.launch {
            getPlanetsUseCase().collect { result: Resource<List<Planet>> ->
                when (result) {
                    is Resource.Loading<*> -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success<*> -> {
                        _state.update {
                            it.copy(isLoading = false, planets = result.data ?: emptyList())
                        }
                        filtrar()
                    }
                    is Resource.Error<*> -> _state.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }
}