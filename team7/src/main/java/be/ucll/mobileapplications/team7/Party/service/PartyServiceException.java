package be.ucll.mobileapplications.team7.Party.service;

import be.ucll.mobileapplications.team7.ServiceException.ServiceException;

public class PartyServiceException extends ServiceException {

  public PartyServiceException(String field, String message) {
    super(field, message);
  }
}
