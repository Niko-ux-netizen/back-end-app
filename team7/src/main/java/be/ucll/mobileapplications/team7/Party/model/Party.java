package be.ucll.mobileapplications.team7.Party.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import be.ucll.mobileapplications.team7.Movie.model.Movie;
// import be.ucll.mobileapplications.team7.Movie.model.Movie;
import be.ucll.mobileapplications.team7.User.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "party")
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_email")
    @JsonBackReference
    private User partyCreator;

    @ManyToMany(mappedBy = "joinedParties")
    private Set<User> partyMembers;

    @ManyToMany
    @JoinTable(name = "party_suggested_movies", joinColumns = @JoinColumn(name = "party_id"), inverseJoinColumns = @JoinColumn(name = "movie_title"))
    private Set<Movie> suggestedMovies;

    @ManyToMany
    @JoinTable(name = "party_selected_movies", joinColumns = @JoinColumn(name = "party_id"), inverseJoinColumns = @JoinColumn(name = "movie_title"))
    private Set<Movie> selectedMovies;

    private LocalDate creationDate;

    private Status status;

    public Party(User partyCreator) {
        this.partyCreator = partyCreator;
        this.partyMembers = new HashSet<>();
        this.suggestedMovies = new HashSet<>();
        this.selectedMovies = new HashSet<>();
        this.creationDate = LocalDate.now();
        this.status = Status.OPEN;
    }

    public Party() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getPartyCreator() {
        return partyCreator;
    }

    public void setPartyCreator(User partyCreator) {
        this.partyCreator = partyCreator;
    }

    public Set<User> getPartyMembers() {
        return partyMembers;
    }

    public void setPartyMembers(Set<User> partyMembers) {
        this.partyMembers = partyMembers;
    }

    public Set<Movie> getSuggestedMovies() {
        return suggestedMovies;
    }

    public void setSuggestedMovies(Set<Movie> suggestedMovies) {
        this.suggestedMovies = suggestedMovies;
    }

    public Set<Movie> getSelectedMovies() {
        return selectedMovies;
    }

    public void setSelectedMovies(Set<Movie> selectedMovies) {
        this.selectedMovies = selectedMovies;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<User> addPartyMember(User partyMember) {
        this.partyMembers.add(partyMember);
        return this.partyMembers;
    }

    public Status setStatusStarted() {
        return this.status = Status.STARTED;
    }

    public Set<Movie> addSuggestedMovie(Movie movie) {
        this.suggestedMovies.add(movie);
        return this.suggestedMovies;
    }

    public Set<Movie> addSelectedMovie(Movie movie) {
        this.selectedMovies.add(movie);
        return this.selectedMovies;
    }
}
