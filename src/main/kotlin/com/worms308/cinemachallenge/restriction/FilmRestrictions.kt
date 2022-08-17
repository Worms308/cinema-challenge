package com.worms308.cinemachallenge.restriction

import com.worms308.cinemachallenge.restriction.dto.FilmRestrictionsDto
import com.worms308.cinemachallenge.restriction.dto.FilmRestrictionsDto.RestrictionDto
import java.time.LocalTime
import java.util.*

internal data class FilmRestrictions internal constructor(
    val filmId: UUID, // filmId is like primary key here to make mapping them more simple
    val restrictions: List<Restriction>
) {
    internal data class Restriction internal constructor(
        val availableFrom: LocalTime,
        val availableTo: LocalTime,
        val reason: String?
    ) {
        fun toDto() = RestrictionDto(
            availableFrom, availableTo, reason
        )

        constructor(restrictionDto: RestrictionDto) : this(
            restrictionDto.availableFrom,
            restrictionDto.availableTo,
            restrictionDto.reason
        )
    }

    fun toDto() = FilmRestrictionsDto(
        filmId, restrictions.map { it.toDto() }
    )

    constructor(filmRestrictionsDto: FilmRestrictionsDto) : this(
        filmRestrictionsDto.filmId, filmRestrictionsDto.restrictions.map { Restriction(it) }
    )
}
