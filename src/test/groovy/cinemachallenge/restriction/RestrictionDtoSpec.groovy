package cinemachallenge.restriction

import com.worms308.cinemachallenge.restriction.FilmRestrictionConfiguration
import com.worms308.cinemachallenge.restriction.FilmRestrictionFacade
import com.worms308.cinemachallenge.restriction.dto.FilmRestrictionsDto
import spock.lang.Specification

class RestrictionDtoSpec extends Specification {

    FilmRestrictionFacade filmRestrictionFacade = new FilmRestrictionConfiguration()
            .filmRestrictionFacade()

    UUID noRestrictionFilmId = UUID.fromString("67ee2a64-1e59-11ed-861d-0242ac120002")

    def "should return empty restriction for film without restrictions"() {
        when:
            FilmRestrictionsDto restrictions = filmRestrictionFacade.getRestrictions(noRestrictionFilmId)

        then:
            restrictions != null
            !restrictions.hasAnyRestrictions()
    }
}
