package firstPackage;
/**
 * Creating a class named Person that contains fields for a person’s first name, last name and address.
 * 
 * 
 * */
public class Person {

	
	private String firstName;
	private String lastName;
	private String address;
	
	//constructor
	/**
	 * Person constructor that accepts all the data fields
	 * 
	 * @param firstName employee's firstname
	 * @param lastName employee's lastname
	 * @param address employee's address
	 * */
	public Person(String firstName, String lastName, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;

	}// end of constructor
	
	
	//get methods
	 /**
     * Returns the first name of the person.
     *
     * @return the first name.
     */
	public String getFirstName() {
		
		return firstName;
	}
	
	/**
     * Returns the last name of the person.
     *
     * @return the last name.
     */
public String getLastName() {
		
		return lastName;
	}
/**
 * Returns the address of the person.
 *
 * @return the address.
 */

public String getAddress() {
	
	return address;
}
	
	
}//end of person class
