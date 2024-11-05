package com.tapascodev.dragonball.domain.usecase.characters

import com.tapascodev.dragonball.domain.model.CharacterModel
import com.tapascodev.dragonball.data.network.Resource
import com.tapascodev.dragonball.domain.repository.CharactersRepository
import com.tapascodev.dragonball.data.network.SafeApiCall
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: CharactersRepository
): SafeApiCall {

    suspend operator fun invoke(id: Int): Resource<CharacterModel> = safeApiCall {
        repository.getCharacter(id)
    }
}