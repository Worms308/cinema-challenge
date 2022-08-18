package com.worms308.cinemachallenge.scheduler

import com.worms308.cinemachallenge.scheduler.dto.RoomAvailabilityDto
import com.worms308.cinemachallenge.scheduler.dto.ScheduledShowDto

interface SchedulerFacade {
    // there should be injected CinemaRoom module to handle cleaning breaks after movies
    fun isRoomAvailable(roomAvailabilityDto: RoomAvailabilityDto): Boolean

    fun scheduleShow(roomAvailabilityDto: RoomAvailabilityDto): ScheduledShowDto?
}

// important: method `scheduleShow` could in the future return also UnableToScheduleShowException or something similar in case of concurrent scheduling
// 2 films in the same time, or film and maintenance break