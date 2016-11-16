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

	// @Test
	// public void test() {
	// fail("Not yet implemented");
	// }

	@Test
	public void numberOfDaysInYear() {
		for (int y = 0; y < LIMITE_YEAR_TESTED; ++y) {
			int n = DateInterface.numberOfDaysInYear(y);
			if (DateInterface.isLeapYear(y)) {
				assertEquals(n, 366);
			} else {
				assertEquals(n, 365);
			}
		}
	}
	
	@Test
	public void isLeapYear(){
		
		Boolean leapYear;
		
		for (int y = 0; y < LIMITE_YEAR_TESTED; ++y) {
			if((y %4 == 0) && (y % 100 == 0) && (y % 400 == 0)){
				leapYear = true;
			}else{
				leapYear = false;
			}
				assertEquals(leapYear,DateInterface.isLeapYear(y));
		}
	}

	// @Test
	// public void toDateInterface(){
	// int numberOfDays = 3;
	// OOSCDate test = (OOSCDate) OOSCDate.toDateInterface(numberOfDays);
	// System.out.println(test.toString());
	// OOSCDate toCorrespond = new OOSCDate(0, Month.JANUARY, 3);
	//
	// assert(test.equals(toCorrespond));
	//
	// }
	//

	@Test
	public void addDaysTest() {
		Random r = new Random();
		for (int i = 0; i < 2; i++) {

			int y = r.nextInt(5000);
			Month m = Month.month(r.nextInt(12) + 1);
			int d = r.nextInt(m.getNumberOfDays(y)) + 1;
			int numberDaysToAdd = Math.abs(r.nextInt());

			OOSCDate testOOSC = new OOSCDate(y, m, d);
			testOOSC.addDays(0);
			System.out.println("ddddddd");
			// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar c = Calendar.getInstance();
			c.set(y, m.getValue() - 1, d);
			c.add(Calendar.DATE, numberDaysToAdd);
			System.out.println("ddddddd");
			Date javaDate = c.getTime();
			OOSCDate testJava = new OOSCDate(javaDate.getYear(), javaDate.getMonth() + 1, javaDate.getDate());

			System.out.println("ddddddd");
			System.out.println(testJava.toString());
			System.out.println(testOOSC.toString());

			assert (testJava.equals(testOOSC));

		}
	}

	@Test
	public void setDate() {

		OOSCDate date = new OOSCDate();
		OOSCDate dateTocompare;
		
		
		for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
			for(Month m : Month.values()){
				for(int d=1; d<=m.getNumberOfDays(y); ++d){
					
					dateTocompare = new OOSCDate(y,m,d);
					date.setDate(y,m,d);
					
					assertEquals(dateTocompare,date);
				}
			}
		}
	}
		

	@Test
	public void setYear() {
		//for(int y1 = 0; y1<LIMITE_YEAR_TESTED; ++y1){
			
		Random r = new Random();
		int y1 = r.nextInt(5000);
		
		for(int y2 = 0; y2<LIMITE_YEAR_TESTED; ++y2){
				for (Month m : Month.values()){
					for(int d=1; d<=m.getNumberOfDays(y1); ++d){
						OOSCDate date = new OOSCDate(y1, m, d);
						date.setYear(y2);
						
						if(m.equals(Month.FEBRUARY) && d == 29 && !DateInterface.isLeapYear(y2)){
							OOSCDate dateToCompare = new OOSCDate(y2, m, 28);
							assertTrue(dateToCompare.equals(date));
						}
						else{
							OOSCDate dateToCompare = new OOSCDate(y2, m, d);
							assertTrue(dateToCompare.equals(date));
						}
					}
				}
			}
		//}
	}

	@Test
	public void setMonth() {

		int n;
		
		for(int y = 1; y<LIMITE_YEAR_TESTED; ++y){
			for (Month m1 : Month.values()){
				for(Month m2 : Month.values()){
				
					if(m1.getNumberOfDays(y) < m2.getNumberOfDays(y)){
						n = m1.getNumberOfDays(y);
					}else{
						n = m2.getNumberOfDays(y);
					}
					
					for(int d=1; d < n ;++d){
						
						OOSCDate date = new OOSCDate(y,m1,d);
						date.setMonth(m2.getValue());
						
						OOSCDate dateToCompare = new OOSCDate(y,m2,d); 
						
						assertTrue(date.equals(dateToCompare));
					}
				}
			}
		}
	}

	@Test
	public void setDay() {

		OOSCDate date,dateToCompare;
		
		for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
			for(Month m : Month.values()){
				for(int d1=1; d1<=m.getNumberOfDays(y); ++d1){
					for(int d2=1; d2<=m.getNumberOfDays(y); ++d2){
							
						date = new OOSCDate(y,m,d1);
						date.setDay(d2);
						
						dateToCompare = new OOSCDate(y,m,d2); 
						
						assertTrue(date.equals(dateToCompare));
					}
				}
			}
		}
	}


	@Test
	public void getYear() {

		OOSCDate date;
		
		for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
			for(Month m : Month.values()){
				for(int d=1; d<=m.getNumberOfDays(y); ++d){
					date = new OOSCDate(y,m,d);
					
					assertEquals(y,date.getYear());
				}
			}
		}
	}

	@Test
	public void getMonth() {
		
		OOSCDate date;
		
		for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
			for(Month m : Month.values()){
				for(int d=1; d<=m.getNumberOfDays(y); ++d){
					date = new OOSCDate(y,m,d);
					
					assertEquals(m.getValue(),date.getMonth());
				}
			}
		}	
	}

	@Test
	public void getDay() {

		OOSCDate date;
		
		for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
			for(Month m : Month.values()){
				for(int d=1; d<=m.getNumberOfDays(y); ++d){
		
					date = new OOSCDate(y,m,d);
		
					assertEquals(d,date.getDay());
				}
			}
		}	
	}

	@Test
	public void addDays() {

		OOSCDate date = new OOSCDate(2016, 2, 8);

		date.addDays(4);

		assertEquals(12, date.getDay());

	}

	@Test
	public void addMonths() {

		Random r = new Random();
		int monthsToadd = r.nextInt(5000);
		int addedyears;
		int addedmonths;
		
		OOSCDate date,dateTocompare;
		
		for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
			for(Month m : Month.values()){
				for(int d=1; d<=m.getNumberOfDays(y); ++d){
		
					date = new OOSCDate(y,m,d);
					dateTocompare = new OOSCDate(y,m,d);
					
					monthsToadd = r.nextInt(5000)+ date.getMonth();
					addedyears = monthsToadd / 12;
					addedmonths = monthsToadd % 12;
					
					date.setYear(y + addedyears);
					date.setMonth(m.getValue() + addedmonths);
					
					dateTocompare.addMonths(monthsToadd); 
					
					assertTrue(date.equals(dateTocompare));
					
				}
			}
		}	

	}

	@Test
	public void addYears() {
		
		Random r = new Random();
		int yearsToadd = r.nextInt(5000);
		
		OOSCDate date;
		
		for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
			for(Month m : Month.values()){
				for(int d=1; d<=m.getNumberOfDays(y); ++d){
		
					yearsToadd = r.nextInt(5000);
					
					date = new OOSCDate(y,m,d);
					date.addYears(yearsToadd);
					
					assertEquals(yearsToadd + y,date.getDay());
				}
			}
		}	
	}

	@Test
	public void removeDays() {

		Random r = new Random();
		int daysToremove;
		int removedYears = 0 ,removedMonths = 0,removedDays;
		int max;
		
		OOSCDate date,dateTocompare;
		
		for(int y = 1; y<LIMITE_YEAR_TESTED; ++y){
			for(Month m : Month.values()){
				for(int d=1; d<=m.getNumberOfDays(y); ++d){
					
					date = new OOSCDate(y,m,d);
					dateTocompare = new OOSCDate(y,m,d);
					
					max = date.toNumberOfDays();
					daysToremove = r.nextInt(max)+1;
					removedDays = daysToremove;
					
					while(removedDays >= DateInterface.numberOfDaysInYear(y)){
						removedDays -= DateInterface.numberOfDaysInYear(y);
						removedYears++;
					}

					while(removedDays >= m.getNumberOfDays(y)){
						removedDays -= m.getNumberOfDays(y);
						removedMonths++;
					}
					
					dateTocompare.setDate(
							y-removedYears, 
							m.getValue()- removedMonths,
							d-removedDays);
					
					date.removeDays(daysToremove);
					
					assertTrue(date.equals(dateTocompare));
					
				}
			}
		}

		
	}

	@Test
	public void removeMonths() {

		Random r = new Random();
		int monthsToremove;
		int removedYears;
		int removedMonths;
		
		OOSCDate date,dateTocompare;
		
		for(int y = 1; y<LIMITE_YEAR_TESTED; ++y){
			for(Month m : Month.values()){
				for(int d=1; d<=m.getNumberOfDays(y); ++d){
					
					date = new OOSCDate(y,m,d);
					dateTocompare = new OOSCDate(y,m,d);
					
					monthsToremove = r.nextInt(date.toNumberOfMonths()) + date.getMonth();
					
					removedYears = monthsToremove / 12;
					removedMonths = monthsToremove % 12;
					
					date.setYear(y - removedYears);
					date.setMonth(m.getValue() - removedMonths);
					
					dateTocompare.removeMonths(monthsToremove);
					 
					assertTrue(date.equals(dateTocompare));
				
				}
			}
		}


	}

	@Test
	public void removeYears() {

		Random r = new Random();
		int yearsToremove;
		
		OOSCDate date;
		
		for(int y = 1; y<LIMITE_YEAR_TESTED; ++y){
			for(Month m : Month.values()){
				for(int d=1; d<=m.getNumberOfDays(y); ++d){
					
					yearsToremove = r.nextInt(y);
					
					date = new OOSCDate(y,m,d);
					
					date.removeYears(yearsToremove);
					
					assertEquals(y-yearsToremove,date.getYear());
				}
			}
		}

	}

	@Test
	public void daysBetween() {

	for (int i=0;i<100;++i){
		
			Random r = new Random();
			
			int y1 = r.nextInt(5000);
			Month m1 = Month.month(r.nextInt(12) + 1);
			int d1 = r.nextInt(m1.getNumberOfDays(y1)) + 1;
		
			OOSCDate date = new OOSCDate(y1,m1,d1);
			
			int y2 = r.nextInt(y1);
			
			Month m2 ;
			if(y1 == y2){
				 m2 = Month.month(m1.getValue());
			}else{
				 m2 = Month.month(r.nextInt(12) + 1);	
			}
			
			int d2 ;
			if(y1 == y2 & m1 == m2){
				d2 = r.nextInt(d1) + 1;
			}else{
				d2 = r.nextInt(m2.getNumberOfDays(y2)) + 1;
			}
			
			OOSCDate dateTocompare = new OOSCDate(y2,m2,d2);
			
			int daysBetween = date.toNumberOfDays() - dateTocompare.toNumberOfDays();
			
			assertEquals(DateInterface.daysBetween(date, dateTocompare), daysBetween);
			
		}
	}

	@Test
	public void monthBetween() {
		
		for(int i = 0 ; i < 100 ; ++i){
			
			Random r = new Random();
			
			int y1 = r.nextInt(5000);
			
			Month m1 = Month.month(r.nextInt(12) + 1);
			int d1 = r.nextInt(m1.getNumberOfDays(y1)) + 1;
		
			OOSCDate date = new OOSCDate(y1,m1,d1);
			
			int y2 = r.nextInt(y1);
			Month m2 ;
			if(y1 == y2){
				 m2 = Month.month(m1.getValue());
			}else{
				 m2 = Month.month(r.nextInt(12) + 1);	
			}
			int d2 = r.nextInt(m2.getNumberOfDays(y2)) + 1;
			
			OOSCDate dateTocompare = new OOSCDate(y2,m2,d2);
			
			int monthsBetween = date.toNumberOfMonths() - date.toNumberOfMonths();
			
			assertEquals(DateInterface.monthBetween(date, dateTocompare), monthsBetween);
			
		}
		
	}
	
	@Test
	public void yearsBetween(){
		
		for(int i = 0 ; i < 100 ; ++i){
			
			Random r = new Random();
			
			int y1 = r.nextInt(5000);
			Month m1 = Month.month(r.nextInt(12) + 1);
			int d1 = r.nextInt(m1.getNumberOfDays(y1)) + 1;
		
			OOSCDate date = new OOSCDate(y1,m1,d1);
			
			int y2 = r.nextInt(y1-1);
			Month m2 = Month.month(r.nextInt(12) + 1);
			int d2 = r.nextInt(m2.getNumberOfDays(y2)) + 1;
			
			OOSCDate dateTocompare = new OOSCDate(y2,m2,d2);
			
			int yearsBetween = y1-y2;
			
			assertEquals(DateInterface.yearBetween(date, dateTocompare), yearsBetween);
			
		}
		
	}

	@Test
	public void timeBetween() {

	}

