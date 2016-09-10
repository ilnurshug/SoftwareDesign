package commands;

import shell.Environment;

import java.util.List;

public class Pwd extends Command {
    public Pwd() {
        super(0);
    }

    @Override
    public String execImpl(List<String> args) {
        return Environment.INSTANCE.getCurrentDirectory();
    }
}
