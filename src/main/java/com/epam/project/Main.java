package com.epam.project;

import com.epam.project.domain.Movie;
import com.epam.project.module.Neo4jOGM;
import org.jooby.Jooby;
import org.jooby.json.Jackson;
import org.neo4j.ogm.session.SessionFactory;

public class Main extends Jooby {

    public static void main(String[] args) {
        var app = new Jooby().port(8888);
        app.use(new Neo4jOGM());
        app.use(new Jackson());
        app.path("movies", () -> {
           app.get(req -> {
               var session = app.require(SessionFactory.class).openSession();
               return session.loadAll(Movie.class);
           });
           app.get(":id", req -> {
               var session = app.require(SessionFactory.class).openSession();
               long value = req.param("id").longValue();
               return session.load(Movie.class, value);
           });
        });
        app.start();
    }
}
