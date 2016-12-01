import logger.Logger;
import org.junit.Test;
import shell.Environment;
import shell.Executor;
import shell.MyExecutor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ShellTest {

    private static final Executor executor = new MyExecutor(Environment.INSTANCE, Logger.INSTANCE);
    
    @Test
    public void echoTest() {
        assertEquals("abc", executor.execute("echo abc"));
        assertEquals("a b c", executor.execute("echo a b  c "));
    }

    @Test
    public void assignmentTest() {
        executor.execute("A = 1");
        executor.execute("B = $A");

        assertEquals("1", executor.execute("echo $B"));

        executor.execute("B = ' $A '");
        assertEquals(" $A ", executor.execute("echo $B"));

        executor.execute("B = \" $A $A \"");
        assertEquals(" 1 1 ", executor.execute("echo $B"));
    }

    @Test
    public void pwdTest() {
        assertEquals(new File("").getAbsolutePath(), executor.execute("pwd"));
    }

    @Test
    public void wcTest() throws IOException {
        File tmp = File.createTempFile("input", ".tmp");
        String filename = tmp.getPath();

        BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));
        bw.write("aa bb cc\nd c");
        bw.close();

        assertEquals("2 5 12 " + filename, executor.execute("wc " + filename));
        assertEquals("2 5 12 " + filename, executor.execute("echo " + filename + " | wc"));
    }

    @Test
    public void catTest() throws IOException {
        File tmp = File.createTempFile("input", ".tmp");
        String filename = tmp.getPath();
        String text = "aa bb cc\nd c";

        BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));
        bw.write(text);
        bw.close();

        assertEquals(text + "\n" + text, executor.execute("cat " + filename + " " + filename));
        assertEquals(text, executor.execute("echo " + filename + " | cat"));
    }

    @Test
    public void procTest() {
        assertEquals("a\nb\nc", executor.execute("./a.out a b c"));
    }

    @Test
    public void grepTest() {
        assertEquals(":blah hello", executor.execute("echo 'blah hello' | grep 'hello'"));
        assertEquals(":blah hrllO", executor.execute("echo 'blah hrllO' | grep -i 'H[er]llo'"));
        assertEquals(":blah hella ss", executor.execute("echo 'blah hella ss' | grep -w 'hell[^o]'"));
        assertEquals("", executor.execute("echo 'blahhello ss' | grep -w 'hello'"));

        assertEquals(":hello\n-aaa", executor.execute("grep -A 1 'hello' ./test/a"));
        assertEquals(":hello\n-aaa\n-bbb", executor.execute("grep -A 2 'hello' ./test/a"));
        assertEquals(":hello", executor.execute("grep -A 2 'hello' ./test/b"));

        assertNull(executor.execute("grep 'hello' ./test/c"));
    }
}
