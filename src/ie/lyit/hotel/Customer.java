package ie.lyit.hotel;
import java.awt.Color;
import java.io.*;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
//Ryszard Kuduk L00120064

public class Customer extends Person implements Serializable
{// INHERITANCE - Customer IS-A Person
	
	
	private static final long serialVersionUID = 1L;
	// Customer has name, address, & phoneNumber from Person
	private String emailAddress;    // AND emailAddress
	private int number = 0;			    // AND number

	private static int nextNumber=1;// static for unique number - starts off at 1
	
	// Default Constructor
	// Called when object is created like this ==> 
	//   Customer cObj = new Customer();	
	public Customer(){
		super();			// NOTE:Don't need to call super() explicitly.
		emailAddress=null;
		// Set number to static nextNumber before incrementing nextNumber
		number=nextNumber++;
	}
	
	// Initialization Constructor
	// Called when object is created like this ==>
	// Customer cObj = new Customer("Mr","Joe","Doe","12 Hi Rd,Letterkenny",
	//                              "0871234567","joe@hotmail.com");
	public Customer(String t, String fN, String sn, String address, 
			        String pNo, String email){
		// Call super class constructor - Passing parameters required by Person ONLY!
		super(t, fN, sn, address, pNo);
		// And then initialise Customers own instance variables
		emailAddress=email;
		// And finally set number to static nextNumber before incrementing nextNumber
		number=nextNumber++;
	}

	// OVERRIDING the Person toString() method!
	// Calling Persons toString() method, and adding additional bits
	@Override
	public String toString(){
		return super.toString() + "," + emailAddress + "," + number;
	}

	// equals() method
	// ==> Called when comparing an object with another object, 
	//     e.g. - if(c1.equals(c2))				
	// ==> Probably sufficient to compare customer numbers as they're unique
	@Override
	public boolean equals(Object obj){
		Customer cObject;
		if (obj instanceof Customer)
		   cObject = (Customer)obj;
		else
		   return false;
		   
	    return(this.number==cObject.number);
	}

	// set() and get() methods
	public void setEmailAddress(String emailAddress){
		this.emailAddress=emailAddress;
	}
	public String getEmailAddress(){
		return this.emailAddress;
	}	
	// You shouldn't be able to setNumber() as it is unique, 
	// so don't provide a setNumber() method
	public int getNumber(){
		return number;
	}	
	
