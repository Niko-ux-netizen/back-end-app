package be.ucll.mobileapplications.team7.Review.service;

import be.ucll.mobileapplications.team7.ServiceException.ServiceException;

public class ReviewServiceException extends ServiceException {

    public ReviewServiceException(String field, String message) {
      super(field, message);
  }
  
}
