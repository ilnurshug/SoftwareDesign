package visitors;

import commands.Pwd;
import grammar.ShellParser;

/**
 * Pwd parse tree node visitor
 */
class PwdVisitor extends CommandVisitor<ShellParser.PwdContext> {

    public PwdVisitor() {
        super(new Pwd());
    }

    @Override
    public void visit(ShellVisitorImpl visitor, ShellParser.PwdContext context) {
        executeCommand(visitor, context, null, false);
    }

}
