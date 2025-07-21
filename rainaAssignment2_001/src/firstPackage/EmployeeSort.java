package firstPackage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EmployeeSort {
/**
 * the main method is used to collect the employee information and 5 employee array objects is created
 * once all 5 employee object is created, it gets stored into employee records text file
 * 
 * @throws IOException Signals that an I/O exception of some sort has occurred.
 * 
 * */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

	
		Scanner input = new Scanner(System.in);
		//employee array
		Employee[] employees = new Employee[5];
		
		
		
		
		//employee details
		for(int i = 0; i < employees.length; i++) {
			
			//person info
			System.out.print("firstname of employee " + (i +1) +": ");
			String firstname = input.nextLine();
			
			System.out.print("lastname of employee " + (i +1) +":" );
			String lastname = input.nextLine();
			
			System.out.print("address of employee " + (i +1) +":");
			String address = input.nextLine();
			
			//creating new person object
			Person person = new Person(firstname,lastname,address);
			
			//depmartment info
			System.out.print(" Departments --> FINANCE, HR, IT , MARKETING: ");
			String departmentName = input.nextLine();
			Departments department = Departments.valueOf(departmentName.toUpperCase());
			
			//salary info
			System.out.print("Annual Salary: ");
			double salary = input.nextDouble();
			input.nextLine(); //buffer
			
			System.out.println("Enter the details for each employee " + (i +1) + ": ");
			System.out.print("Employee ID: ");
			String employeeID = input.nextLine();
			
			
			String ssn;
			while(true) {
				System.out.println("Enter SNN in the format of 999-99-9999: ");
				ssn = input.nextLine();
				if(isValidSSN(ssn)) {
					break;
				} else {
					System.out.println("Invalid SSN format. Please try again");
				}
			} //end of while
			Employee employee = new Employee(employeeID, ssn, person, department, salary);
			employees[i] = employee;
			
		}//end of for
		
		saveEmployeesToFile(employees);
		
		//choice of sorting
		System.out.println("Choose sorting option\n" +
		"press 1 to Sort by Employee ID\n " + "press 2 to Sort by Salary");
		int choice = input.nextInt();
		if(choice ==1) {
			sortByEmployeeID(employees);
			System.out.println("employees sorted");
		} else if (choice ==2) {
			sortBySalary(employees);
			System.out.println("salary sorted");
		}
	
		//display employee
		for(Employee emp: employees) {
			displayEmployee(emp);
		}
		
		input.close();
		
		
		
	}// end of main method
	
	/**
	 * this method validates the SSN in the correct format (999-999-9999)
	 * 
	 * 
	 * @param ssn SSN string to validate
	 * @return true if the SSN format is valid and if not then it is false
	 * */
	public static boolean isValidSSN(String ssn) {
		//999-99-9999
		
		if(ssn.length() !=11) {
			return false;
		}
		
		StringBuilder sb = new StringBuilder(ssn);
		
		if(sb.charAt(3) !='-' || sb.charAt(6) !='-') {
			return false;
		}
		
		return true;
		
	}//end of isValidSSN
	
	
	/**
	 * displays all the information of the employee
	 * 
	 * @param employee Employee object is displayed for each employee
	 * 
	 * */
	public static void displayEmployee(Employee employee) {
		System.out.println("--------------------------------------------------------");
		System.out.println("Employee ID: " + employee.getEmployeeID());
		System.out.println("SSN: " + employee.getSSN());
		System.out.println("FirstName: " + employee.getPerson().getFirstName());
		System.out.println("LastName: " + employee.getPerson().getLastName());
		System.out.println("Address: " + employee.getPerson().getAddress());
		System.out.println("Department: " + employee.getDepartment());
		System.out.println("Annual Salary: " + employee.getSalary());
		System.out.println("--------------------------------------------------------");
	}
	
	/**
	 * sorts in accending order for each array of Employee Object by their ID
	 * 
	 * @param employees array of employee objects that is to be sorted
	 * 
	 * */
	public static void sortByEmployeeID(Employee[] employees) {

		Employee temp;
		for (int i = 0; i < employees.length - 1;i++) {
			for (int j = 0; j < employees.length - 1-i;j++) {
				if (employees[j].getEmployeeID().compareTo(employees[j + 1].getEmployeeID()) > 0) {
					temp = employees[j];
					employees[j] = employees[j + 1];
					employees[j + 1] = temp;

				}
			}
		}

	}
	
	/**
	 * sorts in accending order for each array of Employee Object by their salary
	 * 
	 * @param employees array of employee objects that is to be sorted
	 * 
	 * */
	public static void sortBySalary(Employee[] employees) {

		Employee temp;
		for (int i = 0; i < employees.length - 1;i++) {
			for (int j = 0; j < employees.length - 1 -i;j++) {
				if (employees[j].getSalary() > employees[j+1].getSalary()) {
					temp = employees[j];
					employees[j] = employees[j + 1];
					employees[j + 1] = temp;

				}
			}
		}

	}
	
	/**
	 * Saves the array of eMPLOYEE objects to the file
	 * each of the employee details are written in a comma seperated format
	 * 
	 * @param employees array of employee objects to save to a file
	 * @throws IOException Signals that an I/O exception of some sort has occurred.
	 * */
	public static void saveEmployeesToFile(Employee[] employees) throws IOException {
		FileWriter fw = new FileWriter("EmployeeRecords.txt", true);
		for(Employee emp: employees) {
			fw.write(emp.getEmployeeID() +"," + emp.getSSN() + "," + emp.getPerson().getFirstName()
					+ "," + emp.getPerson().getLastName() + "," + emp.getPerson().getAddress()+"," +
					emp.getDepartment() + "," + emp.getSalary());
		}
		fw.close();
	}

}
