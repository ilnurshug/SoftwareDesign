package listeners;


import grammar.ShellListener;
import grammar.ShellParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class ShellListenerImpl implements ShellListener {
    public void enterCommand(ShellParser.CommandContext ctx) {

    }

    public void exitCommand(ShellParser.CommandContext ctx) {

    }

    public void enterCmd(ShellParser.CmdContext ctx) {

    }

    public void exitCmd(ShellParser.CmdContext ctx) {

    }

    public void enterSimpleCmd(ShellParser.SimpleCmdContext ctx) {

    }

    public void exitSimpleCmd(ShellParser.SimpleCmdContext ctx) {

    }

    public void enterPipeCmd(ShellParser.PipeCmdContext ctx) {

    }

    public void exitPipeCmd(ShellParser.PipeCmdContext ctx) {

    }

    public void enterAssignment(ShellParser.AssignmentContext ctx) {

    }

    public void exitAssignment(ShellParser.AssignmentContext ctx) {

    }

    public void enterCat(ShellParser.CatContext ctx) {
        CatListener.INSTANCE.enterRule(ctx);
    }

    public void exitCat(ShellParser.CatContext ctx) {
        CatListener.INSTANCE.exitRule(ctx);
    }

    public void enterEcho(ShellParser.EchoContext ctx) {

    }

    public void exitEcho(ShellParser.EchoContext ctx) {

    }

    public void enterWc(ShellParser.WcContext ctx) {

    }

    public void exitWc(ShellParser.WcContext ctx) {

    }

    public void enterPwd(ShellParser.PwdContext ctx) {

    }

    public void exitPwd(ShellParser.PwdContext ctx) {

    }

    public void enterExit(ShellParser.ExitContext ctx) {

    }

    public void exitExit(ShellParser.ExitContext ctx) {

    }

    public void enterId(ShellParser.IdContext ctx) {

    }

    public void exitId(ShellParser.IdContext ctx) {

    }

    public void enterVarid(ShellParser.VaridContext ctx) {

    }

    public void exitVarid(ShellParser.VaridContext ctx) {

    }

    public void enterLiteral(ShellParser.LiteralContext ctx) {

    }

    public void exitLiteral(ShellParser.LiteralContext ctx) {

    }

    public void enterFullQuoting(ShellParser.FullQuotingContext ctx) {

    }

    public void exitFullQuoting(ShellParser.FullQuotingContext ctx) {

    }

    public void enterWeakQuoting(ShellParser.WeakQuotingContext ctx) {

    }

    public void exitWeakQuoting(ShellParser.WeakQuotingContext ctx) {

    }

    public void visitTerminal(TerminalNode terminalNode) {

    }

    public void visitErrorNode(ErrorNode errorNode) {

    }

    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}
