package com.tapascodev.dragonball.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import com.tapascodev.dragonball.data.CharacterPagingSource
import com.tapascodev.dragonball.data.local.DragonBallDatabase
import com.tapascodev.dragonball.data.mapper.toFavoriteCharacterEntity
import com.tapascodev.dragonball.data.network.CharacterApi
import com.tapascodev.dragonball.domain.model.CharacterModel
import com.tapascodev.dragonball.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api : CharacterApi,
    private val database: DragonBallDatabase
) : CharactersRepository {

    private val favoriteCharacterDao = database.favoriteCharacterDao()

    companion object {
        const val LIMIT = 10
        const val PREFETCH = 2
    }

    override suspend fun getAllCharacter(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(pageSize = LIMIT, enablePlaceholders = false, initialLoadSize = PREFETCH),
            pagingSourceFactory = {
                CharacterPagingSource(api)
            },
            initialKey = 1
        )
            .flow
            .map { pagingData ->
                pagingData.filter { character -> true
                    //character.name.contains("o")
                }
            }
    }

    override suspend fun getCharactersByName(name: String): List<CharacterModel> {
        return api.getFilterCharacters(name).map { it.toPresentation() }
    }

    override suspend fun getCharacter(id: Int): CharacterModel {
        return api.getCharacter(id).toPresentation()
    }

    override suspend fun searchCharacters(query: String): List<CharacterModel> {
        return api.getFilterCharacters(query).map { it.toPresentation() }
    }

    override suspend fun toggleFavoriteStatus(characterModel: CharacterModel) {
        val isFavorite = favoriteCharacterDao.isCharacterFavorite(characterModel.id.toString())
        val favoriteCharacter = characterModel.toFavoriteCharacterEntity()

        if(isFavorite){
            favoriteCharacterDao.deleteFavoriteCharacter(favoriteCharacter)
        }else{
            favoriteCharacterDao.insertFavoriteCharacter(favoriteCharacter)
        }

    }
}