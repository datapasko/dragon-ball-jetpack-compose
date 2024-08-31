package com.tapascodev.dragonball.domain.usecase

import com.tapascodev.dragonball.domain.model.CharacterModel
import com.tapascodev.dragonball.domain.model.Resource
import com.tapascodev.dragonball.domain.repository.CharactersRepository
import com.tapascodev.dragonball.domain.repository.SafeApiCall
import javax.inject.Inject

class GetCharactersByNameUseCase @Inject constructor(
    private val repository: CharactersRepository
): SafeApiCall {
    suspend operator fun invoke(name: String): Resource<List<CharacterModel>> = safeApiCall {
        repository.getCharactersByName(name)
    }
}