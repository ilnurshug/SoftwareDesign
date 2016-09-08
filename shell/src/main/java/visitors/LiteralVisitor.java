package visitors;

import grammar.ShellParser;
import shell.Environment;

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

}
