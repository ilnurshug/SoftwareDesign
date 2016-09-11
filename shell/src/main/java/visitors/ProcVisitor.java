package visitors;

import commands.Proc;
import grammar.ShellParser;

public class ProcVisitor {

    private static final Proc cmd = new Proc();

    public static void visit(ShellVisitorImpl visitor, ShellParser.ProcContext context) {
        visitor.visitChildren(context);
        VisitorHelper.executeCommand(cmd, context.literal(), visitor.isPipeCmd());
    }

}
