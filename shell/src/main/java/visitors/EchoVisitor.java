package visitors;

import commands.Echo;
import grammar.ShellParser;

public class EchoVisitor extends CommandVisitor<Echo, ShellParser.EchoContext> {

    public EchoVisitor() {
        super(Echo.class);
    }

    @Override
    public void visit(ShellVisitorImpl visitor, ShellParser.EchoContext context) {
        executeCommand(visitor, context, context.literal(), false);
    }

}
