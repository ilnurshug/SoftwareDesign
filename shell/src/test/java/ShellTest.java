import org.junit.Test;
import shell.Shell;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ShellTest {

    @Test
    public void echoTest() {
        assertEquals("abc", Shell.execute("echo abc"));
        assertEquals("a b c", Shell.execute("echo a b  c "));
    }

    @Test
    public void assignmentTest() {
        Shell.execute("A = 1");
        Shell.execute("B = $A");

        assertEquals("1", Shell.execute("echo $B"));

        Shell.execute("B = ' $A '");
        assertEquals(" $A ", Shell.execute("echo $B"));

        Shell.execute("B = \" $A $A \"");
        assertEquals(" 1 1 ", Shell.execute("echo $B"));
    }

    @Test
    public void pwdTest() {
        assertEquals(new File("").getAbsolutePath(), Shell.execute("pwd"));
    }

    @Test
    public void wcTest() throws IOException {
        File tmp = File.createTempFile("input", ".tmp");
        String filename = tmp.getPath();

        BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));
        bw.write("aa bb cc\nd c");
        bw.close();

        assertEquals("2 5 12 " + filename, Shell.execute("wc " + filename));
        assertEquals("2 5 12 " + filename, Shell.execute("echo " + filename + " | wc"));
    }

    @Test
    public void catTest() throws IOException {
        File tmp = File.createTempFile("input", ".tmp");
        String filename = tmp.getPath();
        String text = "aa bb cc\nd c";

        BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));
        bw.write(text);
        bw.close();

        assertEquals(text + "\n" + text, Shell.execute("cat " + filename + " " + filename));
        assertEquals(text, Shell.execute("echo " + filename + " | cat"));
    }

    @Test
    public void procTest() {
        assertEquals("a\nb\nc", Shell.execute("./a.out a b c"));
    }

    @Test
    public void grepTest() {
        assertEquals(":blah hello", Shell.execute("echo 'blah hello' | grep 'hello'"));
        assertEquals(":blah hrllO", Shell.execute("echo 'blah hrllO' | grep -i 'H[er]llo'"));
        assertEquals(":blah hella ss", Shell.execute("echo 'blah hella ss' | grep -w 'hell[^o]'"));
        assertEquals("", Shell.execute("echo 'blahhello ss' | grep -w 'hello'"));

        assertEquals(":hello\n-aaa", Shell.execute("grep -A 1 'hello' ./test/a"));
        assertEquals(":hello\n-aaa\n-bbb", Shell.execute("grep -A 2 'hello' ./test/a"));
        assertEquals(":hello", Shell.execute("grep -A 2 'hello' ./test/b"));

        assertNull(Shell.execute("grep 'hello' ./test/c"));
    }
}
