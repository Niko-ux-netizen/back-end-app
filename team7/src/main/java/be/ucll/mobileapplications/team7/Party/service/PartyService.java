package be.ucll.mobileapplications.team7.Party.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ucll.mobileapplications.team7.Movie.model.Movie;
import be.ucll.mobileapplications.team7.Movie.service.MovieRepository;
import be.ucll.mobileapplications.team7.Party.model.Party;
import be.ucll.mobileapplications.team7.User.model.User;
import be.ucll.mobileapplications.team7.User.service.UserRepository;
import be.ucll.mobileapplications.team7.User.service.UserServiceException;

@Service
public class PartyService {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    public List<Party> getAllParties() {
        return partyRepository.findAll();
    }

    public Party addParty(int creatorId) throws UserServiceException {
        User existingCreator = userRepository.findById(creatorId);

        if (existingCreator == null) {
            throw new UserServiceException("id", "this user does not exist");
        }

        Party party = new Party(existingCreator);

        partyRepository.save(party);
        return party;
    }

    public Party joinParty(int partyId, int userId) throws Exception {
        User existingUser = userRepository.findById(userId);
        Party existingParty = partyRepository.findById(partyId);

        if (existingUser == null) {
            throw new UserServiceException("id", "this user does not exist");
        }

        // Nieuw PartyServiceException nog aanmaken
        if (existingParty == null) {
            throw new UserServiceException("id", "this party does not exist");
        }

        if (!existingParty.getPartyMembers().contains(existingUser)) {
            existingParty.addPartyMember(existingUser);
        } else {
            throw new Exception("User is already member of this party");
        }
        return partyRepository.save(existingParty);

    }

    public Party startParty(int partyId) throws Exception {
        Party existingParty = partyRepository.findById(partyId);

        if (existingParty == null) {
            throw new Exception("this party does not exist");
        }

        existingParty.setStatusStarted();
        return partyRepository.save(existingParty);

    }

    public Party addSugestedMovie(int partyId, int movieId) throws Exception {
        Party existingParty = partyRepository.findById(partyId);
        Movie existingMovie = movieRepository.findById(movieId);

        if (existingMovie == null) {
            throw new Exception("This movie with this title does not exist");
        }
        if (existingParty == null) {
            throw new Exception("this party does not exist");
        }

        existingParty.addSuggestedMovie(existingMovie);
        return partyRepository.save(existingParty);
    }

    public Party addSelectedMovie(int partyId, int movieId) throws Exception {
        Party existingParty = partyRepository.findById(partyId);
        Movie existingMovie = movieRepository.findById(movieId);

        if (existingMovie == null) {
            throw new Exception("This movie with this title does not exist");
        }
        if (existingParty == null) {
            throw new Exception("this party does not exist");
        }

        existingParty.addSelectedMovie(existingMovie);
        return partyRepository.save(existingParty);
    }
}
