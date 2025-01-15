package be.ucll.mobileapplications.team7.Movie.model;

import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;

import be.ucll.mobileapplications.team7.Party.model.Party;
import be.ucll.mobileapplications.team7.User.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    public String title;

    @NotEmpty
    public Set<Genre> genres;

    @ManyToMany(mappedBy = "history")
    @JsonBackReference("user-history")
    private Set<User> watchedBy = new HashSet<>();

    @ManyToMany(mappedBy = "moviesToBeWatched")
    @JsonBackReference("user-to-be-watched")
    private Set<User> toBeWatchedBy = new HashSet<>();

    @ManyToMany(mappedBy = "selectedMovies")
    private Set<Party> selectedInParty = new HashSet<>();

    @ManyToMany(mappedBy = "suggestedMovies")
    private Set<Party> suggestedInParty = new HashSet<>();

    public Movie(String title, Set<Genre> genres) {
        this.title = title;
        this.genres = genres;
        this.watchedBy = new HashSet<>();
        this.toBeWatchedBy = new HashSet<>();
        this.selectedInParty = new HashSet<>();
        this.suggestedInParty = new HashSet<>();
    }

    public Movie() {
    }

    public void addWatchedByUser(User user) {
        this.getWatchedBy().add(user);
    }

    public void addToBeWatchedByUser(User user) {
        this.getToBeWatchedBy().add(user);
    }

    public long getId() {
        return this.id;
    }

    public Set<User> getWatchedBy() {
        return watchedBy;
    }

    public Set<User> getToBeWatchedBy() {
        return toBeWatchedBy;
    }

    public String getTitle() {
        return this.title;
    }

    public Set<Genre> getGenres() {
        return this.genres;
    }

    public Set<Party> getSelectedInParty() {
        return this.selectedInParty;
    }

    public Set<Party> getSuggestedInParty() {
        return this.suggestedInParty;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

}
