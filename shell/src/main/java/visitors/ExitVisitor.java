package visitors;

import commands.Exit;
import grammar.ShellParser;

public class ExitVisitor {

    private static final Exit cmd = new Exit();

    public static void visit(ShellVisitorImpl visitor, ShellParser.ExitContext context) {
        visitor.visitChildren(context);

        cmd.exec(null);
    }

}
