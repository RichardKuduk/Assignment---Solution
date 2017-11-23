package ie.lyit.hotel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class HandlerCustomerDAOImplementation implements HandlerCustomerDAO
{
	//Creates a file with a folder name and file name to store customers
		File file = new File("C:\\RichardKudukL00120064\\customerList.txt"); 
	@Override
	public void writeRecords(ArrayList<Customer> customerList) {
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

	@Override
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
