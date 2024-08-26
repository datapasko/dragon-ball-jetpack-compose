package com.tapascodev.dragonball.di

import com.tapascodev.dragonball.data.repository.CharacterApi
import com.tapascodev.dragonball.data.repository.CharacterRepositoryImpl
import com.tapascodev.dragonball.domain.repository.CharactersRepository
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
    fun provideCharacterRepository(characterApi: CharacterApi): CharactersRepository {
        return CharacterRepositoryImpl(characterApi)
    }
}