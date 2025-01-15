package be.ucll.mobileapplications.team7.Movie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import be.ucll.mobileapplications.team7.Movie.model.Genre;
import be.ucll.mobileapplications.team7.Movie.model.Movie;
import be.ucll.mobileapplications.team7.User.model.User;
import be.ucll.mobileapplications.team7.User.service.UserRepository;
import be.ucll.mobileapplications.team7.User.service.UserServiceException;

@Service
public class MovieService {

  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private UserRepository userRepository;

  public MovieService() {
  }

  public List<Movie> getAllMovies() {
    return movieRepository.findAll();
  }

  private Boolean movieIsNotInHistoryOrWatchListOrDeniedList(User user, Movie movie) throws UserServiceException {

    if (user == null) {
      throw new UserServiceException("User", "User doesn't exists");
    }

    return (!user.getHistory().contains(movie) && !user.getMoviesToBeWatched().contains(movie) && !user.getDeniedMovies().contains(movie));
  }

  public List<Movie> getMoviesByGenresOfTheUserForSwipe(String email) throws UserServiceException {
    
    User existingUser = userRepository.findUserByEmail(email);

    if (existingUser == null) {
      throw new UserServiceException("email", "User with given email doesn't exists");
    }

    List<Movie> movies = getAllMovies();

    List<Movie> selectedMovies = new ArrayList<>();

    for (Movie movie : movies) {
      if (movieIsNotInHistoryOrWatchListOrDeniedList(existingUser, movie)) {
        for (Genre genre : movie.getGenres()) {
          if (existingUser.getFavoriteGenres().contains(genre)) {
              selectedMovies.add(movie);
              break;
          }
      }
      }
    }

    return selectedMovies;
  }

  public List<Movie> getMoviesBySomeGenres(Set<Genre> genres) throws UserServiceException {
    
    List<Movie> movies = getAllMovies();

    List<Movie> selectedMovies = new ArrayList<>();

    for (Movie movie : movies) {
        for (Genre genre : movie.getGenres()) {
          if (genres.contains(genre)) {
              selectedMovies.add(movie);
              break;
          }
      }
    }

    return selectedMovies;
  }

  public List<Movie> getMoviesByAllGenres(Set<Genre> genres) throws UserServiceException {
    
    List<Movie> movies = getAllMovies();

    List<Movie> selectedMovies = new ArrayList<>();

    Boolean allGenres;

    for (Movie movie : movies) {
      allGenres = true;
      
      for (Genre genre : genres) {
        if (!movie.getGenres().contains(genre)) {
          allGenres = false;
        }
      } 

      if (allGenres) {
        selectedMovies.add(movie);
      }
    }

    return selectedMovies;
  }

  public Movie addMovie(Movie movie) throws MovieServiceException {
    String title = movie.getTitle();
    Movie existingMovie = movieRepository.findMovieByTitle(title);

    if (existingMovie != null) {
      throw new MovieServiceException("title", "User with given email already exists");
    }
    movieRepository.save(movie);
    return movie;
  }
}
