package visitors;

import grammar.ShellBaseVisitor;
import grammar.ShellParser;

public class ShellVisitorImpl extends ShellBaseVisitor {
    private boolean isPipeCmd = false;

    public boolean isPipeCmd() {
        return isPipeCmd;
    }

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

    @Override
    public Object visitPipeCmd(ShellParser.PipeCmdContext ctx) {
        isPipeCmd = true;

        super.visitPipeCmd(ctx);

        isPipeCmd = false;
        return null;
    }
}
