package ie.lyit.hotel;

import java.util.ArrayList;

public interface HandlerCustomerDAO
{
	public void writeRecords(ArrayList<Customer> customerList);
	public ArrayList<Customer> readRecords();
}
