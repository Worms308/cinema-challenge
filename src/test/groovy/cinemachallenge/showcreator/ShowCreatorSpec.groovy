package cinemachallenge.showcreator

import com.worms308.cinemachallenge.filmarchive.FilmArchiveFacade
import com.worms308.cinemachallenge.filmarchive.dto.FilmDto
import com.worms308.cinemachallenge.scheduler.SchedulerFacade
import com.worms308.cinemachallenge.scheduler.dto.RoomAvailabilityDto
import com.worms308.cinemachallenge.scheduler.dto.ScheduledShowDto
import com.worms308.cinemachallenge.showcreator.ShowCreator
import com.worms308.cinemachallenge.showcreator.ShowCreatorConfiguration
import com.worms308.cinemachallenge.showcreator.dto.ShowCreationPayload
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.LocalTime

class ShowCreatorSpec extends Specification {

    FilmArchiveFacade archiveFacade = Mock()
    SchedulerFacade schedulerFacade = Mock()

    ShowCreator showCreator = new ShowCreatorConfiguration().showCreator(
            archiveFacade,
            schedulerFacade
    )

    UUID restrictedFilmId = UUID.fromString("a7279128-1e5b-11ed-861d-0242ac120002")
    UUID cinemaRoomId = UUID.fromString("822f2dfa-1f27-11ed-861d-0242ac120002")
    UUID reservationId = UUID.fromString("57d30fc0-1f29-11ed-861d-0242ac120002")
    def restrictedFilmLength = 90
    def threeDimensions = false
    def restrictedFrom = LocalTime.of(17, 0)
    def restrictedTo = LocalTime.of(22, 0)
    def restrictionReason = "Film premiere"
    def cleaningBreakLength = 10

    def "should create film show in restricted to premiere hours"() {
        given:
            def showStart = LocalDateTime.of(2022, 8, 18, 20, 0, 0)
            def roomAvailability = createDefaultRoomAvailability(showStart)
            def showCreationPayload = new ShowCreationPayload(
                    restrictedFilmId,
                    cinemaRoomId,
                    showStart
            )

        and:
            archiveFacade.getFilmDetails(restrictedFilmId) >> createRestrictedFilm() //there should be listed restriction hours but no time for that
            schedulerFacade.isRoomAvailable(roomAvailability) >> true
            schedulerFacade.scheduleShow(roomAvailability) >> createDefaultScheduledShow(showStart)

        when:
            def response = showCreator.createShow(showCreationPayload)

        then:
            response != null
            response.cinemaRoomId == cinemaRoomId
            response.reservationId == reservationId
            response.showStart == showStart
            response.showEnd == showStart.plusMinutes(restrictedFilmLength)
            response.cleaningBreakEnd == showStart.plusMinutes(restrictedFilmLength + cleaningBreakLength) // normally I'd write it in more clean way
    }

    private ScheduledShowDto createDefaultScheduledShow(LocalDateTime showStart) {
        def endTime = showStart.plusMinutes(restrictedFilmLength)
        return new ScheduledShowDto(
                reservationId,
                cinemaRoomId,
                showStart,
                endTime,
                endTime.plusMinutes(cleaningBreakLength)
        )
    }

    private RoomAvailabilityDto createDefaultRoomAvailability(LocalDateTime showStart) {
        return new RoomAvailabilityDto(
                cinemaRoomId,
                createFilmDto(),
                showStart,
                null
        )
    }

    private RoomAvailabilityDto.FilmDto createFilmDto() {
        return new RoomAvailabilityDto.FilmDto(
                restrictedFilmId,
                restrictedFilmLength
        )
    }

    private FilmDto createRestrictedFilm() {
        new FilmDto(
                restrictedFilmId,
                restrictedFilmLength,
                threeDimensions,
                [createFilmRestriction()]
        )
    }

    private FilmDto.FilmRestriction createFilmRestriction() {
        new FilmDto.FilmRestriction(
                restrictedFrom,
                restrictedTo,
                restrictionReason
        )
    }
}
