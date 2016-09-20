package commands;

import shell.Environment;

import java.util.List;

/**
 * Print current directory
 */
public class Pwd extends Command {
    public Pwd() {
        super(0);
    }

    @Override
    protected String execImpl(List<String> args, boolean inPipe) {
        return Environment.INSTANCE.getCurrentDirectory();
    }
}
