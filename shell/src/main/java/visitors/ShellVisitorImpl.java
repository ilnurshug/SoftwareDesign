package visitors;

import grammar.ShellBaseVisitor;
import grammar.ShellParser;
import logger.Logger;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import shell.Environment;

enum Visitors {ASSIGN, CAT, ECHO, EXIT, GREP, PROC, PWD, WC}

abstract class VisitorCreator {
    public abstract CommandVisitor create(Visitors type);
}

class MyVisitorCreator extends VisitorCreator {
    @Override
    public CommandVisitor create(Visitors type) {
        switch (type)
        {
            case WC:
                return new WcVisitor();
            case PWD:
                return new PwdVisitor();
            case PROC:
                return new ProcVisitor();
            case GREP:
                return new GrepVisitor();
            case ASSIGN:
                return new AssignmentVisitor();
            case CAT:
                return new CatVisitor();
            case ECHO:
                return new EchoVisitor();
            case EXIT:
                return new ExitVisitor();
            default:
                return null;
        }
    }
}

/**
 * custom visitor
 * @see AbstractParseTreeVisitor
 */
public class ShellVisitorImpl extends ShellBaseVisitor {
    private Environment environment;
    private Logger logger;
    private final VisitorCreator creator;

    private boolean isPipeCmd = false;

    boolean isPipeCmd() {
        return isPipeCmd;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public ShellVisitorImpl() {
        creator = new MyVisitorCreator();
    }

    @Override
    public Object visitEcho(ShellParser.EchoContext ctx) {
        visit(Visitors.ECHO, ctx);
        return null;
    }

    @Override
    public Object visitAssignment(ShellParser.AssignmentContext ctx) {
        visit(Visitors.ASSIGN, ctx);
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
        visit(Visitors.CAT, ctx);
        return null;
    }

    @Override
    public Object visitPwd(ShellParser.PwdContext ctx) {
        visit(Visitors.PWD, ctx);
        return null;
    }

    @Override
    public Object visitExit(ShellParser.ExitContext ctx) {
        visit(Visitors.EXIT, ctx);
        return null;
    }

    @Override
    public Object visitWc(ShellParser.WcContext ctx) {
        visit(Visitors.WC, ctx);
        return null;
    }

    @Override
    public Object visitProc(ShellParser.ProcContext ctx) {
        visit(Visitors.PROC, ctx);
        return null;
    }

    @Override
    public Object visitGrep(ShellParser.GrepContext ctx) {
        visit(Visitors.GREP, ctx);
        return null;
    }

    private <Context extends ParserRuleContext> void visit(Visitors type, Context ctx) {
        CommandVisitor<Context> visitor = creator.create(type);
        visitor.setEnvironment(environment);
        visitor.setLogger(logger);
        visitor.visit(this, ctx);
    }
}
