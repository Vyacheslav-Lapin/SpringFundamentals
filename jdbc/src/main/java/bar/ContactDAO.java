package bar;

import java.util.List;

/**
 * @author Vyacheslav Lapin (http://vlapin.ru/)
 * @version 0.1 (04.03.2015 13:01).
 */
public interface ContactDAO {
    List<Contact> findAll();
    List<Contact> findAllWithDetail();
    String findLastNameById(int id);
    List<Contact> findByFirstName(String firstName);
    String findFirstNameById(int id);
    void insert(Contact contact);
    void insertWithDetail(Contact contact);
    void update(Contact contact);
    void delete(long contactId);
}
