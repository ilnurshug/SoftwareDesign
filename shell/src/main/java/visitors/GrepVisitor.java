package visitors;

import commands.Grep;
import grammar.ShellParser;

public class GrepVisitor extends CommandVisitor<Grep, ShellParser.GrepContext> {
    GrepVisitor() {
        super(Grep.class);
    }

    @Override
    public void visit(ShellVisitorImpl visitor, ShellParser.GrepContext context) {
        executeCommand(visitor, context, context.literal(), visitor.isPipeCmd());
    }
}
