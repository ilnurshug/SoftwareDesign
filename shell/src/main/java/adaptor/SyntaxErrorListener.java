package adaptor;

import logger.Logger;
import org.antlr.v4.runtime.*;

import java.util.logging.Level;

public class SyntaxErrorListener extends BaseErrorListener {

    public SyntaxErrorListener() {
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e)
    {
        Logger.INSTANCE.log(Level.ALL, "line "+line+":"+charPositionInLine+" at "+offendingSymbol+": "+msg);
    }
}
