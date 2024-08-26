package com.tapascodev.dragonball.domain.repository

import androidx.paging.PagingData
import com.tapascodev.dragonball.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharactersRepository: SafeApiCall {
    suspend fun getAllCharacter() : Flow<PagingData<CharacterModel>>
    suspend fun getCharacter(id: Int) : CharacterModel
}