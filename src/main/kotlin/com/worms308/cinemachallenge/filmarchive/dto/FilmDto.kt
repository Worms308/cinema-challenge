package com.worms308.cinemachallenge.filmarchive.dto

import java.time.LocalTime
import java.util.*

data class FilmDto(
    val id: UUID,
    val length: Int,
    val threeDimensions: Boolean,
    val restrictions: List<FilmRestriction>
) {
    class FilmRestriction(
        val availableFrom: LocalTime,
        val availableTo: LocalTime,
        val reason: String?
    )
}
