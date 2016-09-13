package commands;

import java.util.List;
/*
    Exit from REPL
 */
public class Exit extends Command {
    public Exit() {
        super(0);
    }

    @Override
    protected String execImpl(List<String> args, boolean inPipe) {
        System.exit(0);
        return null;
    }
}
