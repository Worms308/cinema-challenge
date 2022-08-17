package com.worms308.cinemachallenge.restriction

import com.worms308.cinemachallenge.restriction.dto.FilmRestrictionsDto
import java.util.*

internal class FilmRestrictionFacade(
    private val filmRestrictionRepository: FilmRestrictionRepository
) {

    fun getRestrictions(filmId: UUID): FilmRestrictionsDto {
        val restrictions = filmRestrictionRepository.findByFilmId(filmId)
        return restrictions?.toDto() ?: noRestrictionsResponse(filmId)
    }

    private fun noRestrictionsResponse(filmId: UUID) = FilmRestrictionsDto(
        filmId,
        emptyList()
    )
}