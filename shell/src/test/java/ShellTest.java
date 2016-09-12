import org.junit.Test;
import shell.Shell;
import static org.junit.Assert.assertEquals;

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
}
