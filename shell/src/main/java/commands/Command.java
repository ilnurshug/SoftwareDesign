package commands;

import shell.Environment;

import java.util.List;
import java.util.logging.Level;

public abstract class Command {
    protected int argc;

    /*
        argc: number of arguments,
        -1 if command have arbitrary number of args
     */
    public Command(int argc) {
        this.argc = argc;
    }

    public String exec(List<String> args) {
        String res;

        if (argc != -1 && argc != args.size()) {
            logger.Logger.INSTANCE.log(Level.ALL, "wrong number of arguments");
            res = "";
        }
        else {
            res = execImpl(args);
        }

        Environment.INSTANCE.addCommandResult(res);

        return res;
    }

    public abstract String execImpl(List<String> args);
}
