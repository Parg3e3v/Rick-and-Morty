package com.parg3v.rickandmorty.characters.presentation.screen.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.domain.usecase.FetchLocalDataUseCase
import com.parg3v.rickandmorty.characters.domain.usecase.GetAllCharactersUseCase
import com.parg3v.rickandmorty.common_domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharactersUseCase: GetAllCharactersUseCase,
    private val fetchLocalDataUseCase: FetchLocalDataUseCase,
) : ViewModel() {

    private val _charactersState =
        MutableStateFlow<Result<List<CharacterDomainModel>>>(Result.Loading)
    val charactersState = _charactersState.asStateFlow()

    private val _isCharacterFavourite =
        MutableStateFlow<MutableMap<String, Boolean>>(mutableMapOf())
    val isCharacterFavourite = _isCharacterFavourite.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            getCharactersUseCase.invoke().collect { result ->
                _charactersState.value = result
            }
        }
    }

    fun fetchLocalData(id: String) {
        viewModelScope.launch(Dispatchers.Default) {
            fetchLocalDataUseCase.invoke(id).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _isCharacterFavourite.value[id] = result.data?.favourite ?: false
                    }
                else -> _isCharacterFavourite.value[id] = false
            }
        }
    }
}
}