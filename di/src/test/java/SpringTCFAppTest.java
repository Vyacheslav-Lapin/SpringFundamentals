import lab.model.Country;
import lab.model.Person;
import lab.model.UsualPerson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class SpringTCFAppTest {

    @Autowired
    private Person person;

    @Test
    public void testInitPerson() {
        assertThat(person, is(getExpectedPerson()));
    }

    private Person getExpectedPerson() {
        return new UsualPerson(0, "John Smith", 35, 1.78f, true,
                new Country(1, "Russia", "RU"),
                asList("asd@asd.ru", "+7-234-456-67-89"));
    }
}
