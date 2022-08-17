package cinemachallenge.restriction

import com.worms308.cinemachallenge.restriction.FilmRestrictionConfiguration
import com.worms308.cinemachallenge.restriction.FilmRestrictionFacade
import com.worms308.cinemachallenge.restriction.dto.FilmRestrictionsDto
import spock.lang.Specification

import java.time.LocalTime

class RestrictionDtoSpec extends Specification {

    FilmRestrictionFacade filmRestrictionFacade = new FilmRestrictionConfiguration()
            .filmRestrictionFacade()

    UUID noRestrictionFilmId = UUID.fromString("67ee2a64-1e59-11ed-861d-0242ac120002")
    UUID restrictedFilmId = UUID.fromString("a7279128-1e5b-11ed-861d-0242ac120002")


    def "should return empty restriction for film without restrictions"() {
        when:
            FilmRestrictionsDto restrictions = filmRestrictionFacade.getRestrictions(noRestrictionFilmId)

        then:
            restrictions != null
            !restrictions.hasAnyRestrictions()
    }

    def "should return restrictions for film due to being premiere"() {
        given:
            def from = LocalTime.of(12, 0)
            def to = LocalTime.of(17, 0)
            def reason = "Movie premiere"
            def filmRestrictions = new FilmRestrictionsDto(
                    restrictedFilmId,
                    [createRestrictions(from, to, reason)]
            )

        and:
            filmRestrictionFacade.createRestriction(filmRestrictions)

        when:
            FilmRestrictionsDto restrictions = filmRestrictionFacade.getRestrictions(restrictedFilmId)

        then:
            restrictions != null
            restrictions.hasAnyRestrictions()
            restrictions.restrictions.size() == 1
            with (restrictions.restrictions.first()) {
                availableFrom == from
                availableTo == to
                reason == reason
            }
    }

    FilmRestrictionsDto.RestrictionDto createRestrictions(LocalTime from, LocalTime to, String reason) {
        return new FilmRestrictionsDto.RestrictionDto(
                from, to, reason
        )
    }
}
