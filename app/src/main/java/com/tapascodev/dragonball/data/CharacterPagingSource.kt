package com.tapascodev.dragonball.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tapascodev.dragonball.data.repository.CharacterApi
import com.tapascodev.dragonball.domain.model.CharacterModel
import java.io.IOException
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(
    private val api: CharacterApi
) : PagingSource<Int, CharacterModel>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        return try {

            val page = params.key ?: 1
            val response = api.getCharacters(page = page)
            val characters = response.items.map {
                it.toPresentation()
            }
            val prevKey = if(page > 0) page - 1 else null
            val nextKey = if(response.meta.totalPages > page) page + 1 else null

            LoadResult.Page(
                data = characters,
                prevKey = prevKey,
                nextKey = nextKey
            )


        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }
}