package com.worms308.cinemachallenge.restriction

import java.util.*
import java.util.concurrent.ConcurrentHashMap

//I decided not to implement removing/modification of restrictions due to lack of time
internal interface FilmRestrictionRepository {
    fun save(filmRestrictions: FilmRestrictions)

    fun findByFilmId(filmId: UUID): FilmRestrictions?
}

internal class InMemoryFilmRestrictionRepository : FilmRestrictionRepository {
    private val storage: MutableMap<UUID, FilmRestrictions> = ConcurrentHashMap()

    override fun save(filmRestrictions: FilmRestrictions) {
        storage[filmRestrictions.filmId] = filmRestrictions
    }

    override fun findByFilmId(filmId: UUID): FilmRestrictions? {
        return storage[filmId]
    }
}