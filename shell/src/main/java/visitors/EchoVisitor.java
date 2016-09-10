package visitors;

import commands.Echo;
import grammar.ShellParser;
import shell.Environment;

import java.util.List;
import java.util.stream.Collectors;

public class EchoVisitor {

    private static final Echo cmd = new Echo();

    public static void visit(ShellVisitorImpl visitor, ShellParser.EchoContext context) {
        visitor.visitChildren(context);
        VisitorHelper.executeCommand(cmd, context.literal(), false);
    }

}
