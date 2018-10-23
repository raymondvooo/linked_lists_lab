import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Payroll class contains key methods for running program and displaying/collecting demographics.
 */
public class Payroll {
    private PrintWriter pw;
    private ObjectList payfile = new ObjectList();
    private ObjectList orderedList = new ObjectList();
    private int numEmployees = 0;

    /**
     * constructor which takes in PrintWriter to output to file
     *
     * @param pw file to be output to
     */
    public Payroll(PrintWriter pw) {
        this.pw = pw;
    }

    /**
     * getPayfile method gets the input file and creates an objectlist for storing the employees and their demographics. Also counts the number of employees
     *
     * @throws IOException if the input file cannot be found
     */
    public void getPayfile() throws IOException {
        String firstName, lastName, gender, rate;
        int tenure;
        double salary;
        Scanner fileScan = new Scanner(new File("payfile.txt"));

        while (fileScan.hasNextLine()) {
            firstName = fileScan.next();
            lastName = fileScan.next();
            gender = fileScan.next();
            tenure = fileScan.nextInt();
            rate = fileScan.next();
            salary = fileScan.nextDouble();
            Employee person = new Employee(firstName, lastName, gender, tenure, rate, salary);
            payfile.addLast(person);
            numEmployees++;
        }
        fileScan.close();
    }

    /**
     * outputPayfile method creates a table and outputs the employee's demographics in a proper manner
     */
    public void outputPayfile() {
        System.out.printf("%-9s    %-9s    %-1s    %-1s    %-1s    %-5s\n", "First Name", "Last Name", "Gender", "Tenure", "Rate", "Salary");
        pw.printf("%-9s    %-9s    %-1s    %-1s    %-1s    %-5s\r\n", "First Name", "Last Name", "Gender", "Tenure", "Rate", "Salary");

        System.out.println("--------------------------------------------------------------");
        pw.println("--------------------------------------------------------------");


        ObjectListNode p = payfile.getFirstNode();
        while (p != null) {
            Employee person = (Employee) p.getInfo();
            System.out.printf("%-10s    %-12s    %-5s    %-5d    %-2s    %7.2f\n", person.getFirstName(), person.getLastName(), person.getGender(),
                    person.getTenure(), person.getRate(), person.getSalary());
            pw.printf("%-10s    %-12s    %-5s    %-5d    %-2s    %7.2f\r\n", person.getFirstName(), person.getLastName(), person.getGender(),
                    person.getTenure(), person.getRate(), person.getSalary());
            p = p.getNext();
        }
        System.out.println("--------------------------------------------------------------\n");
        pw.println("--------------------------------------------------------------\r\n");

    }

    /**
     * outputNumEmployees method outputs number of employees
     */
    public void outputNumEmployees() {
        System.out.println("Number of employees: " + numEmployees);
        pw.println("Number of employees: " + numEmployees);

    }

    /**
     * outputWomen method outputs the first and last name of all women on the payroll in tabular format
     */
    public void outputWomen() {
        System.out.println("\nWomen on the payroll: ");
        pw.println("\r\nWomen on the payroll: ");
        System.out.printf("%-9s  %-9s\n", "First Name", "Last Name");
        pw.printf("%-9s  %-9s\r\n", "First Name", "Last Name");

        System.out.println("----------------------");
        pw.println("----------------------");

        ObjectListNode p = payfile.getFirstNode();
        while (p != null) {
            Employee person = (Employee) p.getInfo();
            if (person.getGender().equals("F")) {
                System.out.printf("%-12s%-12s\n", person.getFirstName(), person.getLastName());
                pw.printf("%-12s%-12s\r\n", person.getFirstName(), person.getLastName());

            }
            p = p.getNext();
        }
    }

    /**
     * outputTenured method outputs the weekly employees who make more than $35,000 a year and have been with the company for over 5 years
     */
    public void outputTenured() {
        System.out.println("\nWeekly employees w/ salary greater than $35,000/year and have been with company for 5+ years:");
        pw.println("\r\nWeekly employees w/ salary greater than $35,000/year and have been with company for 5+ years:");

        ObjectListNode p = payfile.getFirstNode();
        while (p != null) {
            Employee person = (Employee) p.getInfo();
            if (person.getRate().equals("W") && person.getSalary() * 52 > 35000 && person.getTenure() > 4) {
                System.out.printf("%14s %s %s\n%7s%4s%.2f\n%7s%7.2f\n", "Name:", person.getFirstName(), person.getLastName(), "Weekly Salary:", "$", person.getSalary(),
                        "Yearly Salary: $", person.getSalary() * 52);
                pw.printf("%14s %s %s\r\n%7s%4s%.2f\r\n%7s%7.2f\r\n", "Name:", person.getFirstName(), person.getLastName(), "Weekly Salary:", "$", person.getSalary(),
                        "Yearly Salary: $", person.getSalary() * 52);
            }
            p = p.getNext();
        }
    }

