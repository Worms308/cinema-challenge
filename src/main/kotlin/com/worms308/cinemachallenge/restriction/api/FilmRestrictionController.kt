package com.worms308.cinemachallenge.restriction.api

import com.worms308.cinemachallenge.restriction.FilmRestrictionFacade
import com.worms308.cinemachallenge.restriction.dto.FilmRestrictionsDto
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/film-restriction")
internal class FilmRestrictionController(
    private val filmRestrictionFacade: FilmRestrictionFacade
) {

    @PostMapping("/")
    fun createFilmRestrictions(@RequestBody filmRestrictionsDto: FilmRestrictionsDto) {
        filmRestrictionFacade.createRestriction(filmRestrictionsDto)
    }

    @GetMapping("/{filmId}")
    fun getFilmRestrictions(@PathVariable("filmId") filmId: UUID): FilmRestrictionsDto {
        return filmRestrictionFacade.getRestrictions(filmId)
    }
}