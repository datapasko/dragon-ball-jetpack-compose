package com.tapascodev.dragonball.domain.repository

import androidx.paging.PagingData
import com.tapascodev.dragonball.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getAllCharacter() : Flow<PagingData<CharacterModel>>
    suspend fun getCharactersByName(name:String) : List<CharacterModel>
    suspend fun getCharacter(id: Int) : CharacterModel
    suspend fun searchCharacters(query:String): List<CharacterModel>
    suspend fun toggleFavoriteStatus(characterModel: CharacterModel)
}