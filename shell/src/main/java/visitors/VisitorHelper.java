package visitors;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

public abstract class VisitorHelper {

    public static TerminalNode getFirstTerminalNode(ParseTree node) {
        if (node == null || node instanceof TerminalNode) return (TerminalNode) node;

        return getFirstTerminalNode(node.getChild(0));
    }
}
