package visitors;

import commands.Cat;
import grammar.ShellParser;

/**
 * Cat parse tree node visitor
 */
class CatVisitor extends CommandVisitor<ShellParser.CatContext> {

    public CatVisitor() {
        super(new Cat());
    }

    @Override
    public void visit(ShellVisitorImpl visitor, ShellParser.CatContext context) {
        executeCommand(visitor, context, context.literal(), visitor.isPipeCmd());
    }
}
