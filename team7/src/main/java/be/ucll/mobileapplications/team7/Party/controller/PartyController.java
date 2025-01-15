package be.ucll.mobileapplications.team7.Party.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ucll.mobileapplications.team7.Party.model.Party;
import be.ucll.mobileapplications.team7.Party.service.PartyService;
import be.ucll.mobileapplications.team7.User.service.UserServiceException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/party")
public class PartyController {

    @Autowired
    private PartyService partyService;

    @GetMapping()
    public List<Party> getAllMovies() {
        return partyService.getAllParties();
    }

    @PostMapping("/{id}")
    public Party addParty(@PathVariable int id) throws UserServiceException {
        return partyService.addParty(id);
    }

    @PostMapping("/{partyId}/{userId}")
    public Party joinParty(@PathVariable int partyId, @PathVariable int userId) throws Exception {
        return partyService.joinParty(partyId, userId);
    }

    @PutMapping("/start/{partyId}")
    public Party startParty(@PathVariable int partyId) throws Exception {
        return partyService.startParty(partyId);
    }

    @PostMapping("suggestedMovie/{partyId}/{movieId}")
    public Party addSugestedMovie(@PathVariable int partyId, @PathVariable int movieId) throws Exception {
        return partyService.addSugestedMovie(partyId, movieId);
    }

    @PostMapping("selectedMovie/{partyId}/{movieId}")
    public Party addSelectedMovie(@PathVariable int partyId, @PathVariable int movieId) throws Exception {
        return partyService.addSelectedMovie(partyId, movieId);
    }
}
