package visitors;

import grammar.ShellBaseVisitor;
import grammar.ShellParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * custom visitor
 * @see AbstractParseTreeVisitor
 */
public class ShellVisitorImpl extends ShellBaseVisitor {
    private boolean isPipeCmd = false;

    boolean isPipeCmd() {
        return isPipeCmd;
    }

    @Override
    public Object visitEcho(ShellParser.EchoContext ctx) {
        visit(EchoVisitor.class, ctx);
        return null;
    }

    @Override
    public Object visitAssignment(ShellParser.AssignmentContext ctx) {
        visit(AssignmentVisitor.class, ctx);
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
        visit(CatVisitor.class, ctx);
        return null;
    }

    @Override
    public Object visitPwd(ShellParser.PwdContext ctx) {
        visit(PwdVisitor.class, ctx);
        return null;
    }

    @Override
    public Object visitExit(ShellParser.ExitContext ctx) {
        visit(ExitVisitor.class, ctx);
        return null;
    }

    @Override
    public Object visitWc(ShellParser.WcContext ctx) {
        visit(WcVisitor.class, ctx);
        return null;
    }

    @Override
    public Object visitProc(ShellParser.ProcContext ctx) {
        visit(ProcVisitor.class, ctx);
        return null;
    }

    private <Visitor extends CommandVisitor, Context extends ParserRuleContext> void visit(Class<Visitor> cls, Context ctx) {
        try {
            Visitor visitor = cls.newInstance();
            visitor.visit(this, ctx);
        }
        catch (IllegalAccessException | InstantiationException e) {
            logger.Logger.log(e.getMessage());
        }
    }
}
