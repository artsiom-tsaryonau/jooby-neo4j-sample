package com.epam.project.rest;

import com.epam.project.domain.Movie;
import org.jooby.mvc.GET;
import org.jooby.mvc.Path;
import org.neo4j.ogm.session.SessionFactory;

import javax.inject.Inject;
import java.util.Collection;

    @Path("/movies")
    public class MovieController {

        private SessionFactory sessionFactory;

        @Inject
        public MovieController(SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
        }

        @GET
        public Collection<Movie> getMovies() {
            var session = sessionFactory.openSession();
            return session.loadAll(Movie.class);
        }

    }
