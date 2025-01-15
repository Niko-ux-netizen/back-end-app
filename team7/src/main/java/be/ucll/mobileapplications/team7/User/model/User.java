package be.ucll.mobileapplications.team7.User.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import be.ucll.mobileapplications.team7.Movie.model.Genre;
import be.ucll.mobileapplications.team7.Movie.model.Movie;
import be.ucll.mobileapplications.team7.Party.model.Party;
import be.ucll.mobileapplications.team7.Review.model.Review;
import be.ucll.mobileapplications.team7.User.service.UserServiceException;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public long id;

  @NotBlank(message = "Username name is required")
  public String username;

  @Email(message = "Enter a valid email")
  public String email;

  @NotBlank(message = "Password is required")
  public String password;

  @Past(message = "Date of birth must be in the past")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  public LocalDate dateOfBirth;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "history", joinColumns = @JoinColumn(name = "user_email"), inverseJoinColumns = @JoinColumn(name = "movie_title"))
  private Set<Movie> history = new HashSet<>();

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "to_be_watched_movies", joinColumns = @JoinColumn(name = "user_email"), inverseJoinColumns = @JoinColumn(name = "movie_title"))
  private Set<Movie> moviesToBeWatched = new HashSet<>();

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "denied_movies", joinColumns = @JoinColumn(name = "user_email"), inverseJoinColumns = @JoinColumn(name = "movie_title"))
  private Set<Movie> deniedMovies;

  public Set<Genre> favoriteGenres = new HashSet<>();

  @OneToMany(mappedBy = "partyCreator")
  @JsonManagedReference
  private Set<Party> parties = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference("user-reviews")
  private List<Review> reviews;

  @ManyToMany
  @JoinTable(name = "user_party", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "party_id"))
  private Set<Party> joinedParties = new HashSet<>();

  public User(String username, String email, String password, LocalDate dateOfBirth) throws UserServiceException {
    this.username = username;
    this.email = email;
    this.password = password;
    if (password.strip().length() < 1) {
      throw new UserServiceException("password", "Password is required");
    }
    this.dateOfBirth = dateOfBirth;
    this.history = new HashSet<Movie>();
    this.moviesToBeWatched = new HashSet<Movie>();

    this.deniedMovies = new HashSet<Movie>();

    this.favoriteGenres = new HashSet<Genre>();

    this.reviews = new ArrayList<Review>();
    this.parties = new HashSet<Party>();
    this.joinedParties = new HashSet<Party>();
  }

  public User() {
  }

  public void addMovieToDeniedMovies(Movie movie) {
    this.getDeniedMovies().add(movie);

    movie.addDeniedByUser(this);
  }

  public Set<Movie> getDeniedMovies() {
    return this.deniedMovies;
  }

  public void addMovieToHistoryListUser(Movie movie) {
    this.getHistory().add(movie);

    movie.addWatchedByUser(this);
  }

  public Set<Movie> getHistory() {
    return this.history;
  }

  public void addMovieToBeWatched(Movie movie) {
    this.getMoviesToBeWatched().add(movie);

    movie.addWatchedByUser(this);
  }

  public Set<Movie> getMoviesToBeWatched() {
    return this.moviesToBeWatched;
  }

  public Set<Genre> getFavoriteGenres() {
    return this.favoriteGenres;
  }

  public void setFavoriteGenres(Set<Genre> genres) {
    this.favoriteGenres = genres;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public LocalDate getDateOfBirth() {
    return this.dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }
  public void setHistory(Set<Movie> history) {
    this.history = history;
  }
  public void setMoviesToBeWatched(Set<Movie> moviesToBeWatched) {
    this.moviesToBeWatched = moviesToBeWatched;
  }
  public void setDeniedMovies(Set<Movie> deniedMovies) {
    this.deniedMovies = deniedMovies;
  }

  public Set<Party> getParties() {
    return this.parties;
  }

  public void setParties(Set<Party> parties) {
    this.parties = parties;
  }

  public List<Review> getReviews() {
    return this.reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public Set<Party> getJoinedParties() {
    return this.joinedParties;
  }

  public void setJoinedParties(Set<Party> joinedParties) {
    this.joinedParties = joinedParties;
  }
}