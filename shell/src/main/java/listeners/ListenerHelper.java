package listeners;

import org.antlr.v4.runtime.ParserRuleContext;

public abstract class ListenerHelper<Context extends ParserRuleContext> {

    public abstract void enterRule(Context context);

    public abstract void exitRule(Context context);

}
