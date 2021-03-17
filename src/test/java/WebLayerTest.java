import com.four.transaction.controller.UnitController;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * <pre>
 * @Description:
 * TODO 有问题
 * </pre>
 *
 * @version v1.0
 * @ClassName: WebLayerTest
 * @Author: sanwu
 * @Date: 2021/3/18 0:38
 */
//@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UnitController.class)
@AutoConfigureMockMvc(addFilters = false)
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/unit/one"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("one")));
    }
}
