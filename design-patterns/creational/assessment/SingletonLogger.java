package creational.assessment;

/**
 * Question:
 * Design a thread-safe Logger class that ensures only one instance exists.
 *
 * Logger.getInstance() returns the instance.
 *
 * Each log message should be prefixed with a timestamp.
 *
 * Hint:
 * Use Bill Pugh’s static inner class for thread safety and lazy initialization.
 */
public class SingletonLogger {
    private SingletonLogger() {}

    public static class SingletonLoggerHolder {
        private static final SingletonLogger instance = new SingletonLogger();

    }

    public static SingletonLogger getInstance() {
        return SingletonLoggerHolder.instance;
    }

    public void log(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        SingletonLogger log = SingletonLogger.getInstance();
        log.log("Hello World");
    }
}
