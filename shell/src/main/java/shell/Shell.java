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

    private static final Executor executor = new MyExecutor(Environment.INSTANCE, Logger.INSTANCE);

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String line;
            while ((line = br.readLine()) != null) {
                String res = executor.execute(line);
                if (res != null) {
                    System.out.println(res);
                }
            }
        }
        catch (IOException e) {
            Logger.INSTANCE.log(e.getMessage());
        }
    }
}
