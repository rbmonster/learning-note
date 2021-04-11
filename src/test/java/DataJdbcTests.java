import com.four.unittest.UnitTestApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@Slf4j
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = UnitTestApplication.class)
@DataJdbcTest
public class DataJdbcTests {

//
//    @Autowired
//    UnitTestService unitTestService;
//
//    @Test
//    public void setUnitTestService(){
//        String s = unitTestService.testJdbcTemplate();
//        Assert.notNull("", s);
//    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testJdbc() {
        List<String> query = jdbcTemplate.query("select * from demo limit 2",
                (resultSet, i) -> resultSet.getString(2));
        log.info(Arrays.toString(query.toArray()));
        Assert.assertEquals("", 2, CollectionUtils.size(query));
    }

}
