package firstPackage;

import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EmployeeDatabase {
	
	/**
	 * the main method contains the options for the user to choose. each option is used with while loops and
	 * switch loop so each choice(input) will display
	 * 
	 * @throws IOException Signals that an I/O exception of some sort has occurred.
	 * 
	 * */

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Scanner input = new Scanner(System.in);

		while (true) {
			System.out.println("Choose an option \n" + "Press 1: Add a record \n" + "Press 2: Change a record \n"
					+ "Press 3: Search by Social Security Number \n" + "Press 4: Search By Deparment \n"
					+ "Press 5: Exit");

			int choice = input.nextInt();
			switch (choice) {
			case 1:
				addRecord(input);
				break;
			case 2:
				changeRecord(input);
				break;
			case 3:
				searchBySSN(input);
				break;
			case 4:
				searchByDepartment(input);
				break;
			case 5:
				// exit
				System.out.println("exiting");
				// return;
				input.close();
				break;
			}
		}

	}// end of main

	/**
	 *  writes the list of employees to write to the file.
	 *  
	 * @param employees the list of employees to write into the file
	 * @throws Signals that an I/O exception of some sort has occurred.
	 * */
	public static void writeToFile(ArrayList<Employee> employees) throws IOException {
		FileWriter fw = new FileWriter("EmployeeRecords.txt",true);
		// File file = new File("EmployeeRecords.txt");
		// PrintWriter pw = new PrintWriter(fw);
		for (Employee emp : employees) {
			fw.write(emp.getEmployeeID() + "," + emp.getSSN() + "," + emp.getPerson().getFirstName() + ","
					+ emp.getPerson().getLastName() + "," + emp.getPerson().getAddress() + "," + emp.getDepartment()
					+ "," + emp.getSalary());
		}
		fw.close();
	}// end of writetofile

	/**
	 * 
	 * reads the employee records from the text file and returns them into an ArrayList
	 * 
	 * @return ArrayList of employee objects from the file
	 * @throws Signals that an I/O exception of some sort has occurred.
	 * 
	 * */
	public static ArrayList<Employee> readFromFile() throws IOException {
		File file = new File("EmployeeRecords.txt");
		ArrayList<Employee> employees = new ArrayList<>();
		Scanner input = new Scanner(file);

		while (input.hasNextLine()) {
			String line = input.nextLine();
			String[] emps = line.split(",");
			String employeeID = emps[0];
			String ssn = emps[1];
			String firstName = emps[2];
			String lastName = emps[3];
			String address = emps[4];
			Departments department = Departments.valueOf(emps[5]);
			double salary = Double.parseDouble(emps[6]);

			Person person = new Person(firstName, lastName, address);
			Employee employee = new Employee(employeeID, ssn, person, department, salary);
			employees.add(employee);
		}
		input.close();
		return employees;

	}// end of readfromfile

	
	
	/**
	 * Adds new employee record. the user is prompted to enter the details for the newly added employee.
	 * the SSN is validated and then writes to the file
	 * 
	 * @param input using a scanner object for user input
	 * @throws Signals that an I/O exception of some sort has occurred.
	 * */
	public static void addRecord(Scanner input) throws IOException {
		ArrayList<Employee> employees = readFromFile();
		input.nextLine();// buffer
		System.out.print("Enter Employee ID: ");
		String employeeID = input.nextLine();
		

		// If the ID already exists in the database/text file, issue an error message
		for (Employee emp : employees) {
			if (emp.getEmployeeID().equals(employeeID)) {
				System.out.println("Error!Employee ID already exist");
				return;
			}
		}

		System.out.print("Enter First Name: ");
		String firstName = input.nextLine();

		System.out.print("Enter Last Name: ");
		String lastName = input.nextLine();

		System.out.print("Enter Address: ");
		String address = input.nextLine();

		System.out.print("Enter Departments --> FINANCE, HR, IT , MARKETING: ");
		String departmentName = input.nextLine();
		Departments department = Departments.valueOf(departmentName.toUpperCase());

		System.out.print("Enter Salary: ");
		double salary = input.nextDouble();
		input.nextLine();

		String ssn;
		while (true) {
			System.out.println("Enter SSN in the format of 999-99-9999: ");
			ssn = input.nextLine();
			if (EmployeeSort.isValidSSN(ssn)) {
				// ssn = input.nextLine();
				break;
			} else {
				System.out.println("Invalid SSN format. Please try again");

			}
		} // end of while

		Person person = new Person(firstName, lastName, address);
		Employee newEmp = new Employee(employeeID, ssn, person, department, salary);
		employees.add(newEmp);

		// sorting and writing to file
		// Collections.sort(employees);
		//EmployeeSort.sortByEmployeeID(employees);
		writeToFile(employees);
		System.out.println("emPLOyees succesfully added");
		displayEmployees(employees);
	}// end of addRecord

	
	/**
	 * this changes the salary of the existing employee that is stored in the textfile by searching using the employee ID
	 * 
	 * @param input using a scanner object for user input
	 * @throws Signals that an I/O exception of some sort has occurred.
	 * */
	public static void changeRecord(Scanner input) throws IOException {
		ArrayList<Employee> employees = readFromFile();
		input.nextLine();// buffer
		if (employees.isEmpty()) {
			System.out.println("Database is empty");
			return;
		}

//			System.out.println("Enter employee id to change");
//
//			String employeeID = input.nextLine();
		// input.nextLine();
		boolean exit = true;
		
			System.out.println("Enter employee id to change");
			String employeeID = input.nextLine();
			for (Employee emp : employees) {
				if (emp.getEmployeeID().equals(employeeID)) {
					System.out.println("Enter new salary: ");
					double newSal = input.nextDouble();
					input.nextLine();// buffer

					emp.setSalary(newSal);

					writeToFile(employees);
					exit = false;
					System.out.println("salary succesfully added");
					displayEmployees(employees);
					break;
				} else {
					System.out.println("Error! employee id is not found");
					return;
				}
					
			}

		

		// writeToFile(employees);

	} // end of changerecord

	/**
	 * this method searches for an employee by their social security number (SSN)
	 * if found then displayss the employee's infomation
	 * 
	 * @param input using a scanner object for user input
	 * @throws Signals that an I/O exception of some sort has occurred.
	 * 
	 * */
	public static void searchBySSN(Scanner input) throws IOException {
		ArrayList<Employee> employees = readFromFile();
		input.nextLine();// buffer
		if (employees.isEmpty()) {
			System.out.println("error! Database is empty");
			return;
		}

		boolean exit = true;
		
			System.out.println("Enter SNN in the format of 999-99-9999:");
			String ssn = input.nextLine();
			for (Employee emp : employees) {
				if (emp.getSSN().equals(ssn)) {
					System.out.println("SSN found: ");
					displayEmployees(employees);
					exit = false;
					break;
				}
				else {
					System.out.println("Error! employee's SSN is not found");
					break;
				}
			}
			
			

	}// end of searchbyssn

	/**
	 * this method searches for the employee in certain departments and displays their info.
	 * the user is prompted to enter the department name and the input is validated
	 * 
	 * @param input using a scanner object for user input
	 * @throws Signals that an I/O exception of some sort has occurred.
	 * 
	 * */
	public static void searchByDepartment(Scanner input) throws IOException {
		ArrayList<Employee> employees = readFromFile();
		input.nextLine();
		if (employees.isEmpty()) {
			System.out.println("error! Database is empty");
			return;
		}
		
		System.out.println("Search By Departments --> FINANCE, HR, IT, MARKETING:");
	    System.out.print("Enter Department: ");
	    String departmentName = input.nextLine().trim().toUpperCase();
	    input.nextLine();
	    /*
	     * this catches the exception error when the user inputs the department names. the program is trying to convert a string input into enum constaint,
	     * but the input doesn't match the defined constants in the enumeration
	     * */
	    Departments department;
	    try {
	        department = Departments.valueOf(departmentName);  // finds the matchin enum constants
	    } catch (IllegalArgumentException e) {
	        System.out.println("Invalid department name! Please enter one of the following: FINANCE, HR, IT, MARKETING");
	        return; // thisd exits if the input is invalid
	    }

	    boolean exit = false;
	    for (Employee emp : employees) {
	        if (emp.getDepartment() == department) {
	            if (!exit) System.out.println("Employees in " + department + " department:");
	            EmployeeSort.displayEmployee(emp);
	            exit = true;
	        }
	    }

	    if (!exit) {
	        System.out.println("No employees found in " + department + " department.");
	    }
	
	}

	/**
	 * 
	 * @param employees list of employee objects to display
	 * */
	public static void displayEmployees(ArrayList<Employee> employees) {
		for (Employee emp : employees) {
			EmployeeSort.displayEmployee(emp);
		}
	}// end of displayEmployees
}// end of class
