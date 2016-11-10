package date;


import java.util.Random;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class OOSCDateTest {

	private static final int LIMITE_YEAR_TESTED = 4000;

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

	
	@Test
	public void numberOfDaysInYear(){
		for(int y=0; y<LIMITE_YEAR_TESTED; ++y){
			int n = OOSCDate.numberOfDaysInYear(y);
			if(OOSCDate.isLeapYear(y)){
				assertEquals(n, 366);
			}
			else{
				assertEquals(n, 365);
			}
		}
	}
	
	
	@Test 
	public void toDateInterface(){
		int numberOfDays = 3;
		OOSCDate test = (OOSCDate) OOSCDate.toDateInterface(numberOfDays);
		System.out.println(test.toString());
		OOSCDate toCorrespond = new OOSCDate(0, Month.JANUARY, 3);
		
		assert(test.equals(toCorrespond));
		
	}
	
	@Test
	public void addDaysTest(){
		Random r = new Random();
		for(int i= 0; i<2; i++){
			
			int y = r.nextInt(5000);
			Month m = Month.month(r.nextInt(12) + 1);
			int d = r.nextInt(m.getNumberOfDays(y)) + 1;
			int numberDaysToAdd = Math.abs(r.nextInt());
			
			OOSCDate testOOSC = new OOSCDate(y, m, d);
			testOOSC.addDays(0);System.out.println("ddddddd");
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar c = Calendar.getInstance();
			c.set(y, m.getValue() -1, d);
			c.add(Calendar.DATE, numberDaysToAdd);
			System.out.println("ddddddd");
			Date javaDate = c.getTime();
			OOSCDate testJava  = new OOSCDate(javaDate.getYear(), javaDate.getMonth() + 1, javaDate.getDate());
			
			System.out.println("ddddddd");
			System.out.println(testJava.toString());
			System.out.println(testOOSC.toString());
			
			
			assert(testJava.equals(testOOSC));
					
			
		}
	}
}
