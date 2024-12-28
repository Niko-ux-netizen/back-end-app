package be.ucll.mobileapplications.team7.Movie.model;

import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private Set<User> watchedBy;

    @ManyToMany(mappedBy = "moviesToBeWatched")
    @JsonBackReference
    private Set<User> toBeWatchedBy;

    public Movie(String title, Set<Genre> genres) {
        this.title = title;
        this.genres = genres;
    }

    public Movie() {
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
