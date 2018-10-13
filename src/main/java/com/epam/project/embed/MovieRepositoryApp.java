package com.epam.project.embed;

import com.epam.project.domain.Movie;
import org.jooby.Jooby;
import org.neo4j.ogm.session.SessionFactory;

public class MovieRepositoryApp extends Jooby {
    {
        get("/movies", req -> {
            var session = require(SessionFactory.class)
                    .openSession();
            return session.loadAll(Movie.class);
        });

        get("/movies/:id", req -> {
            var session = require(SessionFactory.class)
                    .openSession();
            long value = req.param("id").longValue();
            return session.load(Movie.class, value);
        });

        post("/movies", req -> {
            var body = req.body(Movie.class);
            var session = require(SessionFactory.class).openSession();

            session.save(body);
            return session.load(Movie.class, body.getId());
        });
    }
}
