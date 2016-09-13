package visitors;

import commands.Command;
import grammar.ShellParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import shell.Environment;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

abstract class CommandVisitor<Cmd extends Command, Context extends ParserRuleContext> {

    Cmd cmd;

    CommandVisitor(Class<Cmd> cls) {
        try {
            cmd = cls.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e) {
            logger.Logger.log(e.getMessage());
        }
    }

    public void visit(ShellVisitorImpl visitor, Context context) {
        visitor.visitChildren(context);
    }

    void executeCommand(ShellVisitorImpl visitor, Context context, List<ShellParser.LiteralContext> literals, boolean isPipe) {
        visitor.visitChildren(context);

        List<String> args;

        if ((literals == null || literals.size() == 0) && isPipe) {
            args = Collections.singletonList(Environment.INSTANCE.getPrevCmdResult())
                                .stream()
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList());
        } else {
            args = CommandVisitor.getValue(literals);
        }

        cmd.exec(args, isPipe);
    }

    static String getValue(ShellParser.LiteralContext literal) {
        String str = CommandVisitor.getFirstTerminalNode(literal).getText();

        if (literal.weakQuoting() != null) {
            return getValue(literal.weakQuoting());
        }
        else if (literal.varid() != null) {
            return Environment.INSTANCE.get(str.substring(1));
        }
        else if (literal.fullQuoting() != null) {
            return str.substring(1, str.length() - 1);
        }
        else {
            return str;
        }
    }

    private static List<String> getValue(List<ShellParser.LiteralContext> literals) {
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
                res.append(Environment.INSTANCE.get(id));
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
