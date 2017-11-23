package ie.lyit.testers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ie.lyit.hotel.Date;
import ie.lyit.hotel.Employee;

public class EmployeeTest {

	private Employee e1;
	
	@Before
	public void setUp() throws Exception {
		e1 = new Employee("Ms","Lisa","Simpson","Letterkenny", "0860987653",20,12,1992,new Date(1,1,2009),25000);
	}

	@Test
	public void testGetDateProbationEnds() {
		assertEquals(e1.getDateProbationEnds(30), new Date(31,1,2009));
		}

}
