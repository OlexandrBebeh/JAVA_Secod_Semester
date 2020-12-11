package project.kpi.controller.validator.exeption;

public class NotCurrentCardNumber extends RuntimeException {
    NotCurrentCardNumber() {
        super("This is not card number.");
    }

    public NotCurrentCardNumber(String message) {
        super(message);
    }

}
