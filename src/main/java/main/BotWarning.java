package main;

public class BotWarning extends RuntimeException {

    public BotWarning(Throwable cause, String userDescription) {
        super(cause);
        System.err.println("[Warn] " + userDescription);
    }
}
