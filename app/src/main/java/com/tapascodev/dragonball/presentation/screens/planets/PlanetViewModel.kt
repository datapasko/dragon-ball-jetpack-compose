package com.tapascodev.dragonball.presentation.screens.planets

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapascodev.dragonball.domain.model.PlanetModel
import com.tapascodev.dragonball.data.network.Resource
import com.tapascodev.dragonball.domain.usecase.planets.GetAllPlanetsUseCase
import com.tapascodev.dragonball.domain.usecase.planets.GetPlanetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetViewModel @Inject constructor(
    private val getAllPlanetsUseCase: GetAllPlanetsUseCase,
    private val getPlanetUseCase: GetPlanetUseCase
): ViewModel() {

    private val _planets = MutableStateFlow<Resource<List<PlanetModel>>>(Resource.Loading)
    val planets: StateFlow<Resource<List<PlanetModel>>> = _planets.asStateFlow()

    private val _planet = MutableStateFlow<Resource<PlanetModel>>(Resource.Loading)
    val planet: StateFlow<Resource<PlanetModel>>
        get() = _planet

    fun getPlanets() = viewModelScope.launch {
        _planets.value = Resource.Loading
        _planets.value = getAllPlanetsUseCase.invoke()
    }

    fun getPlanet(id: Int) =viewModelScope.launch {
        _planets.value = Resource.Loading
        _planet.value = getPlanetUseCase.invoke(id)
    }
}