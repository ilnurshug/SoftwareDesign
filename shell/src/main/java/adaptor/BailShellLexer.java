package adaptor;

import grammar.ShellLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.LexerNoViableAltException;

public class BailShellLexer extends ShellLexer {
    public BailShellLexer(CharStream input) {
        super(input);
    }

    @Override
    public void recover(LexerNoViableAltException e) {
        throw new RuntimeException(e);
    }
}
