package com.worms308.cinemachallenge.restriction

import com.worms308.cinemachallenge.restriction.dto.FilmRestrictionsDto
import java.util.*

//we can make this facade 'public' or available by API - public will be okay in this case
class FilmRestrictionFacade internal constructor(
    private val filmRestrictionRepository: FilmRestrictionRepository
) {

    fun getRestrictions(filmId: UUID): FilmRestrictionsDto {
        val restrictions = filmRestrictionRepository.findByFilmId(filmId)
        return restrictions?.toDto() ?: noRestrictionsResponse(filmId)
    }

    fun createRestriction(filmRestrictionDto: FilmRestrictionsDto) {
        //here can be added some kind of validation but there is no time to add it now
        //also there could be thrown some kind of validation exception to make it catchable in ControllerAdvice class
        filmRestrictionRepository.save(FilmRestrictions(filmRestrictionDto))
    }

    private fun noRestrictionsResponse(filmId: UUID) = FilmRestrictionsDto(
        filmId,
        emptyList()
    )
}