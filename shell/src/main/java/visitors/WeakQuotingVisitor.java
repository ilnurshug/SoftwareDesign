package visitors;

import shell.Environment;

public class WeakQuotingVisitor {

    public static String transform(String str) {
        StringBuilder res = new StringBuilder();

        boolean f = false;
        String id = "";
        for (int i = 1; i < str.length() - 1; i++) {
            char c = str.charAt(i);

            if (c == '$' && !f) {
                f = true;
            }
            else if (!Character.isAlphabetic(c) && f) {
                res.append(Environment.INSTANCE.get(id));
                res.append(c);
                id = "";
                f = false;
            }
            else if (f) {
                id += c;
            }
            else {
                res.append(c);
            }
        }

        return res.toString();
    }

}
