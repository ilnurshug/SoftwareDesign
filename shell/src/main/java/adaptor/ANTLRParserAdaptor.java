package adaptor;

import grammar.ShellParser;
import logger.Logger;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.io.InputStream;

public class ANTLRParserAdaptor {

    /**
     * @param in input (string)
     * @return parse tree of input string or null if something went wrong
     */
    public static ParseTree parse(InputStream in) {
        ANTLRInputStream input;
        try {
            input = new ANTLRInputStream(in);
        }
        catch (IOException e) {
            Logger.log(e.getMessage());
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
            Logger.log(re.getMessage());
        }

        return parseTree;
    }

}
