package mybatis;

import com.learning.mybatis.MyBatisApplication;
import com.learning.mybatis.dao.DemoMapper;
import com.learning.mybatis.entity.Demo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@SpringBootTest(classes = MyBatisApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class InterceptorTest {

    @Autowired
    private DemoMapper demoMapper;

    @Test
    public void testMapper() {
        Demo demo = demoMapper.selectByPrimaryKey(6562302976415772672L);
        System.out.println();
    }
}
