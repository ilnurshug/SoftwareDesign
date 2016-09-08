package commands;

import shell.Environment;

import java.util.List;
import java.util.logging.Level;

public class Assignment extends Command {
    public Assignment() {
        super(2);
    }

    @Override
    public String execImpl(List<String> args) {
        Environment.INSTANCE.set(args.get(0), args.get(1));
        return "";
    }
}
