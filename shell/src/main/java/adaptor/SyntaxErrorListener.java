package adaptor;

import logger.Logger;
import org.antlr.v4.runtime.*;

class SyntaxErrorListener extends BaseErrorListener {

    SyntaxErrorListener() {
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e)
    {
        Logger.log("line "+line+":"+charPositionInLine+" at "+offendingSymbol+": "+msg);
    }
}
