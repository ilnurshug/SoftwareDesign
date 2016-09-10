package visitors;

import commands.Pwd;
import grammar.ShellParser;

public class PwdVisitor {

    private static final Pwd cmd = new Pwd();

    public static void visit(ShellVisitorImpl visitor, ShellParser.PwdContext context) {
        visitor.visitChildren(context);
        VisitorHelper.executeCommand(cmd, null, false);
    }

}
