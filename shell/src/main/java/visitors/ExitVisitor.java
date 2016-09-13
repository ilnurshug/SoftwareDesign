package visitors;

import commands.Exit;
import grammar.ShellParser;

public class ExitVisitor extends CommandVisitor<Exit, ShellParser.ExitContext> {

    public ExitVisitor() {
        super(Exit.class);
    }

    @Override
    public void visit(ShellVisitorImpl visitor, ShellParser.ExitContext context) {
        executeCommand(visitor, context, null, false);
    }

}
