import com.four.unittest.UnitTestApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@ActiveProfiles(profiles = {"test-exclusion", "test"})
@SpringBootTest(classes = UnitTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class FastTestDemo {

    @Test
    public void test() {
        log.info("~~~");
    }
}
