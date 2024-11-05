package com.tapascodev.dragonball.di

import android.content.Context
import androidx.room.Room
import com.tapascodev.dragonball.data.local.DragonBallDatabase
import com.tapascodev.dragonball.data.local.util.Constants.DRAGON_BALL_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://dragonball-api.com/api/"

    @Provides
    fun providerRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    fun providerOkHttpClient (): OkHttpClient =
        OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideDragonBallDatabase(
        @ApplicationContext context: Context
    ): DragonBallDatabase {
        return Room.databaseBuilder(
            context,
            DragonBallDatabase::class.java,
            DRAGON_BALL_DATABASE
        )
            .build()
    }
}