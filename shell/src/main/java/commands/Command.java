package commands;

import logger.Logger;
import shell.Environment;

import java.util.List;

/**
 * abstract class for command execution
 */
public abstract class Command {
    private int argc;

    protected Environment environment;
    protected Logger logger;

    /**
     * abstract command constructor
     * @param argc number of arguments,
     *             -1 if command have arbitrary number of args
     */
    Command(int argc) {
        this.argc = argc;
    }

    /**
     * run execution of command
     * @param args list of command arguments
     * @param inPipe true if current command is being executed in pipe
     * @return result of command execution
     */
    public String exec(List<String> args, boolean inPipe) {
        String res;

        if (argc != -1 && argc != args.size()) {
            logger.log("wrong number of arguments");
            res = "";
        }
        else {
            res = execImpl(args, inPipe);
        }

        environment.addCommandResult(res);

        return res;
    }

    /**
     * set working environment
     * @param environment command execution environment
     */
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * set logger
     * @param logger command logger
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    protected abstract String execImpl(List<String> args, boolean inPipe);
}
