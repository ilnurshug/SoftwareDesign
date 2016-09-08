package visitors;

import grammar.ShellBaseVisitor;
import grammar.ShellParser;

public class ShellVisitorImpl extends ShellBaseVisitor {
    @Override
    public Object visitEcho(ShellParser.EchoContext ctx) {
        EchoVisitor.visit(this, ctx);
        return null;
    }

    @Override
    public Object visitAssignment(ShellParser.AssignmentContext ctx) {
        AssignmentVisitor.visit(this, ctx);
        return null;
    }
}
