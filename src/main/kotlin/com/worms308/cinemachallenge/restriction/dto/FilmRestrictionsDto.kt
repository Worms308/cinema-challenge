package com.worms308.cinemachallenge.restriction.dto

import java.time.LocalTime
import java.util.*

// normally I'd use payload class to create and response class to return results from this module but due to lack of time I'm using only single class
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
