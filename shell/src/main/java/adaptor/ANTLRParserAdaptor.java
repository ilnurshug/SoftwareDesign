package adaptor;


import grammar.ShellLexer;
import grammar.ShellParser;
import logger.Logger;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

public class ANTLRParserAdaptor {

    public static ParseTree parse(InputStream in) {
        ANTLRInputStream input = null;
        try {
            input = new ANTLRInputStream(in);
        }
        catch (IOException e) {
            Logger.INSTANCE.log(Level.ALL, e.getMessage());
            return null;
        }

        BailShellLexer lexer = new BailShellLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        ShellParser parser = new ShellParser(tokens);
        parser.setErrorHandler(new BailErrorStrategy());
        parser.removeErrorListeners();
        parser.addErrorListener(new SyntaxErrorListener());

        ParseTree parseTree = null;
        try {
            parseTree = parser.command();
        }
        catch (RuntimeException re) {
            Logger.INSTANCE.log(Level.ALL, re.getMessage());
        }

        return parseTree;
    }

}
