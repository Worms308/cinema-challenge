package com.worms308.cinemachallenge.showcreator.exception

import java.time.LocalDateTime
import java.util.*

class CinemaRoomNotAvailableException(
    cinemaRoomId: UUID,
    beginAt: LocalDateTime,
    filmLength: Int
) : Exception("Cinema room [$cinemaRoomId] is not available on [$beginAt] for [$filmLength] minutes") {
}