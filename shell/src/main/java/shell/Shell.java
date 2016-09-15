package shell;

import adaptor.ANTLRParserAdaptor;
import logger.Logger;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.SyntaxTree;
import visitors.ShellVisitorImpl;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Shell {

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
            Logger.log(e.getMessage());
        }
    }

    public static String execute(String str) {
        InputStream in = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
        ParseTree parseTree = ANTLRParserAdaptor.parse(in);
        if (parseTree == null) return "";

        ShellVisitorImpl visitor = new ShellVisitorImpl();
        visitor.visit(parseTree);

        return Environment.INSTANCE.getPrevCmdResult();
    }
}
