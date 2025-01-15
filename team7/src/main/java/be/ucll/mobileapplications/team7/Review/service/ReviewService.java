package be.ucll.mobileapplications.team7.Review.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import be.ucll.mobileapplications.team7.Movie.model.Movie;
import be.ucll.mobileapplications.team7.Movie.service.MovieRepository;
import be.ucll.mobileapplications.team7.Review.model.Review;
import be.ucll.mobileapplications.team7.User.model.User;
import be.ucll.mobileapplications.team7.User.service.UserRepository;

@Service
public class ReviewService {
  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ReviewRepository reviewRepository;

  public ReviewService() {
  }

  public List<Review> getAllReviews() {
    return reviewRepository.findAll();
  }

  public Review deleteReview(Long reviewId) throws ReviewServiceException {

    Optional<Review> reviewOpt = reviewRepository.findById(reviewId);

    if (reviewOpt.isEmpty()) {
      throw new ReviewServiceException("Review", "Review with ID " + reviewId + " not found");
    }

    Review review = reviewOpt.get();

    reviewRepository.delete(review);

    return review;
  }

  public Review addReview(Review review, String email, String title) throws ReviewServiceException {

    User user = userRepository.findUserByEmail(email);
    if (user == null) {
      throw new ReviewServiceException("User", "User with email " + email + " not found");
    }

    Movie movie = movieRepository.findMovieByTitle(title);
    if (movie == null) {
      throw new ReviewServiceException("Movie", "Movie with title " + title + " not found");
    }

    Review existingReview = reviewRepository.findByUserAndMovie(user, movie);

    if (existingReview != null) {
      existingReview.setRating(review.getRating());
      existingReview.setComment(review.getComment());
      reviewRepository.save(existingReview);
      return existingReview;
    }
    else {
      review.setUser(user);
      review.setMovie(movie);
  
      reviewRepository.save(review);
  
      movie.getReviews().add(review);
      user.getReviews().add(review);
  
      userRepository.save(user);
      movieRepository.save(movie);
      return review;
    }

    
  }
}
