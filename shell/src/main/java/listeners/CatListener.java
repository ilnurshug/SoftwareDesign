package listeners;

import grammar.ShellParser;

public class CatListener extends ListenerHelper<ShellParser.CatContext> {
    public static final CatListener INSTANCE = new CatListener();

    @Override
    public void enterRule(ShellParser.CatContext context) {

    }

    @Override
    public void exitRule(ShellParser.CatContext context) {

    }
}
