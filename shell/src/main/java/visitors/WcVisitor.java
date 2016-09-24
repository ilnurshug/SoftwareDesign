package visitors;

import commands.Wc;
import grammar.ShellParser;

/**
 * Wc parse tree node visitor
 */
class WcVisitor extends CommandVisitor<Wc, ShellParser.WcContext> {

    public WcVisitor() {
        super(Wc.class);
    }

    @Override
    public void visit(ShellVisitorImpl visitor, ShellParser.WcContext context) {
        executeCommand(visitor, context, context.literal(), visitor.isPipeCmd());
    }
}