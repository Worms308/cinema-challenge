package com.worms308.cinemachallenge.restriction

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class FilmRestrictionConfiguration {

    fun filmRestrictionFacade(): FilmRestrictionFacade {
        val filmRestrictionRepository = InMemoryFilmRestrictionRepository()
        return FilmRestrictionFacade(filmRestrictionRepository)
    }

    @Bean
    fun filmRestrictionRepository(): FilmRestrictionRepository {
        return InMemoryFilmRestrictionRepository()
    }

    @Bean
    fun filmRestrictionFacade(
        filmRestrictionRepository: FilmRestrictionRepository
    ): FilmRestrictionFacade {
        return FilmRestrictionFacade(filmRestrictionRepository)
    }
}