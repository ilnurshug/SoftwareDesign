package visitors;

import commands.Assignment;
import grammar.ShellParser;

import java.util.Arrays;

public class AssignmentVisitor {

    private static final Assignment cmd = new Assignment();

    public static void visit(ShellVisitorImpl visitor, ShellParser.AssignmentContext context) {
        visitor.visitChildren(context);

        cmd.exec(
                Arrays.asList(
                        IdVisitor.getValue(context.id()),
                        LiteralVisitor.getValue(context.literal())
                )
        );
    }

}
