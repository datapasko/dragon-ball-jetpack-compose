package com.tapascodev.dragonball.di

import com.tapascodev.dragonball.data.local.DragonBallDatabase
import com.tapascodev.dragonball.data.network.CharacterApi
import com.tapascodev.dragonball.data.repository.CharacterRepositoryImpl
import com.tapascodev.dragonball.data.network.PlanetApi
import com.tapascodev.dragonball.data.repository.PlanetRepositoryImpl
import com.tapascodev.dragonball.domain.repository.CharactersRepository
import com.tapascodev.dragonball.domain.repository.PlanetsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providerCharacterApiService(retrofit: Retrofit): CharacterApi =
        retrofit.create(CharacterApi::class.java)

    @Singleton
    @Provides
    fun provideCharacterRepository(
        characterApi: CharacterApi,
        database: DragonBallDatabase
    ): CharactersRepository {
        return CharacterRepositoryImpl(characterApi, database)
    }

    @Provides
    fun providerPlanetApiService(retrofit: Retrofit): PlanetApi =
        retrofit.create(PlanetApi::class.java)

    @Singleton
    @Provides
    fun providePlanetRepository(
        planetApi: PlanetApi
    ): PlanetsRepository {
        return PlanetRepositoryImpl(planetApi)
    }
}