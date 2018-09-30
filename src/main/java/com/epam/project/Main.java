package com.epam.project;

import com.epam.project.embed.MovieRepositoryApp;
import org.jooby.Jooby;

public class Main extends Jooby {
    {
        use("movies", new MovieRepositoryApp());
    }

    public static void main(String[] args) {
        run(Main::new, args);
    }
}
