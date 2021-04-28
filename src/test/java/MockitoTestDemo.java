import com.four.unittest.UnitTestApplication;
import com.four.unittest.controller.UnitController;
import com.four.unittest.service.UnitInterface;
import com.four.unittest.service.UnitTestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = UnitTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MockitoTestDemo {

    @Autowired
    private UnitTestService unitTestService;

    @MockBean
    private UnitInterface unitInterface;

    @Test
    public void test1() {
        UnitInterface spy = Mockito.spy(unitInterface);
        Mockito.when(spy.register(Mockito.anyString())).thenReturn("gogogo");
        String s = spy.register("123");
        log.info(s);
    }

    @Test
    public void test() {
        UnitTestService spy = Mockito.spy(unitTestService);
        Mockito.when(spy.testSql()).thenReturn("fadsfasdfasdf");
        String result = spy.testSql();
        log.info(result);   // return fadsfasdfasdf

        Mockito.when(spy.testParameter(Mockito.anyInt(),
                        Mockito.anyString(),
                        Mockito.any(),
                        Mockito.anyList(),
                        Mockito.anyMap()))
                .thenReturn("mock success");

        String res = spy.testParameter(1, "sddf", BigDecimal.ZERO, new ArrayList<>(), new HashMap<>());
        log.info(res);
    }
}
