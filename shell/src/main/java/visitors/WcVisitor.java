package visitors;

import commands.Wc;
import grammar.ShellParser;

public class WcVisitor extends CommandVisitor<Wc, ShellParser.WcContext> {

    public WcVisitor() {
        super(Wc.class);
    }

    @Override
    public void visit(ShellVisitorImpl visitor, ShellParser.WcContext context) {
        visitor.visitChildren(context);
        CommandVisitor.executeCommand(cmd, context.literal(), visitor.isPipeCmd());
    }

}
