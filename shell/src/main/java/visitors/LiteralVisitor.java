package visitors;

import grammar.ShellParser;
import shell.Environment;

import java.util.List;
import java.util.stream.Collectors;

public class LiteralVisitor  {

    public static String getValue(ShellParser.LiteralContext context) {
        String str = VisitorHelper.getFirstTerminalNode(context).getText();

        if (context.weakQuoting() != null) {
            return WeakQuotingVisitor.transform(str);
        }
        else if (context.varid() != null) {
            return Environment.INSTANCE.get(str.substring(1));
        }
        else if (context.fullQuoting() != null) {
            return str.substring(1, str.length() - 1);
        }
        else {
            return str;
        }
    }

    public static List<String> transform(List<ShellParser.LiteralContext> literals) {
        if (literals == null) {
            return null;
        }
        else {
            return literals
                    .stream()
                    .map(LiteralVisitor::getValue)
                    .collect(Collectors.toList());
        }
    }
}
