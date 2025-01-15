package be.ucll.mobileapplications.team7.Movie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import be.ucll.mobileapplications.team7.Movie.model.Genre;
import be.ucll.mobileapplications.team7.Movie.model.Movie;
import be.ucll.mobileapplications.team7.Movie.service.MovieService;
import be.ucll.mobileapplications.team7.Movie.service.MovieServiceException;
import be.ucll.mobileapplications.team7.ServiceException.ServiceException;
import be.ucll.mobileapplications.team7.User.service.UserServiceException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/movie")
public class MovieServiceController {

    @Autowired
    private MovieService movieService;

    @GetMapping()
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    
    @GetMapping("/swipe/user")
    public List<Movie> getMoviesByGenresOfTheUserForSwipe(@RequestParam String email, @RequestParam Boolean allMode) throws UserServiceException {
        return movieService.getMoviesByGenresOfTheUserForSwipe(email, allMode);
    }

    @GetMapping("/genres/all")
    public List<Movie> getMoviesByAllGenres(@RequestBody Set<Genre> genres) throws UserServiceException {
        return movieService.getMoviesByAllGenres(genres);
    }

    @GetMapping("/genres/some")
    public List<Movie> getMoviesBySomeGenres(@RequestBody Set<Genre> genres) throws UserServiceException {
        return movieService.getMoviesBySomeGenres(genres);
    }

    @PostMapping()
    public Movie addMovie(@RequestBody @Valid Movie movie) throws MovieServiceException {
        return movieService.addMovie(movie);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            MethodArgumentNotValidException.class })
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ ServiceException.class })
    public Map<String, String> handleServiceExceptions(ServiceException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("field", ex.getField());
        errorResponse.put("message", ex.getMessage());
        return errorResponse;
    }
}
