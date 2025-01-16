package be.ucll.mobileapplications.team7.Party.model;

import java.util.Set;
import be.ucll.mobileapplications.team7.Movie.model.Genre;
import be.ucll.mobileapplications.team7.Movie.model.Language;
import be.ucll.mobileapplications.team7.Movie.model.StreamingPlatform;
import jakarta.persistence.Embeddable;

@Embeddable
public class PartyOptions {

  private Set<Genre> genres;

  private Boolean acceptAlreadyWatchedMovies;

  private Set<StreamingPlatform> streamingPlatforms;

  private Set<Language> languages;

  private Double minImdbRating; 

  public PartyOptions() {
  }

  public PartyOptions(Set<Genre> genres, Boolean acceptAlreadyWatchedMovies, Set<StreamingPlatform> streamingPlatforms, Set<Language> languages, Double minImdbRating) {
    this.genres = genres;
    this.acceptAlreadyWatchedMovies = acceptAlreadyWatchedMovies;
    this.streamingPlatforms = streamingPlatforms;
    this.languages = languages;
    this.minImdbRating = minImdbRating;
  }

  public Set<StreamingPlatform> getStreamingPlatforms() {
    return this.streamingPlatforms;
  }

  public void setStreamingPlatforms(Set<StreamingPlatform> streamingPlatforms) {
    this.streamingPlatforms = streamingPlatforms;
  }

  public Set<Language> getLanguages() {
    return this.languages;
  }

  public void setLanguages(Set<Language> languages) {
    this.languages = languages;
  }

  public Double getMinImdbRating() {
    return this.minImdbRating;
  }

  public void setMinImdbRating(Double minImdbRating) {
    this.minImdbRating = minImdbRating;
  }

  public Set<Genre> getGenres() {
    return this.genres;
  }

  public void setGenres(Set<Genre> genres) {
    this.genres = genres;
  }

  public Boolean isAcceptAlreadyWatchedMovies() {
    return this.acceptAlreadyWatchedMovies;
  }

  public Boolean getAcceptAlreadyWatchedMovies() {
    return this.acceptAlreadyWatchedMovies;
  }

  public void setAcceptAlreadyWatchedMovies(Boolean acceptAlreadyWatchedMovies) {
    this.acceptAlreadyWatchedMovies = acceptAlreadyWatchedMovies;
  }

}
