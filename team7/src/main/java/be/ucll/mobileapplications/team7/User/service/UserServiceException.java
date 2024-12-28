package be.ucll.mobileapplications.team7.User.service;

import be.ucll.mobileapplications.team7.ServiceException.ServiceException;

public class UserServiceException  extends ServiceException {

    public UserServiceException(String field, String message) {
      super(field, message);
  }
}
