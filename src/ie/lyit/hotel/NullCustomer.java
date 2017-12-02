package ie.lyit.hotel;

public class NullCustomer extends Person
{// Richard Kuduk - L00120064
	
	// overriden method to print customer not on the list 
	@Override
	public String getFullName()
	{
		return "Not Available in Customer Database";
	}
	
}
