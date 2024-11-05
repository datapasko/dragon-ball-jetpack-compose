package com.tapascodev.dragonball.presentation.screens.planets

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tapascodev.dragonball.CoroutinesTestRule
import com.tapascodev.dragonball.data.network.Resource
import com.tapascodev.dragonball.domain.model.PlanetModel
import com.tapascodev.dragonball.domain.usecase.planets.GetAllPlanetsUseCase
import com.tapascodev.dragonball.domain.usecase.planets.GetPlanetUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PlanetViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @RelaxedMockK
    private lateinit var getAllPlanetsUseCase: GetAllPlanetsUseCase

    @RelaxedMockK
    private lateinit var getPlanetUseCase: GetPlanetUseCase

    private lateinit var planetViewModel: PlanetViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        planetViewModel = PlanetViewModel(getAllPlanetsUseCase, getPlanetUseCase)
    }

    /*//fun `when viewmodel is created at the first time, get all planets` () = coroutinesTestRule.unconfinedDispatcher.run {
       //Given
        val myList = Resource.Success(listOf(
            PlanetModel(
            1,
            "Tierra",
            false,
            "es una tierra",
            "una imagen",
            emptyList()
        )
        ))
       // coEvery { getAllPlanetsUseCase() } returns myList

        //When
        //planetViewModel.getPlanets()

        //Then
        //assert(planetViewModel.planets.value == myList)
    }*/

}