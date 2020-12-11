package kpi.view.Exeption;

public class NotCurrentCardNumber extends RuntimeException {
    NotCurrentCardNumber() {
        super("This is not card number.");
    }

    public NotCurrentCardNumber(String message) {
        super(message);
    }

}
