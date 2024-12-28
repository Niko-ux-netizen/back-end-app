package be.ucll.mobileapplications.team7.Movie.service;

import org.springframework.data.jpa.repository.JpaRepository;
import be.ucll.mobileapplications.team7.Movie.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, String> {
  public Movie findMovieByTitle(String title);
}
