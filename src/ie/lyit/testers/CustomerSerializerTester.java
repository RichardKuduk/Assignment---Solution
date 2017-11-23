package ie.lyit.testers;
import ie.lyit.hotel.*;
import javax.swing.JOptionPane;

import ie.lyit.serialize.CustomerSerializer;

public class CustomerSerializerTester 
{// Ryszard Kuduk L00120064


	public static void main(String[] args)
	{
		// Variable to hold user choice
		int choice;
		// Creating object of the CustomerSerializer class
		CustomerSerializer custList = new CustomerSerializer();
		// Reads the record from the file
		//custList.readRecordsFromFile();
		// Menu display measse as []
		Object message []=
			{
					"1. ADD" ,
					"2. VIEW",
					"3. LIST",
					"4. EDIT" ,
					"5. DELETE",
					"6. QUIT and SAVE"
			};
		
		do
		{
			do
			{	 // choice reset
				 choice = -1;
				 // Variable to hold user input
				 String choiceAsString = JOptionPane.showInputDialog(null, message );
				 // Try catch block to ensure that proper input was typed in
				 try
				 { 	// convert choice as string to an int
					 choice = Integer.parseInt(choiceAsString);
				 }
				 catch(NumberFormatException e)
				 {	 // if empty quit
					 if(choiceAsString == null)
					 {// assign choice number 6 (Quit option)
						 choice = 6;
					 }
					 else
					 {	// if not a number value display error msg
						 JOptionPane.showMessageDialog(null, "Wrong format", "Error", JOptionPane.ERROR_MESSAGE);
						 // set choice out of range 
						 choice = -1;
					 }
				 }
				 
				 if(choice > 6 || choice < 1)
				 {
					 // choice out of range
					 choice = -1;
					 // Display error msg
					 JOptionPane.showMessageDialog(null, "Out of range", "Error", JOptionPane.ERROR_MESSAGE);
				 }
			}while(choice == -1); // do it while correct option choosen
			
			// Switch 
			switch(choice)
			{
			case 1: 
				// Call add in CustomerSerializer
				custList.add();
				break;
			case 2: 
				// Call view in CustomerSerializer
				custList.view();
				break;
			case 3:
				// Call list in CustomerSerializer
				custList.list();
				break;
			case 4: 
				custList.edit();
				break;
			case 5: 
				custList.delete();
				break;
			case 6: 
				custList.quit();
				break;
			
			
			}
			// do all of that till option = quit
		} while (choice != 6);
		
		
	}
		

}
