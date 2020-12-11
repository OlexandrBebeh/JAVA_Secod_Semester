package kpi.view.Exeption;

public class WrongRangeException extends RuntimeException{
    WrongRangeException() {
        super("This digit not in range.");
    }

    public WrongRangeException(String message) {
        super(message);
    }
}
