import java.util.HashMap;

public class Environment {
    public static final Environment INSTANCE = new Environment();

    private final HashMap<String, String> env = new HashMap<String, String>();

    private Environment() {
    }

    public void set(String key, String val) {
        env.put(key, val);
    }

    public String get(String key) {
        return env.getOrDefault(key, null);
    }
}
