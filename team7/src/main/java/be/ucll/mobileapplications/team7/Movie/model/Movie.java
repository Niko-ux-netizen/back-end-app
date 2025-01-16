package be.ucll.mobileapplications.team7.Movie.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import be.ucll.mobileapplications.team7.Review.model.Review;

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

    public String tagline;

    @NotEmpty
    public Set<Genre> genres;

    public Date releaseDate;

    public Set<Language> languages;
    
    public Double averageImdbRating; 

    public Set<StreamingPlatform> streamingPlatforms;
    

    @ManyToMany(mappedBy = "history")
    @JsonBackReference("user-history")
    private Set<User> watchedBy = new HashSet<>();

    @ManyToMany(mappedBy = "moviesToBeWatched")
    @JsonBackReference("user-to-be-watched")
    private Set<User> toBeWatchedBy = new HashSet<>();

    @ManyToMany(mappedBy = "deniedMovies")
    @JsonBackReference("user-denied")
    private Set<User> deniedBy;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("movie-reviews")
    private List<Review> reviews;
    

    @ManyToMany(mappedBy = "selectedMovies")
    @JsonBackReference("party-selected")
    private Set<Party> selectedInParty = new HashSet<>();

    @ManyToMany(mappedBy = "suggestedMovies")
    @JsonBackReference("party-suggested")
    private Set<Party> suggestedInParty = new HashSet<>();

    public Movie(String title, Set<Genre> genres, Date releaseDate, Set<Language> languages, Set<StreamingPlatform> streamingPlatforms, String tagline, Double averageImdbRating) {
        this.title = title;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.languages = languages;
        this.tagline = tagline;
        this.averageImdbRating = averageImdbRating;
        this.streamingPlatforms = streamingPlatforms;

        this.watchedBy = new HashSet<>();
        this.toBeWatchedBy = new HashSet<>();
        this.reviews = new ArrayList<>();
    }

    public Movie() {
    }


    public Set<StreamingPlatform> getStreamingPlatforms() {
        return this.streamingPlatforms;
    }

    public void setStreamingPlatforms(Set<StreamingPlatform> streamingPlatforms) {
        this.streamingPlatforms = streamingPlatforms;
    }

    public Set<Party> getSelectedInParty() {
        return this.selectedInParty;
    }

    public void setSelectedInParty(Set<Party> selectedInParty) {
        this.selectedInParty = selectedInParty;
    }

    public Set<Party> getSuggestedInParty() {
        return this.suggestedInParty;
    }

    public void setSuggestedInParty(Set<Party> suggestedInParty) {
        this.suggestedInParty = suggestedInParty;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<Language> getLanguages() {
        return this.languages;
    }

    public void setLanguages(Set<Language> spokenLanguages) {
        this.languages = spokenLanguages;
    }

    public String getTagline() {
        return this.tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public Double getAverageImdbRating() {
        return this.averageImdbRating;
    }

    public void setAverageImdbRating(Double averageImdbRating) {
        this.averageImdbRating = averageImdbRating;
    }

    public Double getAverageRating() {
        Double totalRating = 0.0;
        Integer amountRated = this.getReviews().size();

        for (Review review : this.getReviews()) {
            totalRating += review.getRating();
        }

        return totalRating / amountRated;
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

    public void addToBeWatchedByUser(User user) {
        this.getToBeWatchedBy().add(user);
    }

    public long getId() {
        return this.id;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

}
