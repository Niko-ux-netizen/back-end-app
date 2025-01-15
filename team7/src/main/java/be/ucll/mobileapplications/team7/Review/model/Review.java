package be.ucll.mobileapplications.team7.Review.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonBackReference;
import be.ucll.mobileapplications.team7.Movie.model.Movie;
import be.ucll.mobileapplications.team7.User.model.User;
import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public long id;

  @NotNull(message = "Movie rating is required")
  @Max(value = 5, message = "Rating must be between 0 and 5")
  @Min(value = 0, message = "Rating must be between 0 and 5")
  public Integer rating;
  
  public String comment;

  @ManyToOne
  @JoinColumn(name = "user_email", nullable = false)
  @JsonBackReference("user-reviews")
  private User user;

  @ManyToOne
  @JoinColumn(name = "movie_title", nullable = false)
  @JsonBackReference("movie-reviews")
  private Movie movie;

  public Review() {
  }

  public Review(Integer rating, String comment) {
    this.rating = rating;
    this.comment = comment;
  }


  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Integer getRating() {
    return this.rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Movie getMovie() {
    return this.movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public String getComment() {
    return this.comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}