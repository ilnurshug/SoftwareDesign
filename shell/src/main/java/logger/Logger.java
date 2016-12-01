package logger;


/**
 * custom simple logger
 */
public class Logger {
    public static final Logger INSTANCE = new Logger();

    /**
     * writes messages to error stream
     * @param msg log message
     */
    public void log(String msg) {
        System.err.println(msg);
    }
}
