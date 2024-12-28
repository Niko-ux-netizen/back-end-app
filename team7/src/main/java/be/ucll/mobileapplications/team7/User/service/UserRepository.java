package be.ucll.mobileapplications.team7.User.service;

import org.springframework.data.jpa.repository.JpaRepository;
import be.ucll.mobileapplications.team7.User.model.User;

public interface UserRepository extends JpaRepository<User, String> {

  public User findUserByEmail(String email);
}
