package com.parg3v.rickandmorty.characters.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.domain.usecase.GetCharacterDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.parg3v.rickandmorty.common_domain.Result

class DetailsViewModel(
    private val getCharacterDetails: GetCharacterDetailsUseCase,
) : ViewModel() {

    private val _character =
        MutableStateFlow<Result<CharacterDomainModel?>>(Result.Loading)
    val character = _character.asStateFlow()

    fun getCharacterDetails(characterId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getCharacterDetails.invoke(characterId).collect {
                _character.value = it
            }
        }
    }
}