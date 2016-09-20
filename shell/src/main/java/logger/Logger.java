package logger;


/**
 * custom simple logger
 */
public class Logger {

    /**
     * writes messages to error stream
     * @param msg log message
     */
    public static void log(String msg) {
        System.err.println(msg);
    }
}
