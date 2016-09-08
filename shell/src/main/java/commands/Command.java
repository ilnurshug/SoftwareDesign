package commands;

import java.util.List;

public abstract class Command {
    protected int argc;

    /*
        argc: number of arguments,
        -1 if command have arbitrary number of args
     */
    public Command(int argc) {
        this.argc = argc;
    }

    public abstract String exec(List<String> args);
}
