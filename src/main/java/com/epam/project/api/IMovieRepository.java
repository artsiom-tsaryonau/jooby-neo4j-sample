package com.epam.project.api;

import com.epam.project.domain.Movie;

import java.util.Collection;

public interface IMovieRepository {
    Movie findByTitle(String title);
    Collection<Movie> findByTitleLike(String title);
    Collection<Movie> graph(int limit);
}
