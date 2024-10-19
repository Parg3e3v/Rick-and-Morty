package com.parg3v.rickandmorty.characters.presentation.screen.residents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parg3v.rickandmorty.characters.domain.model.CharacterDomainModel
import com.parg3v.rickandmorty.characters.domain.usecase.GetResidentsUseCase
import com.parg3v.rickandmorty.common_domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ResidentsViewModel(
    private val getResidentsUseCase: GetResidentsUseCase,
) : ViewModel() {

    private val _charactersState =
        MutableStateFlow<Result<Pair<String, List<CharacterDomainModel>>>>(Result.Loading)
    val charactersState = _charactersState.asStateFlow()

    fun getResidents(locationId: String) {
        viewModelScope.launch(Dispatchers.Default) {
            getResidentsUseCase.invoke(locationId).collect { result ->
                _charactersState.value = result
            }
        }
    }
}