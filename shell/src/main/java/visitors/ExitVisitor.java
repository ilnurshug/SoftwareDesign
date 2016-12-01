package visitors;

import commands.Exit;
import grammar.ShellParser;

/**
 * Exit parse tree node visitor
 */
class ExitVisitor extends CommandVisitor<ShellParser.ExitContext> {

    public ExitVisitor() {
        super(new Exit());
    }

    @Override
    public void visit(ShellVisitorImpl visitor, ShellParser.ExitContext context) {
        executeCommand(visitor, context, null, false);
    }

}
