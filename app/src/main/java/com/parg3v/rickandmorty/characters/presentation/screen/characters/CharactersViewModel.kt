package com.parg3v.rickandmorty.characters.presentation.screen.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.domain.usecase.FetchLocalDataUseCase
import com.parg3v.rickandmorty.characters.domain.usecase.GetAllCharactersUseCase
import com.parg3v.rickandmorty.characters.domain.usecase.UpdateCharacterInLocalDBUseCase
import com.parg3v.rickandmorty.common_domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharactersUseCase: GetAllCharactersUseCase,
    private val fetchLocalDataUseCase: FetchLocalDataUseCase,
    private val updateCharacterInLocalDBUseCase: UpdateCharacterInLocalDBUseCase,
) : ViewModel() {

    private val _charactersState =
        MutableStateFlow<Result<List<CharacterDomainModel>>>(Result.Loading)
    val charactersState = _charactersState.asStateFlow()

    private val _isCharacterFavourite = MutableStateFlow<Map<String, CharacterDomainModel>>(mapOf())
    val isCharacterFavourite = _isCharacterFavourite.asStateFlow()

    private val _localDBStoreMessage = MutableStateFlow("")
    val localDBStoreMessage = _localDBStoreMessage.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            getCharactersUseCase.invoke().collect { result ->
                _charactersState.value = result
            }
        }
    }

    fun fetchLocalData() {
        viewModelScope.launch(Dispatchers.Default) {
            fetchLocalDataUseCase.invoke().collect { result ->
                when (result) {
                    is Result.Success -> {
                        _isCharacterFavourite.value = result.data
                    }

                    else -> _isCharacterFavourite.value = mapOf()
                }
            }
        }
    }

    fun updateCharacterInLocalDB(id: String, character: CharacterDomainModel) {
        viewModelScope.launch(Dispatchers.Default) {
            updateCharacterInLocalDBUseCase.invoke(id, character).collect { result ->
                when (result) {
                    is Result.Failure -> {
                        _localDBStoreMessage.value = result.message ?: "Unexpected Error"
                        delay(1000)
                        _localDBStoreMessage.value = ""
                    }

                    else -> {
                        _localDBStoreMessage.value = ""
                    }
                }

            }
        }
    }
}