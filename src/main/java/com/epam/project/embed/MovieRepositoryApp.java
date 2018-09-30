package com.epam.project.embed;

import org.jooby.Jooby;
import org.jooby.neo4j.Neo4j;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;

import java.util.List;
import java.util.Map;

public class MovieRepositoryApp extends Jooby {
    private static final String FIND_BY_TITLE = "MATCH (n:Movie {title:{title}}) RETURN n";

    {
        use(new Neo4j());

        get("/findByTitle", req -> {
            var title = req.param("title").value();
            var session = require(Session.class);
            List<Record> list;
            try (session) {
                // TODO: investigate issue with closed connection
                list = session
                        .run(FIND_BY_TITLE, Map.of("title", title))
                        .list();
            }
            return list;
        });
    }
}
