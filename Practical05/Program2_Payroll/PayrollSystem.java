abstract class Employee {
    private final String name;
    private final int id;
    Employee(String name, int id) { this.name = name; this.id = id; }
    public String getName() { return name; }
    public int getId() { return id; }
    public abstract double monthlySalary();
}
class FullTime extends Employee {
    private final double salary;
    FullTime(String name, int id, double salary) { super(name, id); this.salary = salary; }
    @Override public double monthlySalary() { return salary; }
}
class PartTime extends Employee {
    private final int hours;
    private final double rate;
    PartTime(String name, int id, int hours, double rate) { super(name, id); this.hours = hours; this.rate = rate; }
    @Override public double monthlySalary() { return hours * rate; }
}
class Intern extends Employee {
    private final double stipend;
    Intern(String name, int id, double stipend) { super(name, id); this.stipend = stipend; }
    @Override public double monthlySalary() { return stipend; }
}

public class PayrollSystem {
    public static void main(String[] args) {
        Employee[] employees = {
            new FullTime("Rahul", 101, 30000),
            new PartTime("Amit", 102, 80, 200),
            new Intern("Neha", 103, 8000)
        };
        double total = 0;
        for (Employee employee : employees) {
            double salary = employee.monthlySalary();
            System.out.println("Name: " + employee.getName());
            System.out.println("ID: " + employee.getId());
            System.out.println("Salary: " + salary);
            if (employee instanceof Intern)
                System.out.println("Note: This employee is an Intern.");
            total += salary;
            System.out.println("--------------------");
        }
        System.out.println("Total Payroll: " + total);
    }
}
