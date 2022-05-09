package com.codeup.fortran_movies_api.web;


import io.micrometer.core.instrument.Meter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class MovieController {


    @GetMapping("all")
    public List<Movie> getAll() {
        return sampleMovies
    }


    @GetMapping("{Id}")
    public Movie getById(@PathVariable int id) {
        return sampleMovies.stream().filter((movie) -> {
            return movie.getId() == id;
    )}
                .findFirst()
                .orElse(null);
    }


    @PostMapping
    public void create(@RequestBody Movie movie) {
        sampleMovies.add(movie);

        System.out.println(movie);
    }
    @PostMapping("all")
    public void createAll(@RequestBody List<Movie>moviesToAdd){
        sampleMovies.addAll(moviesToAdd)
    }
}
