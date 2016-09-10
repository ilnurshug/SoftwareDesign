package commands;

import shell.Environment;

import java.util.List;

public class Assignment extends Command {
    public Assignment() {
        super(2);
    }

    @Override
    protected String execImpl(List<String> args) {
        Environment.INSTANCE.set(args.get(0), args.get(1));
        return "";
    }
}
