package core.xml;

import core.HelloMessageGenerator;
import core.MessageGenerator;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class XmlHelloMessageGenerator implements HelloMessageGenerator {

    private String firstName, lastName, patronymic;

    public XmlHelloMessageGenerator(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public String toString() {
        return Stream.<String>of(firstName, patronymic, lastName).collect(joining(" "));
    }

    public static void main(String... args) {
        // Для простейших операций извлечения не обязательно использовать расширенный интерфейс ApplicationContext -
        // достаточно родительского по отношению к нему интерфейса "BeanFactory".
        BeanFactory factory = new ClassPathXmlApplicationContext(
                // Если конфиг указан в аргументе - конфигурируемся по нему, иначе - по конфигу по-умолчанию
                args.length > 0 ? args[0] : "application-context.xml");

        MessageGenerator messageGenerator = factory.getBean("xmlHelloMessageGenerator", MessageGenerator.class);
        System.out.println(messageGenerator);
    }
}
