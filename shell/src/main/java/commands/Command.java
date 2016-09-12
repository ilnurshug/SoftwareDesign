package commands;

import shell.Environment;

import java.util.List;

public abstract class Command {
    private int argc;

    /*
        argc: number of arguments,
        -1 if command have arbitrary number of args
     */
    Command(int argc) {
        this.argc = argc;
    }

    public String exec(List<String> args) {
        String res;

        if (argc != -1 && args != null && argc != args.size()) {
            logger.Logger.log("wrong number of arguments");
            res = "";
        }
        else {
            res = execImpl(args);
        }

        Environment.INSTANCE.addCommandResult(res);

        return res;
    }

    protected abstract String execImpl(List<String> args);
}
