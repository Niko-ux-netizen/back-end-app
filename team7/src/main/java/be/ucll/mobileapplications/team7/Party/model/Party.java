package be.ucll.mobileapplications.team7.Party.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import be.ucll.mobileapplications.team7.Movie.model.Movie;
import be.ucll.mobileapplications.team7.User.model.User;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    private User partyCreator;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "joined_users", 
        joinColumns = @JoinColumn(name = "party_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private Set<User> partyMembers;

    private LocalDate creationDate;

    private Status status;

    private String partyCode;

    @Embedded
    private PartyOptions partyOptions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "suggested_movies", 
        joinColumns = @JoinColumn(name = "party_id"), 
        inverseJoinColumns = @JoinColumn(name = "movie_id"))
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private Set<Movie> suggestedMovies;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "selected_movies", 
        joinColumns = @JoinColumn(name = "party_id"), 
        inverseJoinColumns = @JoinColumn(name = "movie_id"))
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private Set<Movie> selectedMovies;
    
    @ElementCollection
    @CollectionTable(name = "party_votes", joinColumns = @JoinColumn(name = "party_id"))
    private List<Vote> votes = new ArrayList<>();

    public Party(User partyCreator, PartyOptions partyOptions) {
        this.partyCreator = partyCreator;
        this.partyMembers = new HashSet<>();
        this.creationDate = LocalDate.now();
        this.status = Status.CAN_BE_JOINED;
        this.partyCode = generatePartyCode();
        this.partyOptions = partyOptions;
    }

    public Party() {
    }

    public void generateSelectedMovies() {
        if (this.votes == null || this.votes.isEmpty()) {
            throw new IllegalStateException("No votes available to generate selected movies.");
        }
    
        Map<Long, Long> voteCounts = this.votes.stream()
            .filter(Vote::getWantToSeeTheMovie) 
            .collect(Collectors.groupingBy(Vote::getMovieId, Collectors.counting()));
    
        List<Long> topMovieIds = voteCounts.entrySet().stream()
            .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue())) 
            .limit(2)
            .map(Map.Entry::getKey)
            .toList();
    
        Set<Movie> topMovies = this.suggestedMovies.stream()
            .filter(movie -> topMovieIds.contains(movie.getId()))
            .collect(Collectors.toSet());
    
        this.setSelectedMovies(topMovies);
    }

    public List<Vote> getVotes() {
        if (this.votes == null) {
            this.votes = new ArrayList<Vote>();
        }
        return this.votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public Set<Movie> getSuggestedMovies() {
        return this.suggestedMovies;
    }

    public void setSuggestedMovies(Set<Movie> suggestedMovies) {
        this.suggestedMovies = suggestedMovies;
    }

    public Set<Movie> getSelectedMovies() {
        return this.selectedMovies;
    }

    public void setSelectedMovies(Set<Movie> selectedMovies) {
        this.selectedMovies = selectedMovies;
    }

    public PartyOptions getPartyOptions() {
        return this.partyOptions;
    }

    public void setPartyOptions(PartyOptions partyOptions) {
        this.partyOptions = partyOptions;
    }

    private String generatePartyCode() {
        int leftLimit = 65; // letter 'a'
        int rightLimit = 90; // letter 'z'
        int targetStringLength = 4;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();

        return generatedString;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPartyCode() {
        return this.partyCode;
    }

    public void setPartyCode(String partyCode) {
        this.partyCode = partyCode;
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

    public Status setStatusVoting() {
        return this.status = Status.VOTING;
    }

    public Status setStatusCanBeJoined() {
        return this.status = Status.CAN_BE_JOINED;
    }

    public Status setStatusClosed() {
        return this.status = Status.CLOSED;
    }
}
