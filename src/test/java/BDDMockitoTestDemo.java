import com.four.unittest.UnitTestApplication;
import com.four.unittest.service.UnitInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.*;

@Slf4j
@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = UnitTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners(MockitoTestExecutionListener.class)
public class BDDMockitoTestDemo extends AbstractTestNGSpringContextTests {

    @MockBean
    UnitInterface unitInterface;

    @BeforeClass
    public void setup() {
        given(unitInterface.register(anyString())).willReturn("register success");
        given(unitInterface.calculate(anyDouble())).willThrow(new IllegalArgumentException());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void test(){
        BigDecimal calculate = unitInterface.calculate(123.112d);
        log.info(String.valueOf(calculate));
    }
}
