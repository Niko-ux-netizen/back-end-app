package be.ucll.mobileapplications.team7.Movie.service;

import be.ucll.mobileapplications.team7.ServiceException.ServiceException;

public class MovieServiceException  extends ServiceException {

    public MovieServiceException(String field, String message) {
      super(field, message);
  }
}
