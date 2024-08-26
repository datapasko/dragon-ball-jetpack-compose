package com.tapascodev.dragonball.domain.usecase

import androidx.paging.PagingData
import com.tapascodev.dragonball.domain.model.Resource
import com.tapascodev.dragonball.domain.repository.CharactersRepository
import com.tapascodev.dragonball.domain.model.CharacterModel
import com.tapascodev.dragonball.domain.repository.SafeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
): SafeApiCall {
    suspend operator fun invoke(): Resource<Flow<PagingData<CharacterModel>>> = safeApiCall {
        repository.getAllCharacter()
    }
}