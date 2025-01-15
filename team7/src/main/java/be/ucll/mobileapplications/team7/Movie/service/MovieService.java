package be.ucll.mobileapplications.team7.Movie.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import be.ucll.mobileapplications.team7.Movie.model.Movie;

@Service
public class MovieService {

  @Autowired
  private MovieRepository movieRepository;

  public List<Movie> getAllMovies() {
    return movieRepository.findAll();
  }

  public Movie addMovie(Movie movie) throws MovieServiceException {
    String title = movie.getTitle();
    Movie existingMovie = movieRepository.findMovieByTitle(title);

    if (existingMovie != null) {
      throw new MovieServiceException(title, "Movie with given title already exists");
    }

    return movieRepository.save(movie);
  }
}
