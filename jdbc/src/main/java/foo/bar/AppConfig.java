package foo.bar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author Vyacheslav Lapin (http://vlapin.ru/)
 * @version 0.1 (04.03.2015 18:18).
 */
@Configuration
@ImportResource("classpath:spring-config.xml")
//@ComponentScan(basePackages = "foo.bar")
public class AppConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public MappingSqlQuery<Contact> findAllContactsMappingSqlQuery() {

        return new MappingSqlQuery<Contact>(dataSource,
                "select id, first_name, last_name, birth_date from contact") {
            @Override
            protected Contact mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Contact contact = new SimpleContact();
                contact.setId(resultSet.getLong("id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setBirthDate(resultSet.getDate("birth_date"));
                return contact;
            }
        };
    }

    @Bean
    public MappingSqlQuery<Contact> getContactByFirstName() {

        MappingSqlQuery<Contact> result = new MappingSqlQuery<Contact>(dataSource,
                "select id, first_name, last_name, birth_date from contact where first_name = :first_name") {
            @Override
            protected Contact mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Contact contact = new SimpleContact();
                contact.setId(resultSet.getLong("id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setBirthDate(resultSet.getDate("birth_date"));
                return contact;
            }
        };
        result.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
        return result;
    }

    @Bean
    public SqlUpdate updateContact() {
        SqlUpdate result = new SqlUpdate(dataSource,
                "update contact set first_name=:first_name, last_name=:last_name, birth_date=:birth_date where id=:id");
        result.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
        result.declareParameter(new SqlParameter("last_name", Types.VARCHAR));
        result.declareParameter(new SqlParameter("birth_date", Types.DATE));
        result.declareParameter(new SqlParameter("id", Types.INTEGER));

        return result;
    }

    @Bean
    public SqlUpdate insertContact() {
        SqlUpdate result = new SqlUpdate(dataSource,
                "insert into contact (first_name, last_name, birth_date) " +
                        "values (:first_name, :last_name, :birth_date)");
        result.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
        result.declareParameter(new SqlParameter("last_name", Types.VARCHAR));
        result.declareParameter(new SqlParameter("birth_date", Types.DATE));
        result.setGeneratedKeysColumnNames("id");
        result.setReturnGeneratedKeys(true);
        return result;
    }

//    @Autowired BatchSqlUpdate insertContactWithTelDetails;

    @Bean
    public BatchSqlUpdate insertContactWithTelDetails() {
        BatchSqlUpdate insertContactWithTelDetails = new BatchSqlUpdate(dataSource,
                "insert into contact_tel_detail (contact_id, tel_type, tel_number) " +
                        "values (:contact_id, :tel_type, :tel_number)");
        insertContactWithTelDetails.setBatchSize(10);

        insertContactWithTelDetails.declareParameter(new SqlParameter("contact_id", Types.INTEGER));
        insertContactWithTelDetails.declareParameter(new SqlParameter("tel_type", Types.VARCHAR));
        insertContactWithTelDetails.declareParameter(new SqlParameter("tel_number", Types.VARCHAR));

        return insertContactWithTelDetails;
    }
}
