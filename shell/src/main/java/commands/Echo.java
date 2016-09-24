package commands;

import java.util.List;

/**
 *   Print args
 *
 *   echo command can not take as argument the result of previous command
 */
public class Echo extends Command {
    public Echo() {
        super(-1);
    }

    /**
     * execute echo command
     * @param args list of strings
     * @param inPipe true if current command is being executed in pipe
     * @return content of all strings joined by whitespace
     */
    @Override
    protected String execImpl(List<String> args, boolean inPipe) {
        return String.join(" ", args);
    }
}
