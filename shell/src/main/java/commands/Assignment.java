package commands;

import shell.Environment;

import java.util.List;

/**
 * Set variable value in environment
 */
public class Assignment extends Command {
    public Assignment() {
        super(2);
    }

    /**
     * @param args args[0] - variable's name, args[1] - variable's value
     * @param inPipe true if current command is being executed in pipe
     */
    @Override
    protected String execImpl(List<String> args, boolean inPipe) {
        Environment.INSTANCE.set(args.get(0), args.get(1));
        return null;
    }
}
