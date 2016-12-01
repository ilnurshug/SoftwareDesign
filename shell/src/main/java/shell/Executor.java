package shell;

import logger.Logger;

public abstract class Executor {

    protected final Environment environment;
    protected final Logger logger;

    /**
     * create executor with specific execution context - (environment, logger)
     */
    public Executor(Environment environment, Logger logger) {
        this.environment = environment;
        this.logger = logger;
    }

    /**
     * execute string
     * @param str line to be executed
     * @return result of execution
     */
    public abstract String execute(String str);
}
