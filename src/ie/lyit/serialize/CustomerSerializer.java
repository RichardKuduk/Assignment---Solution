package ie.lyit.serialize;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ie.lyit.hotel.Customer;
import ie.lyit.hotel.HandlerCustomerDAO;
import ie.lyit.hotel.HandlerCustomerDAOImplementation;
// Ryszard Kuduk L00120064
public class CustomerSerializer 
{
	// ArrayList to hold customer objects
	private ArrayList<Customer> customerList;
	
	HandlerCustomerDAO interfaceDAO ;
	
	
	// private final String FILENAME = "customers.ser";
	
	// Constructor
	public CustomerSerializer()
	{
		interfaceDAO = new HandlerCustomerDAOImplementation();
		// Reads the record from the file
		customerList = interfaceDAO.readRecords();
	}
	
	
	public void add()
	{
		// Create a customer object
		Customer customer = new Customer();
		// Read its details from user
		if ( customer.read() )
			// adds customer to the arrayList
			customerList.add(customer);
		else
			// if not created decrease a unique number 
			customer.DecreaseCustomerNumber();
		
	}
	
	public Customer view()
	{
		//Customer tempCustomer;
		int inputValue = -1;
		
		//do
		//{
			// Get customer number from dialog box
			String inputValueAsString = JOptionPane.showInputDialog("Please enter customer number: "); 
			// Catch if number is in correct format
			try
			{
				inputValue = Integer.parseInt(inputValueAsString);
			}
			catch(Exception e)
			{// if not correct format show message box 
				System.out.println("Exception: " + e.getMessage());
				JOptionPane.showConfirmDialog(null,
						"Wrong format!",
		                "Not found",
		                JOptionPane.DEFAULT_OPTION,
		                JOptionPane.ERROR_MESSAGE);
				// Wrong format return null
				return null;

			}
			// Check if customer number exists in array list of customers
			for(Customer tempCustomer : customerList)
			{
				if(tempCustomer.getNumber() == inputValue)
				{ 	// print customer details
					System.out.println(tempCustomer);
					// return customer object
					return tempCustomer;
				}
			}
			// if number does not exists display error message 
			JOptionPane.showConfirmDialog(null,
					"Customer does not exist!",
	                "Not found",
	                JOptionPane.DEFAULT_OPTION,
	                JOptionPane.ERROR_MESSAGE);
			return null;
			
		//}while(!(inputValue - 1 < customerList.size()));
		
		
		
		
	}
	public void edit()
	{
		/*
		int inputValue = 1;
		
		do
		{
			String inputValueAsString = JOptionPane.showInputDialog("Please enter customer number to edit: "); 

			try
			{
				inputValue = Integer.parseInt(inputValueAsString);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
			
		}while(!(inputValue - 1 < customerList.size()));
		
		Customer c = customerList.get(inputValue-1);
		*/
		
		// Call view method to get customer object
		Customer tempCustomer = view();
		// if customer does not exists
		if(tempCustomer == null)
			// end procedure
			return;
		// Get index of customer object
		int index = customerList.indexOf(tempCustomer);
		// call Customer class edit procedure for "this" customer
		tempCustomer = tempCustomer.edit();
		// set customer on the array list -> update it
		customerList.set(index, tempCustomer);
		// Show confirmation dialog box 
		JOptionPane.showConfirmDialog(null,
				"Customer details has been changed!",
                "EDIT",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
		
	}
	public void list()
	{
		// Display details of each customer
		for(Customer customer : customerList)
		{
			System.out.println(customer);
		}
	}
	
	public void delete()
	{
		/*
		int inputValue = 1;
		
		do
		{
			String inputValueAsString = JOptionPane.showInputDialog("Please enter customer number to delete: "); 

			try
			{
				inputValue = Integer.parseInt(inputValueAsString);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
			
		}while(!(inputValue - 1 < customerList.size()));
		
		*/	
		
		/*
	  	for(Customer temp : customerList)
	 
		{
			if(temp.getNumber() == inputValue)
			{
				customerList.remove(temp);
				break;
			}
		}
		customerList.remove(customerList.get(inputValue-1));
		*/
		
		
		// Call view method to get customer object
		Customer tempCustomer = view();
		// if customer does not exists
		if(tempCustomer == null)
			// end procedure
			return;
			
		// Assign variable an option from JOptionPane
		int confirmation = JOptionPane.showConfirmDialog(
				null,
				"Are You sure You want to remove this customer?",
				"DELETE CUSTOMER",
				JOptionPane.OK_CANCEL_OPTION);
		
		// if it ok button clicked
	    if(confirmation == JOptionPane.OK_OPTION)
	    {	// Remove this customer
	    	customerList.remove(tempCustomer);
	    }
	    
	  		
		
	}
	public void quit()
	{
		// Save customers to file when exiting program
		interfaceDAO.writeRecords(customerList); 
		// Inform user 
		System.out.println("Saving and clsoing app.! ");
	}
	

		
}

