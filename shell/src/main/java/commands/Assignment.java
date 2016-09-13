package commands;

import shell.Environment;

import java.util.List;
/*
    Set variable value in environment
 */
public class Assignment extends Command {
    public Assignment() {
        super(2);
    }

    @Override
    protected String execImpl(List<String> args, boolean inPipe) {
        Environment.INSTANCE.set(args.get(0), args.get(1));
        return null;
    }
}
