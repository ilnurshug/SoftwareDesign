package shell;

import adaptor.ANTLRParserAdaptor;
import logger.Logger;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.SyntaxTree;
import visitors.ShellVisitorImpl;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * REPL
 */
public class Shell {

    private static final Environment environment = new Environment();
    private static final Logger logger = new Logger();

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String line;
            while ((line = br.readLine()) != null) {
                String res = execute(line);
                if (res != null) {
                    System.out.println(res);
                }
            }
        }
        catch (IOException e) {
            logger.log(e.getMessage());
        }
    }

    /**
     * @param str line to be executed
     * @return result of execution
     */
    public static String execute(String str) {
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
