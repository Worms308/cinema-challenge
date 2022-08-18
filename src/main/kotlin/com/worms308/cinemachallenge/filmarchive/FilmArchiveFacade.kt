package com.worms308.cinemachallenge.filmarchive

import com.worms308.cinemachallenge.filmarchive.dto.FilmDto
import java.util.*

interface FilmArchiveFacade {
    // here would be injected FilmRestrictionFacade in real implementation of this interface

    fun getFilmDetails(filmId: UUID): FilmDto
}