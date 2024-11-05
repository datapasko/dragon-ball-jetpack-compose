package com.tapascodev.dragonball.domain.usecase.characters

import com.tapascodev.dragonball.domain.model.CharacterModel
import com.tapascodev.dragonball.domain.repository.CharactersRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetAllCharactersUseCaseTest{

    @RelaxedMockK
    private lateinit var charactersRepository: CharactersRepository

    lateinit var getAllCharactersUseCase: GetAllCharactersUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getAllCharactersUseCase = GetAllCharactersUseCase(charactersRepository)
    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        //Given
        val myList = listOf(CharacterModel(
            1,
            "goku",
            "200",
            "300",
            "s",
            "h",
            "goku",
            "https://dragonball-api.com/characters/goku_normal.webp",
            "f",
            emptyList()
        ))

        //val data = Resource<Flow>
        //coEvery { charactersRepository.getAllCharacter() } returns myList
    }
}