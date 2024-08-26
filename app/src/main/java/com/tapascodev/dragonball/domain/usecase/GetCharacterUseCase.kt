package com.tapascodev.dragonball.domain.usecase

import com.tapascodev.dragonball.domain.model.CharacterModel
import com.tapascodev.dragonball.domain.model.Resource
import com.tapascodev.dragonball.domain.repository.CharactersRepository
import com.tapascodev.dragonball.domain.repository.SafeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: CharactersRepository
): SafeApiCall {

    suspend operator fun invoke(id: Int): Resource<CharacterModel> = safeApiCall {
        repository.getCharacter(id)
    }
}