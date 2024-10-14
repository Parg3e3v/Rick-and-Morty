package com.parg3v.rickandmorty.characters.presentation.screen.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.domain.usecase.GetAllCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharactersUseCase: GetAllCharactersUseCase,
) : ViewModel() {

    private val _charactersState = MutableStateFlow<List<CharacterDomainModel>>(emptyList())
    val charactersState = _charactersState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            getCharactersUseCase.invoke().collect { charactersList ->
                _charactersState.value = charactersList
            }
        }
    }

}