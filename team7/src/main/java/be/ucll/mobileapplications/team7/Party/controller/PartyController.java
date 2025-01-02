package be.ucll.mobileapplications.team7.Party.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ucll.mobileapplications.team7.Party.model.Party;
import be.ucll.mobileapplications.team7.Party.service.PartyService;
import jakarta.validation.Valid;

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

    @PostMapping()
    public Party addParty(@RequestBody @Valid Party party) {
        return partyService.addParty(party);
    }

}
