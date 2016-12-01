package visitors;

import commands.Proc;
import grammar.ShellParser;

/**
 * Proc parse tree node visitor
 */
class ProcVisitor  extends CommandVisitor<ShellParser.ProcContext> {

    public ProcVisitor() {
        super(new Proc());
    }

    @Override
    public void visit(ShellVisitorImpl visitor, ShellParser.ProcContext context) {
        executeCommand(visitor, context, context.literal(), visitor.isPipeCmd());
    }

}
