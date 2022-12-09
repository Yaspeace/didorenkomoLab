package helpers;

/**
 * Логер для консоли
 */
public class ConsoleLogger implements Logger {
    public void log(String message) {
        var time = java.time.LocalDateTime.now();
        System.out.println("INFO [" + time.toString() + "]: " + message);
    }

    public void logError(String errorMessage) {
        var time = java.time.LocalDateTime.now();
        System.err.println("ERROR [" + time.toString() + "]: " + errorMessage);
    }
}