//	@Test
//	public void synchWithUTCTimeserver() {
//
//		OOSCDate date1 = new OOSCDate(1, 1, 1);
//
//		try {
//			// get URL content
//			URL url = new URL("http://www.timeapi.org/utc/now");
//			URLConnection conn = url.openConnection();
//
//			// open the stream and put it into BufferedReader
//			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//			String inputLine = br.readLine();
//			if (inputLine == null) {
//				// TODO: Throw errors
//			} else {
//				String[] format = inputLine.split("T");
//				if (format.length < 3) {
//
//					String[] parsedDate = format[0].split("-");
//
//					date1.setDate(Integer.parseInt(parsedDate[0]), Integer.parseInt(parsedDate[1]),
//							Integer.parseInt(parsedDate[2]));
//
//				} else {
//					String[] oosc = format[0].split("T");
//					// TODO: Deal with erros
//				}
//			}
//			br.close();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		/*
//		 * OOSCDate date2 = new OOSCDate(1,1,1); date2.synchWithUTCTimeserver();
//		 * 
//		 * assertTrue(date2.equals(date1));
//		 */
//
//	}
	
	@Test
	public void cloneTest(){
		
		OOSCDate date;
		OOSCDate clonedDate;
		
		
		for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
			for(Month m : Month.values()){
				for(int d=1; d<=m.getNumberOfDays(y); ++d){
					
				date = new OOSCDate(y,m,d);	
				clonedDate = (OOSCDate)date.clone();
				
				assertTrue(date.equals(clonedDate));
					
				}
			}
		}
				
	}

	@Test
	public void equal() {

		OOSCDate date1;
		OOSCDate date2;
		Boolean isEqual;
		
		for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
			for(Month m : Month.values()){
				for(int d=1; d<=m.getNumberOfDays(y); ++d){
					date1 = new OOSCDate(y,m,d);
					date2 = new OOSCDate(y,m,d);
					
					isEqual = date1.equals(date2);
					assertTrue(isEqual);
				}
			}
		}
		
	}
	
}
