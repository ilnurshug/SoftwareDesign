package visitors;

import commands.Proc;
import grammar.ShellParser;

public class ProcVisitor  extends CommandVisitor<Proc, ShellParser.ProcContext> {

    public ProcVisitor() {
        super(Proc.class);
    }

    @Override
    public void visit(ShellVisitorImpl visitor, ShellParser.ProcContext context) {
        executeCommand(visitor, context, context.literal(), visitor.isPipeCmd());
    }

}
