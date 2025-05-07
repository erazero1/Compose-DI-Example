package com.erazero1.compose_di_example.di

import com.erazero1.compose_di_example.data.mapper.CharacterMapper
import com.erazero1.compose_di_example.data.mapper.DetailedLocationMapper
import com.erazero1.compose_di_example.data.mapper.EpisodeMapper
import com.erazero1.compose_di_example.data.mapper.LocationMapper
import com.erazero1.compose_di_example.data.mapper.Mapper
import com.erazero1.compose_di_example.data.mapper.OriginMapper
import com.erazero1.compose_di_example.data.model.CharacterDto
import com.erazero1.compose_di_example.data.model.DetailedLocationDto
import com.erazero1.compose_di_example.data.model.EpisodeDto
import com.erazero1.compose_di_example.data.model.LocationDto
import com.erazero1.compose_di_example.data.model.OriginDto
import com.erazero1.compose_di_example.data.remote.RetrofitInstance
import com.erazero1.compose_di_example.data.remote.RickAndMortyApi
import com.erazero1.compose_di_example.data.repository.CharacterDetailsRepositoryImpl
import com.erazero1.compose_di_example.data.repository.CharacterRepositoryImpl
import com.erazero1.compose_di_example.data.repository.LocationDetailsRepositoryImpl
import com.erazero1.compose_di_example.data.repository.LocationsRepositoryImpl
import com.erazero1.compose_di_example.domain.entity.Character
import com.erazero1.compose_di_example.domain.entity.DetailedLocation
import com.erazero1.compose_di_example.domain.entity.Episode
import com.erazero1.compose_di_example.domain.entity.Location
import com.erazero1.compose_di_example.domain.entity.Origin
import com.erazero1.compose_di_example.domain.repository.CharacterDetailsRepository
import com.erazero1.compose_di_example.domain.repository.CharactersRepository
import com.erazero1.compose_di_example.domain.repository.LocationDetailsRepository
import com.erazero1.compose_di_example.domain.repository.LocationsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Named("locationMapper")
    @Singleton
    fun provideLocationMapper(): Mapper<LocationDto, Location> {
        return LocationMapper()
    }

    @Provides
    @Named("detailedLocationMapper")
    @Singleton
    fun provideDetailedLocationMapper(): Mapper<DetailedLocationDto, DetailedLocation> {
        return DetailedLocationMapper()
    }

    @Provides
    @Named("originMapper")
    @Singleton
    fun provideOriginMapper(): Mapper<OriginDto, Origin> {
        return OriginMapper()
    }

    @Provides
    @Named("episodeMapper")
    @Singleton
    fun provideEpisodeMapper(): Mapper<EpisodeDto, Episode> {
        return EpisodeMapper()
    }

    @Provides
    @Named("characterMapper")
    @Singleton
    fun provideCharacterMapper(
        @Named("locationMapper") locationMapper: Mapper<LocationDto, Location>,
        @Named("originMapper") originMapper: Mapper<OriginDto, Origin>,
    ): Mapper<CharacterDto, Character> {
        return CharacterMapper(
            locationMapper = locationMapper,
            originMapper = originMapper,
        )
    }

}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{
    @Provides
    @Singleton
    fun provideRickAndMortyApi(): RickAndMortyApi {
        return RetrofitInstance.api
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLocationRepository(
        api: RickAndMortyApi,
        @Named("detailedLocationMapper") detailedLocationMapper: Mapper<DetailedLocationDto, DetailedLocation>
    ): LocationsRepository {
        return LocationsRepositoryImpl(
            apiService =  api,
            detailedLocationMapper = detailedLocationMapper
        )
    }

    @Provides
    @Singleton
    fun provideLocationDetailsRepository(
        api: RickAndMortyApi,
        @Named("detailedLocationMapper") detailedLocationMapper: Mapper<DetailedLocationDto, DetailedLocation>,
        @Named("characterMapper") characterMapper: Mapper<CharacterDto, Character>,
    ): LocationDetailsRepository {
        return LocationDetailsRepositoryImpl(
            apiService = api,
            detailedLocationMapper = detailedLocationMapper,
            characterMapper = characterMapper,
        )
    }

    @Provides
    @Singleton
    fun provideCharactersRepository(
        api: RickAndMortyApi,
        @Named("characterMapper") characterMapper: Mapper<CharacterDto, Character>,
    ): CharactersRepository {
        return CharacterRepositoryImpl(
            apiService = api,
            characterMapper = characterMapper,
        )
    }

    @Provides
    @Singleton
    fun provideCharacterDetailsRepository(
        api: RickAndMortyApi,
        @Named("characterMapper") characterMapper: Mapper<CharacterDto, Character>,
        @Named("episodeMapper") episodeMapper: Mapper<EpisodeDto, Episode>,
    ): CharacterDetailsRepository {
        return CharacterDetailsRepositoryImpl(
            apiService = api,
            characterMapper = characterMapper,
            episodeMapper = episodeMapper,
        )
    }
}