	// Read in customer details
	 public boolean read()
	   {
		  // Creates JTextFields to be displayed to the user
	      JTextField txtTitle = new JTextField();
	      JTextField txtFirstName = new JTextField();
	      JTextField txtLastName = new JTextField();
	      JTextField txtAdress = new JTextField();
	      JTextField txtPhoneNumber = new JTextField();
	      JTextField txtEmailAdress = new JTextField();
	      JTextField txtEmployeeNumber = new JTextField();
	      // Cant set custNumber by user - just displays it
	      txtEmployeeNumber.setText(String.valueOf(number));
	      // dont allow to edit textbox
	      txtEmployeeNumber.setEditable(false);
	      
	      // message fot the JOptionPane
	      
	      Object[] message = 
	      {
	          "Title:", txtTitle,
	          "First name:", txtFirstName,
	          "Last name: ", txtLastName,
	          "Address:", txtAdress,
	          "Phone number:", txtPhoneNumber,
	          "Email:", txtEmailAdress,
	          "Number: ", txtEmployeeNumber
	      };
	      
	      // variable to check the JTextFields
	      boolean isEmpty;
	      // Variable for JOptionPane return 
	      int option;
	      // loop 
	      do
	      {
	    	  // set to true
	    	  isEmpty = true;
	    	  
	    	  // assign button clicked
	    	  option = JOptionPane.showConfirmDialog(null, message, "Enter customer details:", JOptionPane.OK_CANCEL_OPTION);

   	          // Check all text fields if not empty       
			  if(!txtTitle.getText().isEmpty() && !txtFirstName.getText().isEmpty() && !txtLastName.getText().isEmpty() &&
			    	 !txtAdress.getText().isEmpty() && !txtPhoneNumber.getText().isEmpty() && !txtEmailAdress.getText().isEmpty())
			  {
				  // flag to false all fields have content
				  isEmpty = false;
			    	 	    	
			  }
			  // if empty mark and colour empty jtexfields 
			  else
		      {
				  if(txtTitle.getText().isEmpty())
					  txtTitle.setBackground(Color.YELLOW);
				  else
					  txtTitle.setBackground(Color.WHITE);
				  if(txtFirstName.getText().isEmpty())
					  txtFirstName.setBackground(Color.YELLOW);
				  else
					  txtFirstName.setBackground(Color.WHITE);
				  if(txtLastName.getText().isEmpty())
					  txtLastName.setBackground(Color.YELLOW);
				  else
					  txtLastName.setBackground(Color.WHITE);
				  if(txtAdress.getText().isEmpty())
					  txtAdress.setBackground(Color.YELLOW);
				  else
					  txtAdress.setBackground(Color.WHITE);
				  if(txtPhoneNumber.getText().isEmpty())
					  txtPhoneNumber.setBackground(Color.YELLOW);
				  else
					  txtPhoneNumber.setBackground(Color.WHITE);
				  if(txtEmailAdress.getText().isEmpty())
					  txtEmailAdress.setBackground(Color.YELLOW);
				  else
					  txtEmailAdress.setBackground(Color.WHITE);
			  }
		    	  
			 // do while boolean variable is not flagged to false and option is not cancel
	      }while(isEmpty && !(option == JOptionPane.CANCEL_OPTION));
	      // if ok button clicked and JTextFields are not empty
	      if (option == JOptionPane.OK_OPTION && !isEmpty)
	      {
	    	  // Set the details of customers from textfields
	    	  this.name.setTitle(txtTitle.getText());
	    	  this.name.setFirstName(txtFirstName.getText());
	    	  this.name.setSurname(txtLastName.getText());
	    	  setAddress(txtAdress.getText());
	    	  setPhoneNumber(txtPhoneNumber.getText());
	    	  setEmailAddress(txtEmailAdress.getText());
	    	  // confirmation succesfull
	    	  return true;
	    	  
	      }
	      // customer not added
	      return false;
	      
		}
	 
	 
	 public Customer edit()
	 {		// Fill the JtextFields with current object cusotmer 
		    JTextField txtTitle = new JTextField(this.name.getTitle());
		    JTextField txtFirstName = new JTextField(this.name.getFirstName());
		    JTextField txtLastName = new JTextField(this.name.getSurname());
		    JTextField txtAdress = new JTextField(this.getAddress());
		    JTextField txtPhoneNumber = new JTextField(this.getPhoneNumber());
		    JTextField txtEmailAdress = new JTextField(this.getEmailAddress());
		    JTextField txtEmployeeNumber = new JTextField(this.getNumber());
		    txtEmployeeNumber.setEditable(false);
		    txtEmployeeNumber.setText(String.valueOf(this.number));
		    // Message for JOptionPane  
	        Object[] message = 
			{
				"Title:", txtTitle,
				"First name:", txtFirstName,
				"Last name: ", txtLastName,
				"Address:", txtAdress,
				"Phone number:", txtPhoneNumber,
				"Email:", txtEmailAdress,
				"Number: ", txtEmployeeNumber
			};
	        // Open dialog with filled JTextFields
	        int option = JOptionPane.showConfirmDialog(null, message, "Edit customer details:", JOptionPane.OK_CANCEL_OPTION);
	        
	        // if option is ok and all fields are filled out
	        if (option == JOptionPane.OK_OPTION && !txtTitle.getText().isEmpty() && !txtFirstName.getText().isEmpty() && !txtLastName.getText().isEmpty() &&
			    	 !txtAdress.getText().isEmpty() && !txtPhoneNumber.getText().isEmpty() && !txtEmailAdress.getText().isEmpty())
			{
	        	// set the new customer details
				this.name.setTitle(txtTitle.getText());
				this.name.setFirstName(txtFirstName.getText());
				this.name.setSurname(txtLastName.getText());
				this.setAddress(txtAdress.getText());
				this.setPhoneNumber(txtPhoneNumber.getText());
				this.setEmailAddress(txtEmailAdress.getText());
			    			    	  
			} 
		      // return this customer object
		      return this;
	 }
	 
	// Method to Decrease customer number if customer not added during creation in add() method 
	public void DecreaseCustomerNumber()
	{
		nextNumber--;
	}
	
	@Override
	public String getFullName()
	{
		return getName().getFirstName() + " " + getName().getSurname();
	}

	
}