package commands;

import java.util.List;

public class Exit extends Command {
    public Exit() {
        super(0);
    }

    @Override
    public String execImpl(List<String> args) {
        System.exit(0);
        return "";
    }
}
