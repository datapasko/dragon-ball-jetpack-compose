package com.tapascodev.dragonball.data.network

import com.tapascodev.dragonball.data.response.CharacterResponse
import com.tapascodev.dragonball.data.response.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("characters")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 10
    ) : ResponseWrapper

    @GET("characters/{id}/")
    suspend fun getCharacter(
        @Path("id") id: Int
    ) : CharacterResponse

    @GET("characters")
    suspend fun getFilterCharacters(
        @Query("name") name: String,
    ) : List<CharacterResponse>
}