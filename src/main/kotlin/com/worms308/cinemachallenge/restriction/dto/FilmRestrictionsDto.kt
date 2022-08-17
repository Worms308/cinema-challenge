package com.worms308.cinemachallenge.restriction.dto

import java.time.LocalTime
import java.util.*

data class FilmRestrictionsDto(
    val filmId: UUID, // filmId is like primary key here to make mapping them more simple
    val restrictions: List<RestrictionDto>
) {
    data class RestrictionDto (
        val availableFrom: LocalTime,
        val availableTo: LocalTime,
        val reason: String?
    )

    fun hasAnyRestrictions() = restrictions.isNotEmpty()
}
