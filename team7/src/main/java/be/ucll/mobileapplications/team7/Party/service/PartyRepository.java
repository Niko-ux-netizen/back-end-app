package be.ucll.mobileapplications.team7.Party.service;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ucll.mobileapplications.team7.Party.model.Party;
import be.ucll.mobileapplications.team7.User.model.User;

public interface PartyRepository extends JpaRepository<Party, Integer> {

    public Party findById(int id);

}
