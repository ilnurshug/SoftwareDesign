package visitors;

import commands.Wc;
import grammar.ShellParser;
import shell.Environment;

import java.util.Collections;
import java.util.List;

public class WcVisitor {

    private static final Wc cmd = new Wc();

    public static void visit(ShellVisitorImpl visitor, ShellParser.WcContext context) {
        visitor.visitChildren(context);
        VisitorHelper.executeCommand(cmd, context.literal(), visitor.isPipeCmd());
    }

}
