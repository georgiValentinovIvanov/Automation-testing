package Introduction07.Exercises.Employee;

public class exercises {
    public static void main(String[] args) {
        Employee employee = new Employee(1, "Dimitar", "Tarkalanov", 20);
        String name = employee.getName();
        double annualSalary = employee.getAnnualSalary();
        System.out.println(employee);

        double raisedSalary = employee.raiseSalary(-10);
        annualSalary = employee.getAnnualSalary();
        System.out.println(employee);
    }
}
