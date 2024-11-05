package com.tapascodev.dragonball.domain.usecase.planets

import com.tapascodev.dragonball.domain.model.PlanetModel
import com.tapascodev.dragonball.data.network.Resource
import com.tapascodev.dragonball.domain.repository.PlanetsRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class GetAllPlanetsUseCaseTest{

    @RelaxedMockK
    private lateinit var planetRepository: PlanetsRepository

    lateinit var getAllPlanetsUseCase: GetAllPlanetsUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getAllPlanetsUseCase = GetAllPlanetsUseCase(planetRepository)
    }

    @Test
    fun `when the api return something then get values from api`() = runTest {

        //Given
        val myList = Resource.Success(listOf(PlanetModel(
            1,
            "Tierra",
            false,
            "es una tierra",
            "una imagen",
            emptyList()
        )))
        coEvery { planetRepository.getPlanets() } returns myList

        //when
        val response = getAllPlanetsUseCase()

        //Then
        coVerify (exactly = 1) { planetRepository.getPlanets() }
        assert( myList == response)
    }
}