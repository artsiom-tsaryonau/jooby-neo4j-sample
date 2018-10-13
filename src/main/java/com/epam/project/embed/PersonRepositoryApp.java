package com.epam.project.embed;

import com.epam.project.domain.Person;
import org.jooby.Jooby;
import org.neo4j.ogm.session.SessionFactory;

public class PersonRepositoryApp extends Jooby {
    {
        get(() -> require(SessionFactory.class).openSession()
            .loadAll(Person.class));
    }
}
