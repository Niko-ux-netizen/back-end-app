package be.ucll.mobileapplications.team7.Party.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ucll.mobileapplications.team7.Party.model.Party;
import be.ucll.mobileapplications.team7.User.model.User;

@Service
public class PartyService {

    @Autowired
    private PartyRepository partyRepository;

    public List<Party> getAllParties() {
        return partyRepository.findAll();
    }

    public Party addParty(Party party) {
        partyRepository.save(party);
        return party;
    }

}
