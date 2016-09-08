package visitors;

import grammar.ShellParser;

public class IdVisitor {

    public static String getValue(ShellParser.IdContext context) {
        return VisitorHelper.getFirstTerminalNode(context).getText();
    }

}
