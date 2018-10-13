package com.epam.project;

import com.epam.project.embed.MovieRepositoryApp;
import com.epam.project.embed.PersonRepositoryApp;
import com.epam.project.module.Neo4jOGM;
import org.jooby.Jooby;
import org.jooby.json.Jackson;

public class Main extends Jooby {

    {
        use(new Neo4jOGM());
        use(new Jackson());

        use(new MovieRepositoryApp());
        use("actors", new PersonRepositoryApp());
    }

    public static void main(String[] args) {
        run(Main::new, args);
    }
}
