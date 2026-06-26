package edu.ucne.planetapi.presentation.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.planetapi.data.remote.Resource
import edu.ucne.planetapi.domain.character.model.Character
import edu.ucne.planetapi.domain.character.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterListUiState())
    val state = _state.asStateFlow()

    init {
        loadCharacters()
    }

    fun onEvent(event: CharacterListEvent) {
        when (event) {
            is CharacterListEvent.UpdateName -> {
                _state.update { it.copy(filterName = event.name) }
                filtrar()
            }
            is CharacterListEvent.UpdateRace -> {
                _state.update { it.copy(filterRace = event.race) }
                filtrar()
            }
            is CharacterListEvent.UpdateGender -> {
                _state.update { it.copy(filterGender = event.gender) }
                filtrar()
            }
        }
    }

    private fun filtrar() {
        val nombre = _state.value.filterName.trim().lowercase()
        val raza = _state.value.filterRace.trim().lowercase()
        val genero = _state.value.filterGender.trim().lowercase()
        val filtrados = _state.value.characters.filter { personaje ->
            (nombre.isEmpty() || personaje.name.contains(nombre, true)) &&
                    (raza.isEmpty() || personaje.race.contains(raza, true)) &&
                    (genero.isEmpty() || personaje.gender.contains(genero, true))
        }
        _state.update { it.copy(charactersFiltrados = filtrados) }
    }


    private fun loadCharacters() {
        viewModelScope.launch {
            getCharactersUseCase().collect { result: Resource<List<Character>> ->
                when (result) {
                    is Resource.Loading<*> -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success<*> -> {
                        _state.update {
                            it.copy(isLoading = false, characters = result.data ?: emptyList())
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