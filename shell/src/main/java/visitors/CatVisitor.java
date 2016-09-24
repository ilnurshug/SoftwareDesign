package visitors;

import commands.Cat;
import grammar.ShellParser;

/**
 * Cat parse tree node visitor
 */
class CatVisitor extends CommandVisitor<Cat, ShellParser.CatContext> {

    public CatVisitor() {
        super(Cat.class);
    }

    @Override
    public void visit(ShellVisitorImpl visitor, ShellParser.CatContext context) {
        executeCommand(visitor, context, context.literal(), visitor.isPipeCmd());
    }
}
