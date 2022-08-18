package com.worms308.cinemachallenge.scheduler.dto

import java.time.LocalDateTime
import java.util.*

data class ScheduledShowDto(
    val reservationId: UUID,
    val cinemaRoomId: UUID,
    val showStart: LocalDateTime,
    val showEnd: LocalDateTime,
    val cleaningBreakEnd: LocalDateTime
)
