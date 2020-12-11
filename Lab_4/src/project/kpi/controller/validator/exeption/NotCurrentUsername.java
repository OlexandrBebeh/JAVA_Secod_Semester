package project.kpi.controller.validator.exeption;


public class NotCurrentUsername extends RuntimeException {
    NotCurrentUsername() {
        super("Not current username. There are is enable symbols.");
    }

    public NotCurrentUsername(String message) {
        super(message);
    }

}
