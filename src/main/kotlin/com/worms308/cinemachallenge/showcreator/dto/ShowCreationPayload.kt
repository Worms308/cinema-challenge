package com.worms308.cinemachallenge.showcreator.dto

import java.time.LocalDateTime
import java.util.*

data class ShowCreationPayload(
    val filmId: UUID,
    val cinemaRoomId: UUID,
    val beginAt: LocalDateTime
)
