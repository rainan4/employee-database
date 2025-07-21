package firstPackage;
/**
 * Create a class named Employee that contains fields for an employee ID, 
 * Social Security number, personal information (Person object), department (Departments type) and annual salary. 
 * 
 * 
 * 
 * */
public class Employee {

		private String employeeID;
		private String ssn;
		Person person;
		Departments department;
		private double salary;
		
		
		//construtor
		/**
		 * employee constructor that accepts all the data fields
		 * 
		 * @param employeeID employee's ID
		 * @param ssn Social security number of the employee
		 * @param person contains person details from Person class
		 * @param department contains department details from Department enumeration
		 * @param salary  annual salary of the employee
		 * */
		public Employee(String employeeID,String ssn,Person person,Departments department,double salary) {
			this.employeeID = employeeID;
			this.ssn = ssn;
			this.person = person;
			this.department=department;
			this.salary =salary;
			
		} //end of construcotr
		
		
		//get and set methods
		/**
	     * Returns the employee ID.
	     *
	     * @return the employee ID.
	     */
		public String getEmployeeID () {
			return employeeID;
		}
		 /**
	     * Sets the employee ID.
	     *
	     * @param employeeID the employee ID to set.
	     */
		public void setEmployeeID(String employeeID) {
			this.employeeID = employeeID;
		}
		
		/**
	     * Returns the SSN.
	     *
	     * @return the SSN.
	     */
		public String getSSN () {
			return ssn;
		}
		 /**
	     * Sets the SSN.
	     *
	     * @param ssn the SSN to set.
	     */
		public void setSSN(String ssn) {
		this.ssn = ssn;
		}
		
		
		/**
		 * returns the Person class containing employee;s personal info
		 *  @return person object
		 * */
		public Person getPerson() {
			return person;	
		}
		/**
		 * sets the Person class containing employee;s personal info
		 *  @param Person person the person object is set
		 * */
		public void setPerson (Person person) {
			this.person = person;
		}
		
		/**
		 * returns the Department enumeration containing departments
		 *  @return person object
		 * */
		public Departments getDepartment() {
			return department;	
		}
		
		/**
		 * sets the Department enumeration containing departments
		 *  @param Departments department object is set
		 * */
		public void setDepartment (Departments department) {
			this.department=department;
		}
		 /**
	     * Returns the annual salary of the employee.
	     *
	     * @return the salary.
	     */
		public double getSalary() {
			return salary;
		}
		  /**
	     * Sets the annual salary of the employee.
	     *
	     * @param salary the salary to set.
	     */
		public void setSalary(double salary) {
			this.salary =salary;
		}
}