    /**
     * giveRaise method gives a $.75 raise to all hourly employees making less than $10/hour and a $50.00 raise to all weekly employees making less than
     * $350/week then outpouts their names and new salaries
     */
    public void giveRaise() {
        System.out.println("\n$.75 raise to all hourly employees making less than $10.00/hour:");
        pw.println("\r\n$.75 raise to all hourly employees making less than $10.00/hour:");

        ObjectListNode p = payfile.getFirstNode();
        while (p != null) {
            Employee person = (Employee) p.getInfo();
            if (person.getRate().equals("H") && person.getSalary() < 10) {
                double hourly = person.getSalary() + .75;
                System.out.printf("%18s %s %s\n%7s%-7.2f\n\n", "Name:", person.getFirstName(), person.getLastName(), "New weekly salary: $", hourly);
                pw.printf("%18s %s %s\r\n%7s%-7.2f\r\n\r\n", "Name:", person.getFirstName(), person.getLastName(), "New weekly salary: $", hourly);
            }
            p = p.getNext();
        }
        System.out.println("\n$50.00 raise to all weekly employees making less than $350.00/week:");
        pw.println("\r\n$50.00 raise to all weekly employees making less than $350.00/week:");

        p = payfile.getFirstNode();
        while (p != null) {
            Employee person = (Employee) p.getInfo();
            if (person.getRate().equals("W") && person.getSalary() < 350) {
                double weekly = person.getSalary() + 10;
                System.out.printf("%18s %s %s\n%7s%-7.2f\n\n", "Name:", person.getFirstName(), person.getLastName(), "New weekly salary: $", weekly);
                pw.printf("%18s %s %s\r\n%7s%-7.2f\r\n\r\n", "Name:", person.getFirstName(), person.getLastName(), "New weekly salary: $", weekly);
            }
            p = p.getNext();
        }
    }

    /**
     * outputSorted method outputs a table of employees on the payroll in alphabetical order based on last names as well as outputting their salaries
     */
    public void outputSorted() {
        System.out.println("\nList of employees alphabetically ordered by last/first:");
        pw.println("\r\nList of employees alphabetically ordered by last/first:");


        while (payfile.getFirstNode() != null) {
            orderedList.insert(payfile.removeFirst());
        }
        payfile = orderedList;
        System.out.printf("%-9s  %-9s %12s\n", "First Name", "Last Name", "Salary");
        pw.printf("%-9s  %-9s %12s\r\n", "First Name", "Last Name", "Salary");

        System.out.println("----------------------------------");
        pw.println("----------------------------------");

        ObjectListNode p = payfile.getFirstNode();
        while (p != null) {
            Employee person = (Employee) p.getInfo();
            System.out.printf("%-12s%-12s%10.2f\n", person.getFirstName(), person.getLastName(), person.getSalary());
            pw.printf("%-12s%-12s%10.2f\r\n", person.getFirstName(), person.getLastName(), person.getSalary());
            p = p.getNext();
        }
    }

    /**
     * hire method takes in the hire input file and creates the appropriate employees, adds them to the ordered payroll and outputs the new payroll
     *
     * @throws IOException if the input file cannot be found
     */
    public void hire() throws IOException {
        ObjectList hire = new ObjectList();
        String firstName, lastName, gender, rate;
        int tenure;
        double salary;
        Scanner fileScan = new Scanner(new File("hirefile.txt"));

        while (fileScan.hasNextLine()) {
            firstName = fileScan.next();
            lastName = fileScan.next();
            gender = fileScan.next();
            tenure = fileScan.nextInt();
            rate = fileScan.next();
            salary = fileScan.nextDouble();
            Employee person = new Employee(firstName, lastName, gender, tenure, rate, salary);
            hire.addLast(person);
        }
        fileScan.close();
        while (hire.getFirstNode() != null) {
            payfile.insert(hire.removeFirst());
        }
        System.out.println("\nList of employees after new hires:");
        pw.println("\r\nList of employees after new hires:");
        outputNames();

    }

    /**
     * fire method takes in input file for seeing which employees to fire and deletes them from the ordered payroll and outputs the new payroll
     *
     * @throws IOException
     */
    public void fire() throws IOException {
        String firstName, lastName;
        Scanner fileScan = new Scanner(new File("firefile.txt"));

        while (fileScan.hasNextLine()) {
            firstName = fileScan.next();
            lastName = fileScan.next();

            Employee person = new Employee(firstName, lastName);
            if (payfile.contains(person)) {
                payfile.remove(person);
            }

        }
        fileScan.close();
        System.out.println("\nList of employees after fires:");
        pw.println("\r\nList of employees after fires:");
        outputNames();
    }

    /**
     * outputNames method for outputting payroll's list of employees (just first and last name)
     */
    private void outputNames() {
        System.out.printf("%-9s  %-9s\n", "First Name", "Last Name");
        pw.printf("%-9s  %-9s\r\n", "First Name", "Last Name");

        System.out.println("---------------------");
        pw.println("---------------------");

        ObjectListNode p = payfile.getFirstNode();
        while (p != null) {
            Employee person = (Employee) p.getInfo();
            System.out.printf("%-12s%-12s\n", person.getFirstName(), person.getLastName());
            pw.printf("%-12s%-12s\r\n", person.getFirstName(), person.getLastName());
            p = p.getNext();
        }
    }
}
