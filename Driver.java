/**
 * Project Title: Payroll Lab
 * Project Description: Program takes an input list of employees and outputs a table displaying their first and last names, gender, tenure,
 * rate of pay, and salary. Also calculates the number of employees, displays women on the payroll, calculates raises, orders the employees
 * based on alphabetization, and hires/fires employees.
 *
 * @version April 27, 2018
 * How to Start the Project: Just run it.
 * @author Raymond Vo
 * Palomar ID: 010478907
 * User Instructions: No user interaction needed while the program is running.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Gets the input file and creates a table displaying employee demographics and then displays corresponding information based on gender,
 * salary raises, ordering based on alphabetizaion, and hiring and firing employees.
 */
public class Driver {
    public static void main(String[] args) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("csis.txt"));
        Payroll pay = new Payroll(pw);

        pay.getPayfile();
        pay.outputPayfile();
        pay.outputNumEmployees();
        pay.outputWomen();
        pay.outputTenured();
        pay.giveRaise();
        pay.outputSorted();
        pay.hire();
        pay.fire();
        pw.close();
    }
}
