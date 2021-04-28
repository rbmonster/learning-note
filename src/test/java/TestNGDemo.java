import com.clearspring.analytics.util.Lists;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;

import java.util.Iterator;
import java.util.List;

@Slf4j
public class TestNGDemo {

    @BeforeSuite(groups = "group1")
    public void testBeforeSuite() {
        log.info("testBeforeSuite()");
    }

    @AfterSuite(groups = "group1")
    public void testAfterSuite() {
        log.info("testAfterSuite()");
    }

    @BeforeClass
    public void setup(){
        log.info("this is beforeClass");
    }

    @AfterClass
    public void shutdown(){
        log.info("this is AfterClass");
    }

    @BeforeMethod
    public void testBeforeMethod(){
        log.info("this is beforeMethod");
    }

    @AfterMethod
    public void testAfterMethod(){
        log.info("this is afterMethod");
    }

    @BeforeGroups("group1")
    public void setupGroup1() {
        log.info("setupGroup1()");
    }

    @AfterGroups("group1")
    public void cleanGroup1() {
        log.info("cleanGroup1()");
    }


    @Test(groups = "group1")
    public void test1() {
        log.info("this is test1");
    }

    @Test(groups = "group1", expectedExceptions = ArithmeticException.class)
    public void testException() {
        log.info("this is test2");
        int i = 1 / 0;
        log.info("After division the value of i is :"+ i);
    }

    @Test(enabled = false)
    public void testIgnoreTestcase() {
        log.info("~~~~~~~~~~~~~~~~~ignore ");
    }

    @Test(groups = "group2", timeOut = 5000) // time in mulliseconds
    public void testThisShouldPass() throws InterruptedException {
        Thread.sleep(4000);
    }

    @Test(groups = "group1", dependsOnMethods = "test1")
    public void testDependOn() {
        log.info("this is dependOnMethod~");
    }

    @Test(groups = "group1")
    @Parameters({ "dbconfig", "poolsize" })
    public void createConnection(String dbconfig, int poolsize) {
        log.info("this is parameter Test, parameter is: {}, {}", dbconfig, poolsize);
    }

    @Test(groups = "group2", dataProvider = "addDataProvider")
    public void testDataProvider( String data) {
        log.info("this is testDataProvider, test data: {}",data);
    }

    @DataProvider
    public Iterator<Object> addDataProvider() {
        List<Object> list = Lists.newArrayList();
        list.add("123");
        list.add(("123wesdf"));
        return list.iterator();
    }
}
