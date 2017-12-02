package ie.lyit.testers;

import ie.lyit.hotel.CustomerFactory;
import ie.lyit.hotel.Person;
import ie.lyit.serialize.CustomerSerializer;

public class NullPatternDemo {

	public static void main(String[] args)
	{
		CustomerSerializer custList = new CustomerSerializer();

		//custList.list();
		
		// TODO Auto-generated method stub
		// Object of the person class created using Customer Factort 
		
		// This object Richard Kuduk is on the list
		// When You first time run the program you will first need to 
		// add Richard Kuduk in the main program to populate the list(or differnt person)
		// from below to test the functionality
		Person customer1 = CustomerFactory.getFullName("Richard Kuduk" , custList);
		// Those to should display Not Available
		Person customer2 = CustomerFactory.getFullName("Bob Marlay", custList);
		Person customer3 = CustomerFactory.getFullName("Julie Roberts", custList);

	      System.out.println("Customers");
	      // Return Richard Kuduk because it is on the list
	      System.out.println(customer1.getFullName());
	      // Return NullCustomer object
	      System.out.println(customer2.getFullName());
	      System.out.println(customer3.getFullName());
	}

}
