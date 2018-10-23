/**
 * Employee class creates the employee object and allows comparing of employees based on last name.
 */
public class Employee implements Comparable {
    private String firstName, lastName, gender, rate;
    private int tenure;
    private double salary;

    /**
     * Employee constructor builds employee with all demographics.
     *
     * @param firstName first name of employee
     * @param lastName  last name of employee
     * @param gender    gender of employee
     * @param tenure    how many years employee has been with company
     * @param rate      weekly or hourly rate of pay
     * @param salary    employee's pay
     */
    public Employee(String firstName, String lastName, String gender, int tenure, String rate, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.tenure = tenure;
        this.rate = rate;
        this.salary = salary;
    }

    /**
     * Constructor for employee with just first and last name
     *
     * @param firstName first name of employee
     * @param lastName  last name of employee
     */
    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * getFirstName method gets the employee's first name
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * getLastName method gets the employee's last name
     *
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * getGender method gets employee's gender
     *
     * @return either male or female
     */
    public String getGender() {
        return gender;
    }

    /**
     * getTenure method gets number of years employee has been in company
     *
     * @return number of years employee has been in company
     */
    public int getTenure() {
        return tenure;
    }

    /**
     * getRate method gets the rate of employee's pay (either weekly or hourly)
     *
     * @return weekly or hourly rate of pay
     */
    public String getRate() {
        return rate;
    }

    /**
     * getSalary method gets employee's salary
     *
     * @return employee's salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * compareTo method allows employee to be compared based on last name
     *
     * @param o the employee to be compared
     * @return which employee's last name comes before the other
     */
    public int compareTo(Object o) {
        Employee e = (Employee) o;
        return lastName.compareTo(e.getLastName());
    }
}
