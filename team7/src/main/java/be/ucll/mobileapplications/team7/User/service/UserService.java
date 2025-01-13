package be.ucll.mobileapplications.team7.User.service;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import be.ucll.mobileapplications.team7.Movie.model.Movie;
import be.ucll.mobileapplications.team7.Movie.model.Genre;
import be.ucll.mobileapplications.team7.Movie.service.MovieRepository;
import be.ucll.mobileapplications.team7.User.model.User;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private MovieRepository movieRepository;

  public UserService() {
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User addUser(User user) throws UserServiceException {
    String email = user.getEmail();
    User existingUser = userRepository.findUserByEmail(email);
    if (existingUser != null) {
      throw new UserServiceException("email", "User with given email already exists");
    }
    userRepository.save(user);
    return user;
  }

  public User addGenresToUser(String email, Set<Genre> genres) throws UserServiceException {
    User existingUser = userRepository.findUserByEmail(email);

    if (existingUser == null) {
      throw new UserServiceException("email", "User with given email doesn't exists");
    }

    existingUser.setFavoriteGenres(genres);

    userRepository.save(existingUser);
    return existingUser;
  }

  public User removeMovieFromHistory(String email, String title) throws UserServiceException {

    User user = userRepository.findUserByEmail(email);
    Movie movie = movieRepository.findMovieByTitle(title);

    if (user == null) {
      throw new UserServiceException("userEmail", "User with email %s does not exist".formatted(email));
    }

    if (movie == null) {
      throw new UserServiceException("title", "This movie is not in the database");
    }

    if (!user.getHistory().contains(movie)) {
      throw new UserServiceException("movie", "User doesn't has this movie in his history list.");
    }

    user.getHistory().remove(movie);

    movie.getWatchedBy().remove(user);

    userRepository.save(user);
    return user;
  }

  public User removeMovieFromToBeWatchedList(String email, String title) throws UserServiceException {

    User user = userRepository.findUserByEmail(email);
    Movie movie = movieRepository.findMovieByTitle(title);

    if (user == null) {
      throw new UserServiceException("userEmail", "User with email %s does not exist".formatted(email));
    }

    if (movie == null) {
      throw new UserServiceException("title", "This movie is not in the database");
    }

    if (!user.getMoviesToBeWatched().contains(movie)) {
      throw new UserServiceException("movie", "User doesn't has this movie in his to be watched list.");
    }

    user.getMoviesToBeWatched().remove(movie);

    movie.getToBeWatchedBy().remove(user);

    userRepository.save(user);
    return user;
  }

  public User addMovieToHistoryListUser(String email, String title) throws UserServiceException {
    User user = userRepository.findUserByEmail(email);
    Movie movie = movieRepository.findMovieByTitle(title);

    if (user == null) {
      throw new UserServiceException("userEmail", "User with email %s does not exist".formatted(email));
    }

    if (movie == null) {
      throw new UserServiceException("title", "This movie is not in the database");
    }

    if (user.getMoviesToBeWatched().contains(movie)) {
      removeMovieFromToBeWatchedList(email, title);
    }

    if (user.getHistory().contains(movie)) {
      throw new UserServiceException("movie", "User already has this movie in his history list.");
    }

    user.addMovieToHistoryListUser(movie);

    movieRepository.save(movie);
    return userRepository.save(user);
  }

  public User addMovieToBeWatchedListUser(String email, String title) throws UserServiceException {
    User user = userRepository.findUserByEmail(email);
    Movie movie = movieRepository.findMovieByTitle(title);

    if (user == null) {
      throw new UserServiceException("userEmail", "User with email %s does not exist".formatted(email));
    }

    if (movie == null) {
      throw new UserServiceException("title", "This movie is not in the database");
    }

    if (user.getHistory().contains(movie)) {
      removeMovieFromHistory(email, title);
    }

    if (user.getMoviesToBeWatched().contains(movie)) {
      throw new UserServiceException("movie", "User already has this item in his to be watched list.");
    }

    if (user.getHistory().contains(movie)) {
      throw new UserServiceException("movie", "User already has this item in his to be watched list.");
    }

    user.addMovieToBeWatched(movie);

    movieRepository.save(movie);
    return userRepository.save(user);
  }
}
