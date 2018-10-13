package com.epam.project.module;

import com.google.inject.Binder;
import com.typesafe.config.Config;
import org.jooby.Env;
import org.jooby.Jooby;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.SessionFactory;

/**
 * Module for Neo4J OGM support.
 */
public class Neo4jOGM implements Jooby.Module {

    @Override
    public void configure(Env env, Config conf, Binder binder) {
        var configuration = new Configuration.Builder()
            .uri(conf.getString("db.url"))
            .credentials(conf.getString("db.user"), conf.getString("db.password"))
            .build();
        var sessionFactory = new SessionFactory(configuration,
            "com.epam.project.domain");
        binder.bind(SessionFactory.class).toInstance(sessionFactory);
    }
}
