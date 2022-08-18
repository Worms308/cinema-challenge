package com.worms308.cinemachallenge.showcreator

import com.worms308.cinemachallenge.filmarchive.FilmArchiveFacade
import com.worms308.cinemachallenge.scheduler.SchedulerFacade
import org.springframework.context.annotation.Configuration

@Configuration
class ShowCreatorConfiguration {

//    @Bean
    fun showCreator(
        filmArchive: FilmArchiveFacade,
        scheduler: SchedulerFacade
    ): ShowCreator {
        return ShowCreator(filmArchive, scheduler)
    }
}