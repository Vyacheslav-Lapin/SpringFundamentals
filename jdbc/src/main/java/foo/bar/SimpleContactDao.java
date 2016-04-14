package foo.bar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.BatchSqlUpdate;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("contactDAO")
public class SimpleContactDao implements ContactDAO {

    private static Log log = LogFactory.getLog(SimpleContactDao.class);

//    @Resource(name = "dataSource") private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private MappingSqlQuery<Contact> findAllContactsMappingSqlQuery;
    @Autowired
    private MappingSqlQuery<Contact> getContactByFirstName;
    @Autowired
    private SqlUpdate updateContact;
    @Autowired
    private SqlUpdate insertContact;
    @Autowired
    private BatchSqlUpdate insertContactWithTelDetails;

    @Override
    public List<Contact> findAll() {
        return findAllContactsMappingSqlQuery.execute();
    }

    @Override
    public List<Contact> findAllWithDetail() {
        return jdbcTemplate.query(
                "select c.id, c.first_name, c.last_name, c.birth_date, t.id as contact_tel_id, " +
                        "t.tel_type, t.tel_number from contact c left join contact_tel_detail t on c.id = t.contact_id",
                resultSet -> {
                    Map<Long, Contact> map = new HashMap<>();
                    Contact contact;
                    while (resultSet.next()) {
                        Long id = resultSet.getLong("id");
                        contact = map.get(id);
                        if (contact == null) { // новая запись контакта
                            contact = new SimpleContact();
                            contact.setId(id);
                            contact.setFirstName(resultSet.getString("first_name"));
                            contact.setLastName(resultSet.getString("last_name"));
                            contact.setBirthDate(resultSet.getDate("birth_date"));
                            contact.setContactTelDetails(new ArrayList<>());
                            map.put(id, contact);
                        }
                        // Обработать информацию о телефонах контакта (если есть).
                        long contactTelDetailId = resultSet.getLong("contact_tel_id");
                        if (contactTelDetailId > 0) {
                            ContactTelDetail contactTelDetail = new ContactTelDetail();
                            contactTelDetail.setId(contactTelDetailId);
                            contactTelDetail.setContactld(id);
                            contactTelDetail.setTelType(resultSet.getString("tel_type"));
                            contactTelDetail.setTelNumber(resultSet.getString("tel_number"));
                            contact.getContactTelDetails().add(contactTelDetail);
                        }
                    }
                    return new ArrayList<>(map.values());
                }
        );
    }

    // Код для других методов не показан,
    @Override
    public String findLastNameById(int id) {
        return namedParameterJdbcTemplate.queryForObject(
                "select last_name from contact where id = :contactId",
                new MapSqlParameterSource("contactId", id),
                String.class
        );
    }

    @Override
    public List<Contact> findByFirstName(String firstName) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", firstName);
        return getContactByFirstName.executeByNamedParam(paramMap);
    }

    @Override
    public String findFirstNameById(int id) {
        return jdbcTemplate.queryForObject(
                "select first_name from contact where id = ?",
                new Object[]{id},
                String.class);
    }

    @Override
    public void insert(Contact contact) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", contact.getFirstName()) ;
        paramMap.put("last_name", contact.getLastName());
        paramMap.put("birth_date", contact.getBirthDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertContact.updateByNamedParam(paramMap, keyHolder);
        contact.setId(keyHolder.getKey().longValue());
        log.info("New contact inserted with id: " + contact.getId());
    }

    @Override
    public void insertWithDetail(Contact contact) {
        // Вставить контакт.
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", contact.getFirstName());
        paramMap.put("last_name", contact.getLastName());
        paramMap.put("birth_date", contact.getBirthDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertContact.updateByNamedParam(paramMap, keyHolder);
        contact.setId(keyHolder.getKey().longValue());
        log.info("New contact inserted with id: " + contact.getId());

        // Пакетная операция вставки телефонов для контакта.
        List<ContactTelDetail> contactTelDetails = contact.getContactTelDetails();
        if (contactTelDetails != null)
            for (ContactTelDetail contactTelDetail : contactTelDetails) {
                paramMap = new HashMap<>();
                paramMap.put("contact_id", contact.getId());
                paramMap.put("tel_type", contactTelDetail.getTelType());
                paramMap.put("tel_number", contactTelDetail.getTelNumber());
                insertContactWithTelDetails.updateByNamedParam(paramMap);
            }
        insertContactWithTelDetails.flush();
    }


            @Override
    public void update(Contact contact) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", contact.getFirstName());
        paramMap.put("last_name", contact.getLastName()) ;
        paramMap.put("birth_date", contact.getBirthDate());
        paramMap.put("id", contact.getId());
        updateContact.updateByNamedParam(paramMap);
        log.info("Existing contact updated with id:" + contact.getId());
    }

    @Override
    public void delete(long contactId) {

    }
}
