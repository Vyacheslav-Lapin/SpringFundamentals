package core.java;

import core.MessageGenerator;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "core")
@ImportResource("classpath:application-context.xml")
public class JavaAppConfig {

    @Autowired
    private String lastName;

    @Autowired
    private String patronymic;

    @Bean
    public MessageGenerator javaHelloMessageGenerator() {
        return () -> "Hello, Фёдор " + patronymic + " " + lastName + "!";
    }

    public static void main(String... args) throws ClassNotFoundException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(
                args.length > 0 ? Class.forName(args[0]) : JavaAppConfig.class);

        MessageGenerator messageGenerator = beanFactory.getBean("javaHelloMessageGenerator", MessageGenerator.class);
        System.out.println(messageGenerator.getMessage());
    }
}
