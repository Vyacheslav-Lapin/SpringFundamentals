import lab.model.Country;
import lab.model.Person;
import lab.model.UsualPerson;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleAppTest {

    private static final String APPLICATION_CONTEXT_XML_FILE_NAME =
            "classpath:application-context.xml";

    private ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML_FILE_NAME);
    }

    @Test
    public void testInitPerson() {
        assertThat(context.getBean(Person.class), is(getExpectedPerson()));
    }

    private Person getExpectedPerson() {
        return new UsualPerson(0, "John Smith", 35, 1.78f, true,
                new Country(1, "Russia", "RU"),
                asList("asd@asd.ru", "+7-234-456-67-89"));
    }
}
