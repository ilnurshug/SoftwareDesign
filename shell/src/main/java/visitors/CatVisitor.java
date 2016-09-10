package visitors;

import commands.Cat;
import grammar.ShellParser;
import shell.Environment;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CatVisitor {

    private static final Cat cmd = new Cat();

    public static void visit(ShellVisitorImpl visitor, ShellParser.CatContext context) {
        visitor.visitChildren(context);
        VisitorHelper.executeCommand(cmd, context.literal(), visitor.isPipeCmd());
    }
}
