package com.codeup.fortran_movies_api.web;

import com.codeup.fortran_movies_api.data.DirectorsRepository;
import com.codeup.fortran_movies_api.data.MoviesRepository;
import com.codeup.fortran_movies_api.data.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/movies", headers = "Accept=application/json")
public class MoviesController {

    private List<Movie> sampleMovies = setMovies();
    private final MoviesRepository moviesRepository;
    private final DirectorsRepository directorsRepository;

    public MoviesController(MoviesRepository moviesRepository, DirectorsRepository directorsRepository){
        this.moviesRepository = moviesRepository;
        this.directorsRepository = directorsRepository;
    }

    @GetMapping("all")
    public List<Movie> getAll() {
        return sampleMovies;
    }

    @GetMapping
    public Movie one() {
        return sampleMovies.get(1);
    }

    @GetMapping("{id}") //
    public Movie getById(@PathVariable int id) {
        return sampleMovies.stream().filter((movie) -> {
                    return movie.getId() == id;
                })
                .findFirst()
                .orElse(null);

    }

    @PostMapping
    public void create(@RequestBody Movie movie){

        moviesRepository.save(movie);
    }

    @PostMapping ("many")
    public void createMany(@RequestBody List<Movie> movies){

        moviesRepository.saveAll(movies);
    }

    @PutMapping
    public void updateOne(@RequestBody Movie movie){
        moviesRepository.save(movie);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable int id) throws IOException {
        try {
            moviesRepository.deleteById(id);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No matching movie with ID: " + id);
        }
    }


    private List<Movie> setMovies() {
        List<Movie> movies = new ArrayList<>();
//        movies.add(new Movie(
//                1,
//                "Dune",
//                "1999",
//                "\tDenis Villeneuve",
//                "Oscar Isaac",
//                "https://en.wikipedia.org/wiki/Dune_(2021_film)/",
//                "Action",
//                "The film is the second theatrical adaptation of Dune following David Lynch's 1984 film."
//        ));
//        movies.add(new Movie(
//                2,
//                "Duness",
//                "2021",
//                "\tDenis Villeneuve",
//                "Oscar Isaac",
//                "https://en.wikipedia.org/wiki/Dune_(2021_film)/",
//                "Action",
//                "The film is the second theatrical adaptation of Dune following David Lynch's 1984 film."
//        ));
//        movies.add(new Movie(
//                3,
//                "Dunes",
//                "2021",
//                "\tDenis Villeneuve",
//                "Oscar Isaac",
//                "https://en.wikipedia.org/wiki/Dune_(2021_film)/",
//                "action",
//                "The film is the second theatrical adaptation of Dune following David Lynch's 1984 film."
//        ));
        return movies;
    }
}