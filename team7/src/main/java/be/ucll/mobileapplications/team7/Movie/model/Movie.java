package be.ucll.mobileapplications.team7.Movie.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import be.ucll.mobileapplications.team7.Review.model.Review;
import be.ucll.mobileapplications.team7.User.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @NotBlank(message = "Email is required")
    public String title;

    public Set<Genre> genres;

    @ManyToMany(mappedBy = "history")
    @JsonBackReference("user-history")
    private Set<User> watchedBy;

    @ManyToMany(mappedBy = "moviesToBeWatched")
    @JsonBackReference("user-to-be-watched")
    private Set<User> toBeWatchedBy;

    @ManyToMany(mappedBy = "deniedMovies")
    @JsonBackReference("user-denied")
    private Set<User> deniedBy;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("movie-reviews")
    private List<Review> reviews;

    public Movie(String title, Set<Genre> genres) {
        this.title = title;
        this.genres = genres;
        this.reviews = new ArrayList<>();
    }

    public Movie() {
    }

    public Set<User> getDeniedBy() {
        if (deniedBy == null) {
            deniedBy = new HashSet<>();
        }

        return deniedBy;
    }

    public void addDeniedByUser(User user) {
        this.getDeniedBy().add(user);
    }
    public void setWatchedBy(Set<User> watchedBy) {
        this.watchedBy = watchedBy;
    }
    public void setToBeWatchedBy(Set<User> toBeWatchedBy) {
        this.toBeWatchedBy = toBeWatchedBy;
    }
    public void setDeniedBy(Set<User> deniedBy) {
        this.deniedBy = deniedBy;
    }

    public List<Review> getReviews() {
        return this.reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }


    public Set<User> getWatchedBy() {
        if (watchedBy == null) {
            watchedBy = new HashSet<>();
        }

        return watchedBy;
    }

    public void addWatchedByUser(User user) {
        this.getWatchedBy().add(user);
    }

    public Set<User> getToBeWatchedBy() {
        if (toBeWatchedBy == null) {
            toBeWatchedBy = new HashSet<>();
        }

        return toBeWatchedBy;
    }

    public void addToBeWatchedByUser(User user) {
        this.getToBeWatchedBy().add(user);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Genre> getGenres() {
        return this.genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

}
