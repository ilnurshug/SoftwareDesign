import org.junit.Test;
import shell.Executor;
import static org.junit.Assert.assertEquals;

public class ExecutorTest {

    @Test
    public void echoTest() {
        assertEquals("abc", Executor.execute("echo abc"));
        assertEquals("a b c", Executor.execute("echo a b  c "));
    }
}
