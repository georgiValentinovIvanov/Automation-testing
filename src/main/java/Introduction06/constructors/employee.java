package Introduction06.constructors;

public class employee {private String firstName;
    private String lastName;
    private int birthYear;

    public employee() {
    }

    public employee(String firstName, String lastName, int birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    public employee(String firstName, String lastName) {
        this(firstName, lastName, -1);
    }
}
