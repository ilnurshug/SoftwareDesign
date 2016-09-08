import adaptor.ANTLRParserAdaptor;
import listeners.ShellListenerImpl;
import grammar.ShellListener;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.InputStream;

public class Executor {

    public static void execute(InputStream in) {
        ParseTree parseTree = ANTLRParserAdaptor.parse(in);
        if (parseTree == null) return;

        ParseTreeWalker walker = new ParseTreeWalker();
        ShellListener listener = new ShellListenerImpl();

        walker.walk(listener, parseTree);
    }

}
