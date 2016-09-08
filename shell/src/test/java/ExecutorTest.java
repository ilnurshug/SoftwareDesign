import org.junit.Test;
import shell.Executor;
import static org.junit.Assert.assertEquals;

public class ExecutorTest {

    @Test
    public void echoTest() {
        assertEquals("abc", Executor.execute("echo abc"));
        assertEquals("a b c", Executor.execute("echo a b  c "));
    }

    @Test
    public void assignmentTest() {
        Executor.execute("A = 1");
        Executor.execute("B = $A");

        assertEquals("1", Executor.execute("echo $B"));
    }
}
