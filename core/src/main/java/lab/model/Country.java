package lab.model;

import java.io.Serializable;
import java.util.Objects;

public class Country implements Serializable{
    private int id;
    private String name;
    private String codeName;

    public Country() {
        this("", "");
    }

    public Country(String name, String codeName) {
        this(0, name, codeName);
    }

    public Country(int id, String name, String codeName) {
        this.id = id;
        this.name = name;
        this.codeName = codeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    @Override
    public String toString() {
        return "Country{" + "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + codeName + "\'}";
    }

    @Override
    public boolean equals(Object o) {
        Country country;
        return this == o
                || !(o == null || getClass() != o.getClass())
                && id == (country = (Country) o).id
                && Objects.equals(name, country.name)
                && Objects.equals(codeName, country.codeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, codeName);
    }
}
