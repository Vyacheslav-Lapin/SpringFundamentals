package core.annotations;

import core.HelloMessageGenerator;
import core.MessageGenerator;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 * @author Vyacheslav
 * @version 0.1 (02.04.2015).
 */
public class AnnotationHelloMessageGenerator implements HelloMessageGenerator {

    /**
     * Аннотация "Resource" (часть базового Java API JSR-250, а не Spring`а) можно сказать, "трудолюбива" - она даёт
     * возможность задавать значение по-умолчанию в аннотации "Value", но она честно просматривает конфиги и если видит,
     * что в каком-либо из них задано соответствующее значение - вставляется оно. В данном случае в переменную вставится
     * значение "Василий" (раскомментируйте в конфиге строку
     * <bean id="firstName" class="java.lang.String" c:_0="Фёдор"/> ,
     * что бы увидеть иной результат).
     */
    @Resource
    @Value(value = "Василий")
    private String firstName;

    /**
     * Здесь значение "Пупкин" будет проигнорировано, поскольку в конфиге задано иное значение - "Емельяненко":
     * <bean id="lastName" class="java.lang.String" c:_0="Емельяненко"/>
     */
    @Resource
    @Value(value = "Пупкин")
    private String lastName;

    /**
     * Аннотация "Autowired", напротив - "ленива": она ищет значение для подстановки лишь до тех пор, пока не найдёт
     * первое подходящее - и тогда его вставляет. Аннотация "Value" ближе, чем файл конфигурации, её Spring-сканнер
     * видит тут же, что называется, "не отходя от кассы", так что никуда больше не лезет и вставляет значение из неё.
     * Соответственно, здесь будет вставлено значение "Иванович" не смотря на то, что в конфиге есть строчка
     * <bean id="patronymic" class="java.lang.String" c:_0="Викторович"/>
     * (закомментируйте аннотацию "Value"), что бы увидеть иной результат.
     */
    @Autowired
    @Value(value = "Иванович")
    private String patronymic;

    @Override
    public String toString() {
        return String.join(" ", firstName, patronymic, lastName);
    }

    public static void main(String... args) {
        BeanFactory factory = new ClassPathXmlApplicationContext(args.length > 0 ? args[0] : "application-context.xml");

        MessageGenerator messageGenerator = factory.getBean(MessageGenerator.class);
        System.out.println(messageGenerator);
    }
}
