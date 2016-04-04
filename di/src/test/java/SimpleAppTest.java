import lab.model.Country;
import lab.model.Person;
import lab.model.UsualPerson;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

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
        Person person = context.getBean(Person.class);
        assertThat(person, is(getExpectedPerson()));
        System.out.println(person.toString());
    }

    static Person getExpectedPerson() {
        int id = 0;
        String name = "John Smith";
        int age = 35;
        float height = 1.78f;
        boolean isProgrammer = true;
        Country country = new Country(1, "Russia", "RU");
        List<String> contacts = asList("asd@asd.ru", "+7-234-456-67-89");

        return new UsualPerson(id, name, age, height, isProgrammer, country, contacts);
    }
}
