package visitors;

import commands.Exit;
import grammar.ShellParser;

public class ExitVisitor extends CommandVisitor<Exit, ShellParser.ExitContext> {

    public ExitVisitor() {
        super(Exit.class);
    }

    @Override
    public void visit(ShellVisitorImpl visitor, ShellParser.ExitContext context) {
        visitor.visitChildren(context);
        CommandVisitor.executeCommand(cmd, null, false);
    }

}
