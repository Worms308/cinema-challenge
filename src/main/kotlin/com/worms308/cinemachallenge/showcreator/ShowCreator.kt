package com.worms308.cinemachallenge.showcreator

import com.worms308.cinemachallenge.filmarchive.FilmArchiveFacade
import com.worms308.cinemachallenge.filmarchive.dto.FilmDto
import com.worms308.cinemachallenge.scheduler.SchedulerFacade
import com.worms308.cinemachallenge.scheduler.dto.RoomAvailabilityDto
import com.worms308.cinemachallenge.scheduler.dto.ScheduledShowDto
import com.worms308.cinemachallenge.showcreator.dto.ShowCreationPayload
import com.worms308.cinemachallenge.showcreator.exception.CinemaRoomNotAvailableException

class ShowCreator internal constructor(
    private val filmArchive: FilmArchiveFacade,
    private val scheduler: SchedulerFacade
) {

    fun createShow(showCreationPayload: ShowCreationPayload): ScheduledShowDto? {
        val filmDetails = filmArchive.getFilmDetails(showCreationPayload.filmId)
        val roomAvailabilityDto = createRoomAvailabilityPayload(showCreationPayload, filmDetails)
        if (scheduler.isRoomAvailable(roomAvailabilityDto)) {
            return scheduler.scheduleShow(roomAvailabilityDto) // normally I'd map this response from domain of scheduler to some kind of DTO from showCreator domain
        } else {
            throw CinemaRoomNotAvailableException(
                showCreationPayload.cinemaRoomId,
                showCreationPayload.beginAt,
                filmDetails.length
            )
        }
    }

    private fun createRoomAvailabilityPayload(
        showCreationPayload: ShowCreationPayload,
        filmDetails: FilmDto
    ): RoomAvailabilityDto = RoomAvailabilityDto(
        cinemaRoomId = showCreationPayload.cinemaRoomId,
        film = RoomAvailabilityDto.FilmDto(
            filmDetails.id,
            filmDetails.length
        ),
        showStartAt = showCreationPayload.beginAt
    )
}