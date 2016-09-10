package visitors;

import commands.Command;
import grammar.ShellParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import shell.Environment;

import java.util.Collections;
import java.util.List;

public abstract class VisitorHelper {

    public static TerminalNode getFirstTerminalNode(ParseTree node) {
        if (node == null || node instanceof TerminalNode) return (TerminalNode) node;

        return getFirstTerminalNode(node.getChild(0));
    }

    public static void executeCommand(Command cmd, List<ShellParser.LiteralContext> literals, boolean isPipe) {
        List<String> args;

        if ((literals == null || literals.size() == 0) && isPipe) {
            args = Collections.singletonList(Environment.INSTANCE.getPrevCmdResult());
        } else {
            args = LiteralVisitor.transform(literals);
        }

        cmd.exec(args);
    }
}
