package be.ucll.mobileapplications.team7.Party.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import be.ucll.mobileapplications.team7.Movie.model.Movie;
import be.ucll.mobileapplications.team7.Movie.service.MovieRepository;
import be.ucll.mobileapplications.team7.Movie.service.MovieService;
import be.ucll.mobileapplications.team7.Party.model.Party;
import be.ucll.mobileapplications.team7.Party.model.PartyOptions;
import be.ucll.mobileapplications.team7.Party.model.Status;
import be.ucll.mobileapplications.team7.Party.model.Vote;
import be.ucll.mobileapplications.team7.User.model.User;
import be.ucll.mobileapplications.team7.User.service.UserRepository;

@Service
public class PartyService {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    public List<Party> getAllParties() {
        return partyRepository.findAll();
    }

    public Party createParty(int creatorId, PartyOptions partyOptions) throws PartyServiceException {
        User existingCreator = userRepository.findById(creatorId);

        if (existingCreator == null) {
            throw new PartyServiceException("id", "This user does not exist");
        }

        Party party = new Party(existingCreator, partyOptions);

        partyRepository.save(party);
        return party;
    }

    public Party joinParty(int partyId, int userId, String givenCode) throws PartyServiceException {
        
        User existingUser = userRepository.findById(userId);
        Party existingParty = partyRepository.findById(partyId);

        if (existingUser == null) {
            throw new PartyServiceException("id", "This user does not exist");
        }

        if (existingParty == null) {
            throw new PartyServiceException("id", "This party does not exist");
        }

        if (canThePartyBeJoined(existingParty, existingUser, givenCode)) {
            existingParty.addPartyMember(existingUser);
        }

        return partyRepository.save(existingParty);

    }

    public Party startParty(int partyId, int userId) throws PartyServiceException {
        Party existingParty = partyRepository.findById(partyId);

        if (existingParty == null) {
            throw new PartyServiceException("id", "This party does not exist");
        }

        if (existingParty.getPartyCreator().getId() != userId) {
            throw new PartyServiceException("User", "You are not the owner of the party");
        }

        if (existingParty.getStatus() != Status.CAN_BE_JOINED) {
            throw new PartyServiceException("Status", "This party cannot be started");
        }

        existingParty.setStatusVoting();
        existingParty.setSuggestedMovies(selectMoviesForParty(existingParty));
        return partyRepository.save(existingParty);
    }

    public Boolean voteForMovie(int partyId, int userId, int movieId, Boolean wantToSeeTheMovie) throws PartyServiceException {
        Party existingParty = partyRepository.findById(partyId);
        User existingUser = userRepository.findById(userId);
        Movie existingMovie = movieRepository.findById(movieId);

        if (existingParty == null) {
            throw new PartyServiceException("id", "This party does not exist");
        }

        if (existingUser == null) {
            throw new PartyServiceException("id", "This user does not exist");
        }

        if (existingMovie == null) {
            throw new PartyServiceException("id", "This movie does not exist");
        }

        if (existingParty.getStatus() != Status.VOTING) {
            throw new PartyServiceException("Status", "This party cannot be voted on");
        }

        if (!existingParty.getSuggestedMovies().contains(existingMovie)) {
            throw new PartyServiceException("Movie", "This movie is not in this party");
        }

        if ((!existingParty.getPartyMembers().contains(existingUser) && !existingParty.getPartyCreator().equals(existingUser))) {
            throw new PartyServiceException("User", "User is not a member of this party");
        }

        if (existingParty.getVotes().stream().anyMatch(vote -> (vote.getUserId().equals(userId) && vote.getMovieId().equals(movieId)))) {
            throw new PartyServiceException("User", "User has already voted for this movie");
        }

        existingParty.getVotes().add(new Vote(Long.valueOf(userId), Long.valueOf(movieId), wantToSeeTheMovie));
        partyRepository.save(existingParty);

        return checkIfUserHasEndedVoting(existingParty, Long.valueOf(userId));
    }

    public Party closeParty(int partyId, int userId) throws PartyServiceException {
        Party existingParty = partyRepository.findById(partyId);

        if (existingParty == null) {
            throw new PartyServiceException("Id", "This party does not exist");
        }

        if (existingParty.getPartyCreator().getId() != userId) {
            throw new PartyServiceException("User", "You are not the owner of the party");
        }

        if (existingParty.getStatus() != Status.VOTING) {
            throw new PartyServiceException("Status", "This party cannot be closed");
        }

        existingParty.generateSelectedMovies(); 
        existingParty.setStatusClosed();

        return partyRepository.save(existingParty);
    }

    private Boolean checkIfUserHasEndedVoting(Party existingParty, Long userId) {
        long userVoteCount = existingParty.getVotes().stream()
            .filter(vote -> vote.getUserId().equals(userId))
            .count();
    
        return userVoteCount == existingParty.getSuggestedMovies().size();
    }
    
    private Set<Movie> selectMoviesForParty(Party party) {

        List<Movie> movies = movieService.getMoviesBySomeGenres(party.getPartyOptions().getGenres());
        
        if (!party.getPartyOptions().getAcceptAlreadyWatchedMovies()) {
            List<Movie> alreadySeenMovies = new ArrayList<>();

            for (User user : party.getPartyMembers()) {
                alreadySeenMovies.addAll(user.getHistory());
            }

            movies.removeAll(alreadySeenMovies);
        }
        
        movies.removeIf(movie -> movie.getLanguages().stream()
        .noneMatch(language -> party.getPartyOptions().getLanguages().contains(language)));

        movies.removeIf(movie -> movie.getStreamingPlatforms().stream()
        .noneMatch(streamingPlatform -> party.getPartyOptions().getStreamingPlatforms().contains(streamingPlatform)));

        movies.removeIf(movie -> movie.getAverageImdbRating() < party.getPartyOptions().getMinImdbRating());

        return new HashSet<>(movies);
    }
    
    private Boolean canThePartyBeJoined(Party existingParty, User existingUser, String givenCode) throws PartyServiceException {

        if (existingParty.getStatus() != Status.CAN_BE_JOINED) {
            throw new PartyServiceException("Status", "This party cannot be joined");
        }
        
        if (!existingParty.getPartyCode().equals(givenCode)) {
            throw new PartyServiceException("Code", "The code is not correct");
        }

        if (existingParty.getPartyMembers().contains(existingUser)) {
            throw new PartyServiceException("User", "User is already member of this party");
        }

        return true;
    }

    // private Boolean checkIfPartyHasEndedVoting(Party existingParty) {
    //     return (existingParty.getVotes().size() == ((existingParty.getPartyMembers().size() + 1) * existingParty.getSuggestedMovies().size()));
    // }
}
