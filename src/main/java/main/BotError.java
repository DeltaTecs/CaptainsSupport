package main;

public class BotError extends RuntimeException {

    public BotError(Throwable cause, String userDescription) {
        super(cause);
        System.err.println("[ERR] " + userDescription);
    }
}
