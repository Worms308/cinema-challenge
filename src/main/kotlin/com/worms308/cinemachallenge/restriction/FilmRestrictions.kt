package com.worms308.cinemachallenge.restriction

import com.worms308.cinemachallenge.restriction.dto.FilmRestrictionsDto
import com.worms308.cinemachallenge.restriction.dto.FilmRestrictionsDto.RestrictionDto
import java.time.LocalTime
import java.util.*

internal data class FilmRestrictions(
    val filmId: UUID, // filmId is like primary key here to make mapping them more simple
    val restrictions: List<Restriction>
) {
    internal data class Restriction (
        val availableFrom: LocalTime,
        val availableTo: LocalTime,
        val reason: String?
    ) {
        fun toDto() = RestrictionDto(
            availableFrom, availableTo, reason
        )
    }

    fun toDto() = FilmRestrictionsDto(
        filmId, restrictions.map { it.toDto() }
    )
}
