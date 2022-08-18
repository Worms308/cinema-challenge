package cinemachallenge.restriction

import cinemachallenge.IntegrationSpec
import com.fasterxml.jackson.databind.ObjectMapper
import com.worms308.cinemachallenge.restriction.FilmRestrictionFacade
import com.worms308.cinemachallenge.restriction.dto.FilmRestrictionsDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType

import java.time.LocalTime
import java.time.format.DateTimeFormatter

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class FilmRestrictionIntegrationSpec extends IntegrationSpec {

    @Autowired
    FilmRestrictionFacade restrictionFacade

    @Autowired
    ObjectMapper objectMapper

    UUID noRestrictionFilmId = UUID.fromString("67ee2a64-1e59-11ed-861d-0242ac120002")
    UUID restrictedFilmId = UUID.fromString("a7279128-1e5b-11ed-861d-0242ac120002")

    def "should return empty list of restrictions for film without restrictions"() {
        given:
            def filmId = noRestrictionFilmId

        when:
            def response = mockMvc.perform(
                    get("/film-restriction/${filmId}")
            )

        then:
            response
                    .andExpect(status().isOk())
                    .andExpect(content().json(
                            """{"filmId": "$filmId", "restrictions": [] }"""
                    ))
    }

    def "should add restrictions for film and return it in get request"() {
        given:
            def filmId = restrictedFilmId
            def from = LocalTime.of(12, 0)
            def to = LocalTime.of(17, 0)
            def filmRestrictions = new FilmRestrictionsDto(
                    filmId,
                    [createRestrictions(from, to, "Movie premiere")]
            )

        when:
            def createResponse = mockMvc.perform(
                    post("/film-restriction/")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(filmRestrictions))
            )

        then:
            createResponse.andExpect(status().isOk())

        when:
            def getResponse = mockMvc.perform(
                    get("/film-restriction/${filmId}")
            )

        then:
            getResponse
                    .andExpect(status().isOk())
                    .andExpect(content().json(
                            """{"filmId": "$filmId", "restrictions": [{"availableFrom": "${asTime(from)}", "availableTo": "${asTime(to)}", "reason": "Movie premiere"}] }"""
                    ))
    }

    String asTime(LocalTime localTime) {
        return localTime.format(DateTimeFormatter.ISO_LOCAL_TIME)
    }

    FilmRestrictionsDto.RestrictionDto createRestrictions(LocalTime from, LocalTime to, String reason) {
        return new FilmRestrictionsDto.RestrictionDto(
                from, to, reason
        )
    }
}
