package adaptor;

import grammar.ShellLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.LexerNoViableAltException;

/**
 * bailing out of the lexer at the first lexical error
 */
class BailShellLexer extends ShellLexer {
    BailShellLexer(CharStream input) {
        super(input);
    }

    /**
     * Make sure we don't attempt to recover from lexical problems.
     */
    @Override
    public void recover(LexerNoViableAltException e) {
        throw new RuntimeException(e);
    }
}
