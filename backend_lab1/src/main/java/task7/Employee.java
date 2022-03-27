package task7;

public class Employee {

    private final String name;
    private final String position;

    public Employee(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return this.name;
    }

    public String getPosition() {
        return this.position;
    }
}
