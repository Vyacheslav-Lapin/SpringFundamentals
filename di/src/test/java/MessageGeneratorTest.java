import core.MessageGenerator;
import core.java.JavaAppConfig;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.function.BooleanSupplier;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.lang.Thread.sleep;

/**
 * @author Vyacheslav
 * @version 0.1 (02.04.2015).
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/AppConfig.groovy")
@ContextConfiguration(classes = JavaAppConfig.class)
//@ContextConfiguration("classpath:spring-config.xml") В AppConfig уже включён (@ImportResource) в spring-config.xml
public class MessageGeneratorTest extends TestCase implements ApplicationContextAware {

    @Autowired
    private MessageGenerator xmlHelloMessageGenerator;

    @Test
    public void testGetMessageOnXML() throws Exception {
        assertEquals("Hello, Фёдор Владимирович Емельяненко!", xmlHelloMessageGenerator.getMessage());
    }

    @Autowired
    private MessageGenerator annotationHelloMessageGenerator;

    @Test
    public void testGetMessageOnAnnotations() throws Exception {
        assertEquals("Hello, Василий Иванович Емельяненко!", annotationHelloMessageGenerator.getMessage());
    }

    @Autowired
    private MessageGenerator javaHelloMessageGenerator;

    @Test
    public void testGetMessageOnJavaConfig() throws Exception {
        assertEquals("Hello, Фёдор Владимирович Емельяненко!", javaHelloMessageGenerator.getMessage());
    }

    private BeanFactory beanFactory;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        beanFactory = context;
    }

    @Test
    public void testGetMessageOnXmlConfigWithGroovyBean() throws Exception {
        testProcess(() -> p(beanFactory.getBean("groovyHelloMessageGenerator", MessageGenerator.class).getMessage())
                .equals("Hello, Фёдор Владимирович Емельяненко!"), 1000);
    }

    private static void testProcess(BooleanSupplier supplier, int delay) throws InterruptedException {
        while (!supplier.getAsBoolean())
            sleep(delay);
    }

    private static String p(String s) {
        out.println(currentTimeMillis() + ": " + s);
        return s;
    }
}
