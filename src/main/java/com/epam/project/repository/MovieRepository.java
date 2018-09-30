package com.epam.project.repository;

import com.epam.project.Movie;
import com.epam.project.api.IMovieRepository;
import org.neo4j.driver.v1.Driver;

import javax.inject.Inject;
import java.util.Collection;

public class MovieRepository implements IMovieRepository {

    private Driver driver;

    @Inject
    public MovieRepository(Driver driver) {
        this.driver = driver;
    }

    @Override
    public Movie findByTitle(String title) {

        return null;
    }

    @Override
    public Collection<Movie> findByTitleLike(String title) {
        return null;
    }

    @Override
    public Collection<Movie> graph(int limit) {
        return null;
    }
}
