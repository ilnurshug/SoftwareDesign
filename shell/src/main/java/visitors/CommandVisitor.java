package visitors;

import commands.Command;
import grammar.ShellParser;
import logger.Logger;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import shell.Environment;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * abstract class for visiting nodes of parse tree and executing commands
 * @param <Context> type of parse tree node
 */
abstract class CommandVisitor<Context extends ParserRuleContext> {

    protected static Environment environment;
    protected static Logger logger;

    Command cmd;

    CommandVisitor(Command cmd) {
        this.cmd = cmd;
    }

    /**
     * @param environment command execution environment
     */
    void setEnvironment(Environment environment) {
        CommandVisitor.environment = environment;
        cmd.setEnvironment(environment);
    }

    void setLogger(Logger logger) {
        CommandVisitor.logger = logger;
        cmd.setLogger(logger);
    }

    /**
     * recursively traverse child nodes
     */
    public void visit(ShellVisitorImpl visitor, Context context) {
        visitor.visitChildren(context);
    }

    /**
     * recursively traverse child nodes and then execute command
     * (Post-order traverse)
     *
     * @param visitor visitor object
     * @param context current node of parse tree
     * @param literals children's list of context with type Literal
     * @param isPipe true if current command is being executed in pipe
     * @see ShellVisitorImpl
     */
    void executeCommand(ShellVisitorImpl visitor, Context context, List<ShellParser.LiteralContext> literals, boolean isPipe) {
        visitor.visitChildren(context);

        List<String> args;

        if ((literals == null || literals.size() == 0) && isPipe) {
            args = Collections.singletonList(environment.getPrevCmdResult())
                                .stream()
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList());
        } else {
            args = CommandVisitor.getValue(literals);
        }

        cmd.exec(args, isPipe);
    }

    /**
     *
     * @param literal parse tree node
     * @return literal content
     *          performs content transformations if needed
     */
    static String getValue(ShellParser.LiteralContext literal) {
        String str = CommandVisitor.getFirstTerminalNode(literal).getText();

        if (literal.weakQuoting() != null) {
            return getValue(literal.weakQuoting());
        }
        else if (literal.varid() != null) {
            return environment.get(str.substring(1));
        }
        else if (literal.fullQuoting() != null) {
            return str.substring(1, str.length() - 1);
        }
        else {
            return str;
        }
    }

    static List<String> getValue(List<ShellParser.LiteralContext> literals) {
        if (literals == null) {
            return new LinkedList<>();
        }
        else {
            return literals
                    .stream()
                    .map(CommandVisitor::getValue)
                    .collect(Collectors.toList());
        }
    }

    static String getValue(ShellParser.IdContext id) {
        return CommandVisitor.getFirstTerminalNode(id).getText();
    }

    private static String getValue(ShellParser.WeakQuotingContext context) {
        String str = context.getText();

        StringBuilder res = new StringBuilder();

        boolean f = false;
        String id = "";
        for (int i = 1; i < str.length() - 1; i++) {
            char c = str.charAt(i);

            if (c == '$' && !f) {
                f = true;
            }
            else if (!Character.isAlphabetic(c) && f) {
                res.append(environment.get(id));
                res.append(c);
                id = "";
                f = false;
            }
            else if (f) {
                id += c;
            }
            else {
                res.append(c);
            }
        }

        return res.toString();
    }

    private static TerminalNode getFirstTerminalNode(ParseTree node) {
        if (node == null || node instanceof TerminalNode) return (TerminalNode) node;

        return getFirstTerminalNode(node.getChild(0));
    }
}
