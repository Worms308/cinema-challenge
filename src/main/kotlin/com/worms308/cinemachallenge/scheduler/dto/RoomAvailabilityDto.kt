package com.worms308.cinemachallenge.scheduler.dto

import java.time.LocalDateTime
import java.util.*

data class RoomAvailabilityDto(
    val cinemaRoomId: UUID,
    val film: FilmDto,
    val showStartAt: LocalDateTime,
    val customCleaningBreakValue: Int?
) {
    data class FilmDto(
        val filmId: UUID,
        val filmLength: Int,
    )
}
