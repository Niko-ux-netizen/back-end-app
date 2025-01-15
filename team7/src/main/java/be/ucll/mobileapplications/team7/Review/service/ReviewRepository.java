package be.ucll.mobileapplications.team7.Review.service;

import be.ucll.mobileapplications.team7.Movie.model.Movie;
import be.ucll.mobileapplications.team7.Review.model.Review;
import be.ucll.mobileapplications.team7.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>{
  
  public Review findByUserAndMovie(User user, Movie movie);
}
