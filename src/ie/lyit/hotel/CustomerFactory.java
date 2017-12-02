package ie.lyit.hotel;

import ie.lyit.serialize.CustomerSerializer;

public class CustomerFactory  
{// Richard Kuduk - L00120064
	
	   //public static final String[] names = {"Richard"};
		
		// Method receives a name and the Array list of customers
	   public static Person getFullName(String name, CustomerSerializer custList)
	   {
		   // Loops thorugh the customer array list
	      for (int i = 0; i < custList.getArray().size() ; i++)
	      {
	    	 // checks if name at index i is the same as the name which was send
	         if (custList.getArray().get(i).getFullName().equalsIgnoreCase(name))
	         {// if found it returns that customer
	            return custList.getArray().get(i);
	         }
	      }
	      // if not it return object of the NullCustomer class 
	      // containing method to print Not available in database
	      return new NullCustomer();
	   }
}