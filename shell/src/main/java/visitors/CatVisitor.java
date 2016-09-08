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

        List<String> args;

        if (context.literal().size() == 0 && visitor.isPipeCmd()){
            args = Collections.singletonList(Environment.INSTANCE.getPrevCmdResult());
        }
        else {
            args = context.literal()
                    .stream()
                    .map(LiteralVisitor::getValue)
                    .collect(Collectors.toList());
        }

        cmd.exec(args);
    }

}
