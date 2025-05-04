package com.erazero1.compose_di_example.di

import android.app.Application
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
import com.erazero1.compose_di_example.domain.usecase.GetCharacterDetailsUseCase
import com.erazero1.compose_di_example.domain.usecase.GetCharactersUseCase
import com.erazero1.compose_di_example.domain.usecase.GetEpisodeByUrlUseCase
import com.erazero1.compose_di_example.domain.usecase.GetLocationDetailsUseCase
import com.erazero1.compose_di_example.domain.usecase.GetLocationsUseCase
import com.erazero1.compose_di_example.domain.usecase.GetResidentByUrlUseCase
import com.erazero1.compose_di_example.ui.character.CharacterViewModel
import com.erazero1.compose_di_example.ui.character_details.CharacterDetailsViewModel
import com.erazero1.compose_di_example.ui.location.LocationViewModel
import com.erazero1.compose_di_example.ui.location_details.LocationDetailsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(uiModule, domainModule, dataModule)
        }
    }
}

val dataModule = module {
    single<RickAndMortyApi> { RetrofitInstance.api }

    single<Mapper<LocationDto, Location>>(named("locationMapper")) { LocationMapper() }
    single<Mapper<DetailedLocationDto, DetailedLocation>>(named("detailedLocationMapper")) { DetailedLocationMapper() }
    single<Mapper<OriginDto, Origin>>(named("originMapper")) { OriginMapper() }
    single<Mapper<EpisodeDto, Episode>>(named("episodeMapper")) { EpisodeMapper() }

    single<Mapper<CharacterDto, Character>>(named("characterMapper")) {
        CharacterMapper(
            locationMapper = get(named("locationMapper")),
            originMapper = get(named("originMapper"))
        )
    }

    single<LocationsRepository> {
        LocationsRepositoryImpl(
            apiService = get(),
            detailedLocationMapper = get(named("detailedLocationMapper"))
        )
    }

    single<LocationDetailsRepository> {
        LocationDetailsRepositoryImpl(
            apiService = get(),
            detailedLocationMapper = get(named("detailedLocationMapper")),
            characterMapper = get(named("characterMapper"))
        )
    }

    single<CharacterDetailsRepository> {
        CharacterDetailsRepositoryImpl(
            apiService = get(),
            characterMapper = get(named("characterMapper")),
            episodeMapper = get(named("episodeMapper"))
        )
    }

    single<CharactersRepository> {
        CharacterRepositoryImpl(
            apiService = get(),
            mapper = get(named("characterMapper"))
        )
    }


}


val domainModule = module {
    single {
        GetCharacterDetailsUseCase(repository = get())
    }

    single {
        GetCharactersUseCase(repository = get())
    }

    single {
        GetLocationDetailsUseCase(repository = get())
    }

    single {
        GetLocationsUseCase(repository = get())
    }

    single {
        GetEpisodeByUrlUseCase(repository = get())
    }

    single {
        GetResidentByUrlUseCase(repository = get())
    }
}

val uiModule = module {

    viewModel { (id: Int) ->
        CharacterDetailsViewModel(
            getCharacterDetailsUseCase = get(),
            getEpisodeByUrlUseCase = get(),
            id = id
        )
    }

    viewModel {
        CharacterViewModel(getCharactersUseCase = get())
    }

    viewModel {
        LocationViewModel(getLocationsUseCase = get())
    }

    viewModel { (id: Int) ->
        LocationDetailsViewModel(
            getLocationDetailsUseCase = get(),
            getResidentByUrlUseCase = get(),
            id = id
        )
    }

}


