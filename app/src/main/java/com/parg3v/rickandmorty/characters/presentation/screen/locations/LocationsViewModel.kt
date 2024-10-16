package com.parg3v.rickandmorty.characters.presentation.screen.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parg3v.rickandmorty.characters.domain.model.LocationDomainModel
import com.parg3v.rickandmorty.characters.domain.usecase.GetAllLocationsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.parg3v.rickandmorty.common_domain.Result

class LocationsViewModel(
    private val getAllLocationsUseCase: GetAllLocationsUseCase,
) : ViewModel() {

    private val _locationsState = MutableStateFlow<Result<List<LocationDomainModel>>>(Result.Loading)
    val locationsState = _locationsState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            getAllLocationsUseCase.invoke().collect { locationsList ->
                _locationsState.value = locationsList
            }
        }
    }

}