package visitors;

import commands.Pwd;
import grammar.ShellParser;

class PwdVisitor extends CommandVisitor<Pwd, ShellParser.PwdContext> {

    public PwdVisitor() {
        super(Pwd.class);
    }

    @Override
    public void visit(ShellVisitorImpl visitor, ShellParser.PwdContext context) {
        executeCommand(visitor, context, null, false);
    }

}
