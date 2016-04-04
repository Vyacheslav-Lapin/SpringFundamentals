package bar;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @author Vyacheslav Lapin (http://vlapin.ru/)
 * @version 0.1 (04.03.2015 13:03).
 */
public interface Contact extends Serializable {
    Long getId();

    void setId(long id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    Date getBirthDate();

    void setBirthDate(Date birthDate);

    List<ContactTelDetail> getContactTelDetails();

    void setContactTelDetails(List<ContactTelDetail> contactTelDetails);
}
