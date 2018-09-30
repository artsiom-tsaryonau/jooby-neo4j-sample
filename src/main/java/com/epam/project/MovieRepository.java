package com.epam.project;

import java.util.Collection;
import java.util.List;

public class MovieRepository {

    public Movie findByTitle(String title) {
        return new Movie();
    }

    public Collection<Movie> findByTitleLike(String title) {
        return List.of();
    }

    // @Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Person) RETURN m,r,a LIMIT {limit}")
    public Collection<Movie> graph(int limit) {
        return List.of();
    }
}
