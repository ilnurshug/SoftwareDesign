package shell;

import adaptor.ANTLRParserAdaptor;
import org.antlr.v4.runtime.tree.ParseTree;
import visitors.ShellVisitorImpl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Executor {

    public static String execute(String str) {
        InputStream in = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
        ParseTree parseTree = ANTLRParserAdaptor.parse(in);
        if (parseTree == null) return "";

        ShellVisitorImpl visitor = new ShellVisitorImpl();
        visitor.visit(parseTree);

        return Environment.INSTANCE.getPrevCmdResult();
    }

}
