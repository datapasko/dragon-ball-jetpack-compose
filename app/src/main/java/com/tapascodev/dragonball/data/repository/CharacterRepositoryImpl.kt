package com.tapascodev.dragonball.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tapascodev.dragonball.data.CharacterPagingSource
import com.tapascodev.dragonball.domain.model.CharacterModel
import com.tapascodev.dragonball.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api : CharacterApi
) : CharactersRepository {

    companion object {
        const val LIMIT = 10
        const val PREFETCH = 3
    }

    override suspend fun getAllCharacter(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(LIMIT, PREFETCH),
            pagingSourceFactory = {
                CharacterPagingSource(api)
            }
        ).flow
    }

    override suspend fun getCharactersByName(name: String): List<CharacterModel> {
        return api.getFilterCharacters(name).map { it.toPresentation() }
    }

    override suspend fun getCharacter(id: Int): CharacterModel {
        return api.getCharacter(id).toPresentation()
    }
}