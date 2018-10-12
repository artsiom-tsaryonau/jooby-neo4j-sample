package com.epam.project.embed;

import org.jooby.Jooby;
import org.jooby.json.Jackson;
import org.jooby.neo4j.Neo4j;
import org.neo4j.driver.internal.InternalNode;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MovieRepositoryApp extends Jooby {
    private static final String FIND_BY_TITLE = "MATCH (m:Movie {title:{title}}) RETURN m";
    private static final String FIND_BY_TITLE_LIKE
            = "MATCH (m:Movie) WHERE m.title =~ {titleLike} RETURN m";
    private static final String GRAPH = "MATCH (m:Movie)<-[r:ACTED_IN]-(a:Person) RETURN m,r,a LIMIT {limit}";

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieRepositoryApp.class);

    {
        use(new Neo4j());
        use(new Jackson());

        get("/findByTitle", req -> {
            var title = req.param("title").value();
            var session = require(Driver.class).session();
            try (session) {
                return session.run(FIND_BY_TITLE, Map.of("title", title)).single().asMap();
            }
        });

        get("/findByTitleLike", req -> {
            var titleLike = req.param("title").value();
            var session = require(Driver.class).session();
            try (session) {
                Iterable<Record> iterable =
                        () -> session.run(FIND_BY_TITLE_LIKE, Map.of("titleLike", ".*" + titleLike + ".*"));
                var list = StreamSupport
                        .stream(iterable.spliterator(), false)
                        .map(Record::asMap)
                        .map(Map::values)
                        .flatMap(Collection::stream) // nodes
                        .map(node -> ((InternalNode) node).asMap())
                        .collect(Collectors.toList());
                LOGGER.info("Processed list: {}", list);
                return list;
            }
        });

        get("/graph", req -> {
            var limit = req.param("limit").intValue();
            var session = require(Driver.class).session();

            // play: movies
            try (session) {
                var records = session.run(GRAPH, Map.of("limit", limit));

                var nodes = new ArrayList<>();
                var rels = new ArrayList<>();

                while (records.hasNext()) {
                    var record = records.next();
                    nodes.add(record);
                }

            }
           return Map.of();
        });
    }
}
