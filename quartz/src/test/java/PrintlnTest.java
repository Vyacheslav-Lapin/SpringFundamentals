import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Vyacheslav Lapin (http://vlapin.ru/)
 * @version 0.1 (22.04.2015 0:38).
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class PrintlnTest {

    @Test
    public void testPrint() throws Exception {
        System.out.println("Started!");
        while (true);
    }
}
