package commands;

import java.util.List;

/*
    echo command can not take as argument the result of previous command
 */
public class Echo extends Command {
    public Echo() {
        super(-1);
    }

    @Override
    protected String execImpl(List<String> args, boolean inPipe) {
        return String.join(" ", args);
    }
}
