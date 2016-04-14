package foo.bar;

import java.sql.Date;
import java.util.List;

/**
 * @author Vyacheslav Lapin (http://vlapin.ru/)
 * @version 0.1 (04.03.2015 13:04).
 */
public class SimpleContact implements Contact {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private List<ContactTelDetail> contactTelDetails;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Date getBirthDate() {
        return birthDate;
    }

    @Override
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public List<ContactTelDetail> getContactTelDetails() {
        return contactTelDetails;
    }

    @Override
    public void setContactTelDetails(List<ContactTelDetail> contactTelDetails) {
        this.contactTelDetails = contactTelDetails;
    }


    public String toString() {
        return "Contact - Id: " + id + ", First name: " + firstName
                + ", Last name: " + lastName + ", Birthday: " + birthDate;
    }
}
