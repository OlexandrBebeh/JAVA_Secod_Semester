package classes.kpi.controller.validator.exeption;

public class NotDigitException extends RuntimeException {
    NotDigitException() {
        super("This is not a digit.");
    }

    public NotDigitException(String message) {
        super(message);
    }

}