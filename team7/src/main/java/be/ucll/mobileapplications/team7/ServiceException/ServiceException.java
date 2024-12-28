package be.ucll.mobileapplications.team7.ServiceException;

public class ServiceException extends Exception {

    public String field;

    public ServiceException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return this.field;
    }
}
