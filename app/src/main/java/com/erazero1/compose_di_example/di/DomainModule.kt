package com.erazero1.compose_di_example.di

import com.erazero1.compose_di_example.domain.repository.CharacterDetailsRepository
import com.erazero1.compose_di_example.domain.repository.CharactersRepository
import com.erazero1.compose_di_example.domain.repository.LocationDetailsRepository
import com.erazero1.compose_di_example.domain.repository.LocationsRepository
import com.erazero1.compose_di_example.domain.usecase.GetCharacterDetailsUseCase
import com.erazero1.compose_di_example.domain.usecase.GetCharactersUseCase
import com.erazero1.compose_di_example.domain.usecase.GetEpisodeByUrlUseCase
import com.erazero1.compose_di_example.domain.usecase.GetLocationDetailsUseCase
import com.erazero1.compose_di_example.domain.usecase.GetLocationsUseCase
import com.erazero1.compose_di_example.domain.usecase.GetResidentByUrlUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    @ViewModelScoped
    fun provideGetCharacterDetailsUseCase(
        characterDetailsRepository: CharacterDetailsRepository
    ): GetCharacterDetailsUseCase {
        return GetCharacterDetailsUseCase(repository = characterDetailsRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetCharactersUseCase(
        charactersRepository: CharactersRepository
    ): GetCharactersUseCase {
        return GetCharactersUseCase(repository = charactersRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetLocationDetailsUseCase(
        locationDetailsRepository: LocationDetailsRepository
    ): GetLocationDetailsUseCase {
        return GetLocationDetailsUseCase(repository = locationDetailsRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetLocationsUseCase(
        locationsRepository: LocationsRepository
    ): GetLocationsUseCase {
        return GetLocationsUseCase(repository = locationsRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetEpisodeByUrlUseCase(
        characterDetailsRepository: CharacterDetailsRepository
    ): GetEpisodeByUrlUseCase {
        return GetEpisodeByUrlUseCase(repository = characterDetailsRepository)
    }
    @Provides
    @ViewModelScoped
    fun provideGetResidentByUrlUseCase(
        locationDetailsRepository: LocationDetailsRepository
    ): GetResidentByUrlUseCase {
        return GetResidentByUrlUseCase(repository = locationDetailsRepository)
    }
}