package shell;

import java.util.HashMap;

/**
 * represents command execution environment
 */
public class Environment {

    private static final HashMap<String, String> env = new HashMap<String, String>();
    private static String result = null;

    public Environment() {
    }

    /**
     * set variable value in environment
     * @param key variable's name
     * @param val new value
     */
    public void set(String key, String val) {
        env.put(key, val);
    }

    /**
     * @param key variable's name
     * @return value of selected variable
     */
    public String get(String key) {
        return env.getOrDefault(key, null);
    }

    /**
     * @param res execution's result of last command
     */
    public void addCommandResult(String res) {
        result = res;
    }

    /**
     * @return get result of previously executed command
     */
    public String getPrevCmdResult() {
        String tmp = result;
        result = null;
        return tmp;
    }

    public String getCurrentDirectory() {
        return System.getProperty("user.dir");
    }
}
