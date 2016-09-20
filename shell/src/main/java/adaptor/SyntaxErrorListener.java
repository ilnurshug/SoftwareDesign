package adaptor;

import logger.Logger;
import org.antlr.v4.runtime.*;

/**
 * listener for logging all syntax errors
 */
class SyntaxErrorListener extends BaseErrorListener {

    SyntaxErrorListener() {
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e)
    {
        Logger.log(String.format("line %d: %d at %s: %s", line, charPositionInLine, offendingSymbol, msg));
    }
}
