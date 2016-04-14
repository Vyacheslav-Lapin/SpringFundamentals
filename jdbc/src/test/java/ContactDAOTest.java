import foo.bar.AppConfig;
import foo.bar.Contact;
import foo.bar.ContactDAO;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class ContactDAOTest extends TestCase {

    @Autowired
    private ContactDAO contactDAO;

    @Test
    public void testFindAll() throws Exception {
        assertTrue(contactDAO.findAll().size() == 3 /*&& */); //TODO: Проверять и на соответствие Contact`ов
    }

    @Test
    public void testFindAllWithDetail() throws Exception {
        List<Contact> allWithDetail = contactDAO.findAllWithDetail();
        assertTrue(allWithDetail.size() == 3 /*&& */); //TODO: Проверять и на соответствие Contact`ов
    }

    @Test
    public void testFindLastNameByld() throws Exception {
        assertEquals(contactDAO.findLastNameById(1), "Ho");
    }

    @Test
    public void testFindByFirstName() throws Exception {
        assertTrue(contactDAO.findByFirstName("Clarence").size() == 1);
    }

    @Test
    public void testFindFirstNameById() throws Exception {

    }

    @Test
    public void testInsert() throws Exception {

    }

    @Test
    public void testInsertWithDetail() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }
}
