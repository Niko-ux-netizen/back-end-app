package be.ucll.mobileapplications.team7.Party.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

// import be.ucll.mobileapplications.team7.Movie.model.Movie;
import be.ucll.mobileapplications.team7.User.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    private List<String> suggestedMovies;

    private List<String> selectedMovies;

    private LocalDate creationDate;

    private Status status;

    public Party(User partyCreator) {
        this.partyCreator = partyCreator;
        this.suggestedMovies = new ArrayList<>();
        this.selectedMovies = new ArrayList<>();
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

    public List<String> getSuggestedMovies() {
        return suggestedMovies;
    }

    public void setSuggestedMovies(List<String> suggestedMovies) {
        this.suggestedMovies = suggestedMovies;
    }

    public List<String> getSelectedMovies() {
        return selectedMovies;
    }

    public void setSelectedMovies(List<String> selectedMovies) {
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
}
