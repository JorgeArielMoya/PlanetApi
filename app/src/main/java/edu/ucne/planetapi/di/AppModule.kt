package edu.ucne.planetapi.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.planetapi.data.remote.DragonBallApi
import edu.ucne.planetapi.data.remote.remotedatasource.CharacterRemoteDataSource
import edu.ucne.planetapi.data.remote.remotedatasource.PlanetRemoteDataSource
import edu.ucne.planetapi.data.repository.CharacterRepositoryImpl
import edu.ucne.planetapi.data.repository.PlanetRepositoryImpl
import edu.ucne.planetapi.domain.character.repository.CharacterRepository
import edu.ucne.planetapi.domain.planet.repository.PlanetRepository
import jakarta.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(moshi: Moshi): DragonBallApi {
        return Retrofit.Builder()
            .baseUrl("https://dragonball-api.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(DragonBallApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: DragonBallApi): PlanetRemoteDataSource {
        return PlanetRemoteDataSource(api)
    }

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: PlanetRemoteDataSource): PlanetRepository {
        return PlanetRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideCharacterRemoteDataSource(api: DragonBallApi): CharacterRemoteDataSource {
        return CharacterRemoteDataSource(api)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(remoteDataSource: CharacterRemoteDataSource): CharacterRepository {
        return CharacterRepositoryImpl(remoteDataSource)
    }
}