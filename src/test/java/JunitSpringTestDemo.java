import com.four.unittest.UnitTestApplication;
import com.four.unittest.service.UnitTestService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: SpringTestDemo
 * @Author: sanwu
 * @Date: 2021/3/16 22:28
 */

@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = UnitTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class JunitSpringTestDemo {

    @LocalServerPort
    private int port;

    @Autowired
    private UnitTestService unitTestService;

    @MockBean
    private TransactionTemplate transactionTemplate;

    @Before
    public void setUp() {

    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        String forObject = this.restTemplate.getForObject("http://localhost:" + port + "/learning/unit/one", String.class);
        System.out.println(forObject);
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/unit/one"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("one")));
    }

    @Test
    public void testTemplate() {
        String s = unitTestService.testJdbcTemplate();
        Assert.assertEquals("return result should be bbb", "bbb", s);
    }
}
