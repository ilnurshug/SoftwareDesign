package visitors;

import commands.Wc;
import grammar.ShellParser;

class WcVisitor extends CommandVisitor<Wc, ShellParser.WcContext> {

    public WcVisitor() {
        super(Wc.class);
    }

    @Override
    public void visit(ShellVisitorImpl visitor, ShellParser.WcContext context) {
        executeCommand(visitor, context, context.literal(), visitor.isPipeCmd());
    }

}
