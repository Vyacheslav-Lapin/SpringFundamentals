package lab.model;

public class Customer implements Person {
    private String name;
    private boolean broke;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public boolean isBroke() {
        return broke;
    }

    public void setBroke(boolean broke) {
        this.broke = broke;
    }

    @Override
    public void sayHello(Person person) {

    }
}
