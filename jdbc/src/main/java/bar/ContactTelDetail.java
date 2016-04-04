package bar;

/**
 * @author Vyacheslav Lapin (http://vlapin.ru/)
 * @version 0.1 (04.03.2015 13:03).
 */
public class ContactTelDetail {
    private Long id;
    private Long contactld;
    private String telType;
    private String telNumber;

    // Методы извлечения и установки опущены.
    public String toString() {
        return "Contact Tel Detail - Id: " + id + ", Contact id:" + contactld
                + ", Type: " + telType + ", Number: " + telNumber;
    }


    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getContactld() {
        return contactld;
    }

    public void setContactld(Long contactld) {
        this.contactld = contactld;
    }

    public String getTelType() {
        return telType;
    }

    public void setTelType(String telType) {
        this.telType = telType;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }
}
