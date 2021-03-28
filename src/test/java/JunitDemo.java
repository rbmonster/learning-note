import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.*;

@Slf4j
public class JunitDemo {

    @BeforeClass
    public static void beforeClass() {
        log.info("this is beforeClass method");
    }

    @Before
    public void setUp() {
        log.info("this is before method");
    }


    @Test
    public void testDemo() {
        log.info("hello junit world~");
    }

    @Ignore
    public void testIgnore(){
        log.info("this is ignore method");
    }

    @After
    public void finish() {
        log.info("this is after method");
    }

    @AfterClass
    public static void afterClass(){
        log.info("this is afterClass method");
    }
}
