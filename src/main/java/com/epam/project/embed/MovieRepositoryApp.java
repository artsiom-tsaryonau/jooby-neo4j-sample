package com.epam.project.embed;

import org.jooby.Jooby;
import org.jooby.neo4j.Neo4j;
import org.neo4j.driver.v1.Driver;
import java.util.Map;

public class MovieRepositoryApp extends Jooby {
    private static final String FIND_BY_TITLE = "MATCH (n:Movie {title:{title}}) RETURN n";

    {
        use(new Neo4j());

        get("/findByTitle", req -> {
            var title = req.param("title").value();
            var session = require(Driver.class).session();
            try (session) {
                return session.run(FIND_BY_TITLE, Map.of("title", title)).list();
            }
        });
    }
}
