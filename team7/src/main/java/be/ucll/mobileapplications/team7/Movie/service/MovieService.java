package be.ucll.mobileapplications.team7.Movie.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import be.ucll.mobileapplications.team7.Movie.model.Movie;

@Service
public class MovieService {

  @Autowired
  private MovieRepository movieRepository;

  public MovieService() {
  }

  public List<Movie> getAllMovies() {
    return movieRepository.findAll();
  }

  public Movie addMovie(Movie movie) throws MovieServiceException {
    System.out.println("Adding movie with title: " + movie.getTitle());
    String title = movie.getTitle();
    Movie existingMovie = movieRepository.findMovieByTitle(title);

    if (existingMovie != null) {
      throw new MovieServiceException("title", "User with given email already exists");
    }
    movieRepository.save(movie);
    return movie;
  }
}
