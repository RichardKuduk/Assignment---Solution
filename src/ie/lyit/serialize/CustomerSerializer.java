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
// Ryszard Kuduk L00120064
public class CustomerSerializer implements HandlerCustomerDAO
{
	// ArrayList to hold customer objects
	private ArrayList<Customer> customerList;
	//Creates a file with a folder name and file name to store customers
	File file = new File("C:\\RichardKudukL00120064\\customerList.txt"); 

	//HandlerCustomerDAO interfaceDAO ;
	
	
	// private final String FILENAME = "customers.ser";
	
	// Constructor
	public CustomerSerializer()
	{
		// Reads the record from the file
		customerList = readRecords();
	}
	
	@Override
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
	@Override
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
	@Override
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
	
	@Override
	public void list()
	{
		// Display details of each customer
		for(Customer customer : customerList)
		{
			System.out.println(customer);
		}
	}
	
	public ArrayList<Customer> getArray()
	{
		return customerList;
	}
	
	@Override
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
	
	@Override
	public void quit()
	{
		// Save customers to file when exiting program
		writeRecords(customerList); 
		// Inform user 
		System.out.println("Saving and clsoing app.! ");
	}
	
	
	public void writeRecords(ArrayList<Customer> customerList)
	{
		
		//	public void writeRecords(ArrayList<Customer> customerList) {
				// TODO Auto-generated method stub
				try
				{
					// If file doesnt exists
					if(!(file.exists()))
					{	// we create directory specifed in the path of the File at the begining of the program
						if(file.getParentFile().mkdir())
						{	// creating a new file in specified directory with a specified filename
							file.createNewFile();
						}
						
						else
						{	// Error catching
						    throw new IOException("Failed to create directory or directory already exists!" + file.getParent());
						}
					} 
					else
					{	// Inform that file was found and overwrite take place
						System.out.println("File exists! Overwriting!");
					}
				}
				catch(IOException ioE)
				{
					System.out.println(ioE.getMessage());
				}
				
				ObjectOutputStream os=null;
				
				try {
					// Serialize the ArrayList...
					FileOutputStream fileStream = new FileOutputStream(file);
					
					os = new ObjectOutputStream(fileStream);
					// Writes our arrayList	
					os.writeObject(customerList);
					
				}
				catch(FileNotFoundException fNFE){
					System.out.println("Cannot create file to store customers!");
				}
				catch(IOException ioE){
					System.out.println(ioE.getMessage());
				}
				finally {
					try {
						os.close();
					}
					catch(IOException ioE){
						System.out.println(ioE.getMessage());
					}
				}
	}
	public ArrayList<Customer> readRecords()
	{
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		// TODO Auto-generated method stub
		// if file found and exists
				if(file.exists())
				{
					ObjectInputStream is=null;
					
					try
					{
						// Deserialize the ArrayList...
						FileInputStream fileStream = new FileInputStream(file);
					
						is = new ObjectInputStream(fileStream);
						// read objects 
						customerList = (ArrayList<Customer>)is.readObject();	
					}
					catch(FileNotFoundException fNFE){
						System.out.println("Cannot create file to store books.");
					}
					catch(IOException ioE){
						System.out.println(ioE.getMessage());
					}
					catch(Exception e){
						System.out.println(e.getMessage());
					}
					finally
					{
						try
						{
							is.close();
						}
						catch(IOException ioE){
							System.out.println(ioE.getMessage());
						}
					}
					
					// Variable to hold the highest cutomer number
					int highestCustomerNumber = 0;
					// Check what is the highest customer number on the customer list
					for(Customer customer : customerList)
					{	
						if(customer.getNumber() >= highestCustomerNumber)
							// variable is assigned a number 1 larger than the higest on the list
							highestCustomerNumber = customer.getNumber()+1;
						
					}
					// Creating empty customers in order to assign them a correct customer numbers on the list
					// This code is needed to ensure that every time we load a customers from the file
					// and when we create a new customer he will receive number 1 bigger than the 
					// highest on the loaded list. 
					for(int i = 1 ; i < highestCustomerNumber ; i++ )
					{	// Creating empty customer to increase static variable customer number in Customer class
						Customer c = new Customer();
					}
					
				}
				else 
				{
					System.out.println("File does not exists ? ");
				}
				
		return customerList;
	
	}
		
}

