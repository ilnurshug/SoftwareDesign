package visitors;

public class WeakQuotingVisitor {

    public static String transform(String str) {
        StringBuilder res = new StringBuilder();

        boolean f = false;
        String id = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '$' && !f) {
                f = true;
            }
            else if (!Character.isAlphabetic(c) && f) {
                res.append(id);
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
