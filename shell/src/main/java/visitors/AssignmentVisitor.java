package visitors;

import commands.Assignment;
import grammar.ShellParser;

import java.util.Arrays;

/**
 * Assignment parse tree node visitor
 */
class AssignmentVisitor extends CommandVisitor<Assignment, ShellParser.AssignmentContext> {

    public AssignmentVisitor() {
        super(Assignment.class);
    }

    @Override
    public void visit(ShellVisitorImpl visitor, ShellParser.AssignmentContext context) {
        visitor.visitChildren(context);

        cmd.exec(
                Arrays.asList(
                        getValue(context.id()),
                        getValue(context.literal())
                )
                , false);
    }

}
