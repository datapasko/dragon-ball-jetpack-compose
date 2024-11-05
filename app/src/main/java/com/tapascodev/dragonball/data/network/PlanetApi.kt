package com.tapascodev.dragonball.data.network

import com.tapascodev.dragonball.data.response.PlanetResponse
import com.tapascodev.dragonball.data.response.ResponsePlanetWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetApi {

    @GET("planets")
    suspend fun getPlanets(): ResponsePlanetWrapper

    @GET("planets/{id}/")
    suspend fun getPlanet(
        @Path("id") id: Int
    ) : PlanetResponse
}