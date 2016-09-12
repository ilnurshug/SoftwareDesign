package visitors;

import commands.Echo;
import grammar.ShellParser;

public class EchoVisitor extends CommandVisitor<Echo, ShellParser.EchoContext> {

    public EchoVisitor() {
        super(Echo.class);
    }

    @Override
    public void visit(ShellVisitorImpl visitor, ShellParser.EchoContext context) {
        visitor.visitChildren(context);
        CommandVisitor.executeCommand(cmd, context.literal(), false);
    }

}
