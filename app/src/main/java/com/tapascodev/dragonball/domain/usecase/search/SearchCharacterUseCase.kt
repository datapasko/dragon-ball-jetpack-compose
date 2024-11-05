package com.tapascodev.dragonball.domain.usecase.search

import com.tapascodev.dragonball.domain.model.CharacterModel
import com.tapascodev.dragonball.data.network.Resource
import com.tapascodev.dragonball.domain.repository.CharactersRepository
import com.tapascodev.dragonball.data.network.SafeApiCall
import javax.inject.Inject

class SearchCharacterUseCase @Inject constructor(
    val repository: CharactersRepository
): SafeApiCall {
    suspend operator fun invoke(name: String): Resource<List<CharacterModel>> = safeApiCall {
        repository.getCharactersByName(name)
    }
}