package classes.kpi.controller.validator.exeption;

public class NotEmailException extends RuntimeException{
    NotEmailException() {
        super("This is not email.");
    }

    public NotEmailException(String message) {
        super(message);
    }
}