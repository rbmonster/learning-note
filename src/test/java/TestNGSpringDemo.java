import com.four.unittest.UnitTestApplication;
import com.four.unittest.service.UnitTestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;


@Slf4j
@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = UnitTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestNGSpringDemo extends AbstractTestNGSpringContextTests {

    @Autowired
    UnitTestService unitTestService;

    @Test
    public void test1() {
        log.info("this is test1");
        String s = unitTestService.testJdbcTemplate();
        log.info("jdbc query result is : {}", s);
    }
}
