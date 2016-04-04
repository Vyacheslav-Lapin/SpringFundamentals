package bar;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {
    public static void main(String... args) throws ClassNotFoundException {
        ContactDAO contactDAO =
                new AnnotationConfigApplicationContext(Class.forName(args[0]))
                        .getBean("contactDAO", ContactDAO.class);

//        findFirstNameById(contactDAO);

//        findLastNameById(contactDAO);

        findByFirstName(contactDAO);

//        findAll(contactDAO);

        update(contactDAO);

        insert(contactDAO);

        insertWithDetail(contactDAO);

        findAllWithDetail(contactDAO);
    }

    private static void findFirstNameById(ContactDAO contactDAO){
        System.out.println("First name for contact id 1 is: " + contactDAO.findFirstNameById(1) + "\n");
    }

    private static void findLastNameById(ContactDAO contactDAO) {
        System.out.println("Last name for contact id 1 is: " + contactDAO.findLastNameById(1) + "\n");
    }

    private static void findAllWithDetail(ContactDAO contactDAO) {
        System.out.println("Выводим контакты с детализированной информацией о телефонах:");
        listContacts(contactDAO.findAllWithDetail());
    }

    private static void insertWithDetail(ContactDAO contactDAO) {
        Contact contact;
        System.out.println("Вставляем контакт вместе с деталями");

        contact = new SimpleContact();
        contact.setFirstName("Michael");
        contact.setLastName("Jackson");
        contact.setBirthDate(new Date((new GregorianCalendar(1964, 10, 1)).getTime().getTime()));

        List<ContactTelDetail> contactTelDetails = new ArrayList<>();

        ContactTelDetail ContactTelDetail = new ContactTelDetail();
        ContactTelDetail.setTelType("Home");
        ContactTelDetail.setTelNumber("11111111");
        contactTelDetails.add(ContactTelDetail);

        ContactTelDetail = new ContactTelDetail();
        ContactTelDetail.setTelType("Mobile");
        ContactTelDetail.setTelNumber("22222222");
        contactTelDetails.add(ContactTelDetail);
        contact.setContactTelDetails(contactTelDetails);
        contactDAO.insertWithDetail(contact);
    }

    private static void insert(ContactDAO contactDAO) {
        System.out.println("Вставляем контакт Rod Johnson");

        Contact contact = new SimpleContact();
        contact.setFirstName("Rod");
        contact.setLastName("Johnson");
        contact.setBirthDate(new Date((new GregorianCalendar(2001, 10, 1)).getTime().getTime()));
        contactDAO.insert(contact);

        findAll(contactDAO);
    }

    private static void update(ContactDAO contactDAO) {
        System.out.println("Update`им контакт с id = 1");

        Contact contact = new SimpleContact();
        contact.setId(1);
        contact.setFirstName("Clarence");
        contact.setLastName("Peter");
        contact.setBirthDate(new Date((new GregorianCalendar(1977, 10, 1)).getTime().getTime()));
        contactDAO.update(contact);

        findAll(contactDAO);
    }

    private static void findAll(ContactDAO contactDAO) {
        System.out.println("Простой список контактов:");
        contactDAO.findAll().forEach(System.out::println);
        System.out.println();
    }

    private static void findByFirstName(ContactDAO contactDAO) {
        System.out.println("Контакты по имени \"Clarence\":");
        contactDAO.findByFirstName("Clarence").forEach(System.out::println);
        System.out.println();
    }

    private static void listContacts(List<Contact> contacts) {
        for (Contact contact : contacts) {
            System.out.println(contact);
            if (contact.getContactTelDetails() != null)
                for (ContactTelDetail contactTelDetail : contact.getContactTelDetails())
                    System.out.println("---" + contactTelDetail);
            System.out.println();
        }
    }
}
