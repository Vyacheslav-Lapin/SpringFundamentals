package lab.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class UsualPerson implements Person {

    @Id
    @Column
    private final int id;

    @Column
    private final String name;

    private final int age;
    private final float height;
    private final boolean isProgrammer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private final Country country;

    private final List<String> contacts;


    public UsualPerson(int id, String name, int age, float height, boolean isProgrammer, Country country, List<String> contacts) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.isProgrammer = isProgrammer;
        this.country = country;
        this.contacts = contacts;
    }


    @Override
    public void sayHello(Person person) {
        System.out.println("Hello, " + person);
    }


    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public int getAge() {
        return age;
    }

    public float getHeight() {
        return height;
    }

    public boolean isProgrammer() {
        return isProgrammer;
    }

    public List<String> getContacts() {
        return contacts;
    }

    @Override
    public String toString() {
        return "UsualPerson{" + "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", age=" + age +
                ", height=" + height +
                ", isProgrammer=" + isProgrammer +
                ", contacts=" + contacts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        UsualPerson that;
        return this == o
                || !(o == null || getClass() != o.getClass())
                && id == (that = (UsualPerson) o).id
                && age == that.age
                && Float.compare(that.height, height) == 0
                && isProgrammer == that.isProgrammer
                && Objects.equals(name, that.name)
                && Objects.equals(country, that.country)
                && Objects.equals(contacts, that.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, age, height, isProgrammer, contacts);
    }
}
