package date;


import java.util.Random;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
			int n = DateInterface.numberOfDaysInYear(y);
			if(DateInterface.isLeapYear(y)){
				assertEquals(n, 366);
			}
			else{
				assertEquals(n, 365);
			}
		}
	}
	
	
//	@Test 
//	public void toDateInterface(){
//		int numberOfDays = 3;
//		OOSCDate test = (OOSCDate) OOSCDate.toDateInterface(numberOfDays);
//		System.out.println(test.toString());
//		OOSCDate toCorrespond = new OOSCDate(0, Month.JANUARY, 3);
//		
//		assert(test.equals(toCorrespond));
//		
//	}
//	
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
	
	
	@Test
	public void setDate(){
		
		OOSCDate date1 = new OOSCDate(2016,11,10);
		OOSCDate date2 = new OOSCDate(2016,11,10);
		
		date1.setDate(2016,11,12);
		date2.setDate(2016,11,12);
		
		//assertEquals(date1,date2);
		assertTrue(date1.equals(date2));
	}
	
	@Test
	public void setYear(){
		
		OOSCDate date = new OOSCDate(2016,11,10);
		
		date.setYear(2000);
		
		assertEquals(2000,date.getYear());
	
	}
	
	@Test
	public void setMonth(){

		OOSCDate date = new OOSCDate(2016,11,10);
		
		date.setMonth(12);
		
		assertEquals(12,date.getMonth());
		
	}
	
	@Test
	public void setDay(){
		
		OOSCDate date = new OOSCDate(2016,11,10);
		
		date.setDay(29);
		
		assertEquals(29,date.getDay());
	}
	 
	@Test
	public void getYear(){
		
		OOSCDate date = new OOSCDate(2016,11,10);
		
		assertEquals(2016,date.getYear());
		
	}
	
	@Test
	public void getMonth(){
		
		OOSCDate date = new OOSCDate(2016,11,10);
		
		assertEquals(11,date.getMonth());
		
	}
	
	@Test
	public void getDay(){
		
		OOSCDate date = new OOSCDate(2016,11,10);
		
		assertEquals(10,date.getDay());
		
	}
	 
	@Test
	public void addDays(){
		 
		OOSCDate date = new OOSCDate(2016,2,8);
			
		date.addDays(4);
		 	
		assertEquals(12,date.getDay());
			
	 }
	
	@Test
	public void addMonths(){
	 
		OOSCDate date = new OOSCDate(2016,2,10);
			
		date.addMonths(1);
		 	
		assertEquals(3,date.getMonth());
			
	 }
	 
	 @Test
	 public void addYears(){
		 
	 	OOSCDate date = new OOSCDate(2016,11,10);
		
	 	date.addYears(2);
	 	
		assertEquals(2018,date.getYear());
		
	 }
	 
	 @Test
	 public void removeDays(){
	
		 OOSCDate date = new OOSCDate(2016,11,10);
			
		 date.removeDays(1);
		 	
		 assertEquals(9,date.getDay());
			 
	 }
	 
	 @Test
	 public void removeMonths(){
		
		OOSCDate date = new OOSCDate(2016,6,10);
				
		date.removeMonths(3);
		
		assertEquals(3,date.getMonth());
					 
		 
	 }
	 
	 @Test
	 public void removeYears(){
	
	 	OOSCDate date = new OOSCDate(2016,11,10);
		
	 	date.removeYears(2);
	 	
		assertEquals(2014,date.getYear());
	
	 }
	 
	 @Test
	 public void daysBetween(){
		 
		OOSCDate date1 = new OOSCDate(2016,11,10);
		OOSCDate date2 = new OOSCDate(2016,11,9);
		
		assertEquals(1,date1.daysBetween(date1, date2));
		 
	 }
	 
	 @Test
	 public void monthBetween(){
		
		OOSCDate date1 = new OOSCDate(2016,10,10);
		OOSCDate date2 = new OOSCDate(2016,12,9);
			
		assertEquals(2,date1.monthBetween(date1, date2));
			 		 
	 }
	 
	 @Test
	 public void timeBetween(){
		
		OOSCDate date1 = new OOSCDate(2016,11,12);
		OOSCDate date2 = new OOSCDate(2016,11,10);
			
		assertEquals(2,date1.timeBetween(2, date2));
			 
	 }
	 
	 @Test
	 public void synchWithUTCTimeserver(){
		 
		 OOSCDate date1 = new OOSCDate(1,1,1);
		 
		 try {
				// get URL content
				URL url = new URL("http://www.timeapi.org/utc/now");
				URLConnection conn = url.openConnection();

				// open the stream and put it into BufferedReader
				BufferedReader br = new BufferedReader(
	                               new InputStreamReader(conn.getInputStream()));

				String inputLine = br.readLine();
				if(inputLine == null){
					// TODO: Throw errors
				}
				else{
					String[] format = inputLine.split("T");
					if(format.length < 3){
						
						String[] parsedDate = format[0].split("-");
						
						date1.setDate(Integer.parseInt(parsedDate[0]),
								Integer.parseInt(parsedDate[1]),
								Integer.parseInt(parsedDate[2]));
					
					}
					else{
						String[] oosc = format[0].split("T");
						// TODO: Deal with erros
					}					
				}
				br.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 
/*		 OOSCDate date2 = new OOSCDate(1,1,1);
		 date2.synchWithUTCTimeserver();
		 
		 assertTrue(date2.equals(date1));*/
		 
	 }
	 

	
}
