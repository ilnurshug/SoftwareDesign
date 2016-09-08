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
    public String execImpl(List<String> args) {
        return String.join(" ", args);
    }
}
