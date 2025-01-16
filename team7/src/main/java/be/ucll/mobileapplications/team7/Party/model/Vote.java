package be.ucll.mobileapplications.team7.Party.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Vote {
    private Long userId;
    private Boolean wantToSeeTheMovie;
    private Long movieId;

    public Vote() {
    }

    public Vote(Long userId, Long movieId, Boolean wantToSeeTheMovie) {
        this.userId = userId;
        this.movieId = movieId;
        this.wantToSeeTheMovie = wantToSeeTheMovie;
    }

  public Long getMovieId() {
    return this.movieId;
  }

  public void setMovieId(Long movieId) {
    this.movieId = movieId; 
  }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setWantToSeeTheMovie(Boolean wantToSeeTheMovie) {
        this.wantToSeeTheMovie = wantToSeeTheMovie;
    }

    public Boolean getWantToSeeTheMovie() {
      return this.wantToSeeTheMovie;
    }
}
