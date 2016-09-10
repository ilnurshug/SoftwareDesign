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

    @Override
    public Object visitCat(ShellParser.CatContext ctx) {
        CatVisitor.visit(this, ctx);
        return null;
    }

    @Override
    public Object visitPwd(ShellParser.PwdContext ctx) {
        PwdVisitor.visit(this, ctx);
        return null;
    }

    @Override
    public Object visitExit(ShellParser.ExitContext ctx) {
        ExitVisitor.visit(this, ctx);
        return null;
    }

    @Override
    public Object visitWc(ShellParser.WcContext ctx) {
        WcVisitor.visit(this, ctx);
        return null;
    }
}
