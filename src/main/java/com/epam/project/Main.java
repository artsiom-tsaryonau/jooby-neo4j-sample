package com.epam.project;

import com.epam.project.domain.Movie;
import com.epam.project.module.Neo4jOGM;
import com.epam.project.rest.MovieController;
import org.jooby.Jooby;
import org.jooby.json.Jackson;
import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Jooby {

        private static final Logger LOG = LoggerFactory.getLogger(Main.class);

        {
            use(new Neo4jOGM());
            use(new Jackson());

            // use(MovieController.class);

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

        public static void main(String[] args) {
            run(Main::new, args);
        }
    }
