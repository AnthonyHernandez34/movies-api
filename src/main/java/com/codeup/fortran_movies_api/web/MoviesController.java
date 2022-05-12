package com.codeup.fortran_movies_api.web;

import com.codeup.fortran_movies_api.data.Director;
import com.codeup.fortran_movies_api.data.DirectorsRepository;
import com.codeup.fortran_movies_api.data.Genre;
import com.codeup.fortran_movies_api.data.GenresRepository;
import com.codeup.fortran_movies_api.data.Movie;
import com.codeup.fortran_movies_api.data.MoviesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/movies")
public class MoviesController {

    private final MoviesRepository moviesRepository;
    private final DirectorsRepository directorsRepository;
    private final GenresRepository genresRepository;

    public MoviesController(MoviesRepository moviesRepository, DirectorsRepository directorsRepository, GenresRepository genresRepository) {
        this.moviesRepository = moviesRepository;
        this.directorsRepository = directorsRepository;
        this.genresRepository = genresRepository;
    }

    @GetMapping("all")
    public List<Movie> getAll() {
        return moviesRepository.findAll();
    }

    @GetMapping("{id}")
    public Movie getById(@PathVariable long id) {
        return moviesRepository.findById(id).orElse(null);
    }

    @GetMapping("search")
    public List<Movie> getByTitle(@RequestParam("title") String title) {
        // TODO: we need to create the findByTitle() method in our MoviesRepository - magic!
        return moviesRepository.findByTitle(title);
    }

    @GetMapping("search/year") // api/movies/search/year
    public List<Movie> getByYearRange(@RequestParam("startYear") int startYear, @RequestParam("endYear") int endYear) {
        // TODO: @RequestParam expects a query parameter in the request URL
        //  to have a param matching what is in the annotation (ie: @RequestParam("startYear"))
        return moviesRepository.findByYearRange(startYear, endYear);
    }

    @GetMapping("search/director")
    public List<Director> getByDirector(@RequestParam("name") String directorName) {
        List<Director> directors = directorsRepository.findByName(directorName);
        return directors;
    }

    @GetMapping("search/genre")
    public List<Genre> getByGenre(@RequestParam("name") String genreType) {
        List<Genre> genres = genresRepository.findByName(genreType);
        return genres;
    }

    @PostMapping
    public void create(@RequestBody Movie movie) {

        moviesRepository.save(movie);
    }

    @PostMapping("many")
    public void createMany(@RequestBody List<Movie> movies) {
        moviesRepository.saveAll(movies);
    }

    @PutMapping
    public void updateOne(@RequestBody Movie movie) {
        moviesRepository.save(movie);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable long id) throws IOException {
        try {
            moviesRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No matching movie with ID: " + id);
        }
    }


}