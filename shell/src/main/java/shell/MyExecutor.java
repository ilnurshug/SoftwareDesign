package shell;

import adaptor.ANTLRParserAdaptor;
import logger.Logger;
import org.antlr.v4.runtime.tree.ParseTree;
import visitors.ShellVisitorImpl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MyExecutor extends Executor {

    public MyExecutor(Environment environment, Logger logger) {
        super(environment, logger);
    }

    @Override
    public String execute(String str) {
        InputStream in = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
        ParseTree parseTree = ANTLRParserAdaptor.parse(in);
        if (parseTree == null) return "";

        ShellVisitorImpl visitor = new ShellVisitorImpl();
        visitor.setEnvironment(environment);
        visitor.setLogger(logger);

        visitor.visit(parseTree);

        return environment.getPrevCmdResult();
    }
}
