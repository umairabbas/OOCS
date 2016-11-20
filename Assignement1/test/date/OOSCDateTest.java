package date;

import java.util.Random;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
	public void toNumberOfMonths() {
		
		OOSCDate date;
		int numMonths,actualMonths;
	
		for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
			for(Month m : Month.values()){
				for(int d=1; d<=m.getNumberOfDays(y); ++d){
		
					date = new OOSCDate(y,m,d);
					
					numMonths = y * 12;
					numMonths += m.getValue() -1;
					
					actualMonths = date.toNumberOfMonths();
					
					assertEquals(numMonths,actualMonths);
					
				}
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
	public void addDaysTestOCL(){
		OOSCDate date = new OOSCDate(2016, 2, 8);
		OOSCDate datePre = new OOSCDate(2016, 2, 8);
		date.addDays(9);
		
		assertEquals(date.toNumberOfDays() ,  datePre.toNumberOfDays() + 9);
	}

	@Test
	public void addDaysTest() {

		Random r = new Random();
		int daysToadd;
		
		OOSCDate date;
		LocalDate ldate,expectedlDate;
		
		for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
			for(Month m : Month.values()){
				for(int d=1; d<=m.getNumberOfDays(y); ++d){
					
					daysToadd = r.nextInt(5000);
					
					date = new OOSCDate(y,m,d);
					date.addDays(daysToadd);
					
					ldate = LocalDate.of(y,m.getValue(),d);
					expectedlDate = ldate.plusDays(daysToadd);
					
					assertEquals(expectedlDate.getYear(),date.getYear());
					assertEquals(expectedlDate.getMonthValue(), date.getMonth());
					assertEquals(expectedlDate.getDayOfMonth(), date.getDay());
					
				}
			}
		}
	}


	@Test
	public void addMonths() {

		Random r = new Random();
		int monthsToadd;
		int actualMonths;
        int newYear;
        int newMonth;

		OOSCDate date,expectedDate;
		
		for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
			for(Month m : Month.values()){
				for(int d=1; d<=m.getNumberOfDays(y); ++d){
					date = new OOSCDate(y,m,d);
					expectedDate = new OOSCDate(y,m,d);
										
					monthsToadd = r.nextInt(5000);

					date.addMonths(monthsToadd);
					
	                actualMonths = date.getYear()*12;
	                actualMonths += date.getMonth();
	                actualMonths += monthsToadd;
	                            
	                newYear = actualMonths/12;
	                if(actualMonths %12 == 0){
	                    newMonth = 12;
	                }
	                else{
	                    newMonth = actualMonths %12; 
	                }
	                
	                expectedDate.setYear(newYear);
	                expectedDate.setMonth(newMonth);
	           
	                assertTrue(date.equals(expectedDate));
	           
				}
			}
		}	

	}
	
	
	@Test
	public void addMonthsExemple() {
		OOSCDate date = new OOSCDate(2016, 2, 8);

		date.addMonths(2);

		assertEquals(4, date.getMonth());
	
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
					
					assertEquals(yearsToadd + y, date.getYear());
				}
			}
		}	
	}

	@Test
	public void removeDaysExemple(){
		OOSCDate date = new OOSCDate(2016, 2, 8);

		date.removeDays(3);

		assertEquals(5, date.getMonth());
	}
	
			
	
	@Test
	public void removeDays() {


		Random r = new Random();
		int daysToremove;
		
		OOSCDate date;
		LocalDate ldate,expectedlDate;
		
		for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
			for(Month m : Month.values()){
				for(int d=1; d<=m.getNumberOfDays(y); ++d){

					
					date = new OOSCDate(y,m,d);
					daysToremove = r.nextInt(date.toNumberOfDays());

					date.removeDays(daysToremove);
					
					ldate = LocalDate.of(y,m.getValue(),d);
					expectedlDate = ldate.minusDays(daysToremove);
					
					assertEquals(expectedlDate.getYear(),date.getYear());
					assertEquals(expectedlDate.getMonthValue(), date.getMonth());
					assertEquals(expectedlDate.getDayOfMonth(), date.getDay());
					
				}
			}
		}
		
	}
	
	

	@Test
	public void removeMonths() {


		Random r = new Random();
		int monthsToremove;
		int actualMonths = 0;
		int newDateMonths;
        int newYear;
        int newMonth;

		OOSCDate date,expectedDate;
		
		for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
			for(Month m : Month.values()){
				for(int d=1; d<=m.getNumberOfDays(y); ++d){
					
					date = new OOSCDate(y,m,d);
					expectedDate = new OOSCDate(y,m,d);

					actualMonths = date.getYear()*12;
	                actualMonths += date.getMonth();
	                
	                monthsToremove = r.nextInt(actualMonths)+1;
					
	                newDateMonths = actualMonths - monthsToremove;

					date.removeMonths(monthsToremove);
					
	                newYear = newDateMonths/12;
	                if(newDateMonths %12 == 0){
	                    newMonth = 12;
	                }
	                else{
	                    newMonth = newDateMonths %12; 
	                }
	                
	                expectedDate.setYear(newYear);
	                expectedDate.setMonth(newMonth);
	           
	                assertTrue(date.equals(expectedDate));
	           
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

		int y1,y2,d1,d2;
		Month m1,m2;
		
		OOSCDate date1,date2;
		LocalDate ldate1,ldate2;
		
		int expDaysBetween,actDaysBetween;
		
		for (int i=0;i<100;++i){
		
			Random r = new Random();
			
			y1 = r.nextInt(5000);
			m1 = Month.month(r.nextInt(12) + 1);
			d1 = r.nextInt(m1.getNumberOfDays(y1)) + 1;
			
			y2 = r.nextInt(5000);
			m2 = Month.month(r.nextInt(12) + 1);
			d2 = r.nextInt(m1.getNumberOfDays(y1)) + 1;
			
			date1 = new OOSCDate(y1,m1,d1);
			date2 = new OOSCDate(y2,m2,d2);
			
			ldate1 = LocalDate.of(y1,m1.getValue(),d1);
			ldate2 = LocalDate.of(y2,m2.getValue(),d2);
			
			actDaysBetween = DateInterface.daysBetween(date1,date2);
			expDaysBetween = (int) ChronoUnit.DAYS.between(ldate2, ldate1);
			
			assertEquals(expDaysBetween,actDaysBetween);
	
		}
	}

	@Test
	public void monthBetween() {
		
		int y1,y2,d1,d2;
		Month m1,m2;
		
		OOSCDate date1,date2;
		LocalDate ldate1,ldate2;
		
		int expDaysBetween,actDaysBetween;
		
		for (int i=0;i<100;++i){
		
			Random r = new Random();
			
			y1 = r.nextInt(5000);
			m1 = Month.month(r.nextInt(12) + 1);
			d1 = r.nextInt(m1.getNumberOfDays(y1)) + 1;
			
			y2 = r.nextInt(5000);
			m2 = Month.month(r.nextInt(12) + 1);
			d2 = r.nextInt(m1.getNumberOfDays(y1)) + 1;
			
			date1 = new OOSCDate(y1,m1,d1);
			date2 = new OOSCDate(y2,m2,d2);
			
			ldate1 = LocalDate.of(y1,m1.getValue(),d1);
			ldate2 = LocalDate.of(y2,m2.getValue(),d2);
			
			actDaysBetween = DateInterface.daysBetween(date1,date2);
			expDaysBetween = (int) ChronoUnit.MONTHS.between(ldate2, ldate1);
			
			assertEquals(expDaysBetween,actDaysBetween);
			
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

		int y1,y2,d1,d2;
		Month m1,m2;
		
		OOSCDate date1,date2;
		LocalDate ldate1,ldate2;
		
		int expDaysBetween = 0,actDaysBetween;
		
		for (int i=0;i<100;++i){
			for(int j = 0; j < 3 ; ++j){
			Random r = new Random();
			
			y1 = r.nextInt(5000);
			m1 = Month.month(r.nextInt(12) + 1);
			d1 = r.nextInt(m1.getNumberOfDays(y1)) + 1;
			
			y2 = r.nextInt(5000);
			m2 = Month.month(r.nextInt(12) + 1);
			d2 = r.nextInt(m1.getNumberOfDays(y1)) + 1;
			
			date1 = new OOSCDate(y1,m1,d1);
			date2 = new OOSCDate(y2,m2,d2);
			
			ldate1 = LocalDate.of(y1,m1.getValue(),d1);
			ldate2 = LocalDate.of(y2,m2.getValue(),d2);
			
			actDaysBetween = date1.timeBetween(j, date2);
			
			switch (j){
				case 0: expDaysBetween = (int) ChronoUnit.YEARS.between(ldate2, ldate1);
					break;
				case 1: expDaysBetween = (int) ChronoUnit.DAYS.between(ldate2, ldate1);
					break;
				case 2: expDaysBetween = (int) ChronoUnit.MONTHS.between(ldate2, ldate1);
					break;
			}
			
			assertEquals(expDaysBetween,actDaysBetween);
			
			}
		}
		
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
	
	
	
	
	
	
	// add by Lauriane 
	
	@Test
	public void toNumberOfDays(){
		OOSCDate test = new OOSCDate(0, 1, 1);
		
		assertEquals(1, test.toNumberOfDays());
	}
	
	
	
	@Test
	public void toDateInterfaceExemple1(){
		DateInterface test = DateInterface.toDateInterface(1);
		assertEquals(0, test.getYear());
		assertEquals(1, test.getMonth());
		assertEquals(1, test.getDay());
	}
	
	@Test
	public void toDateInterfaceExemple2(){
		DateInterface test = DateInterface.toDateInterface(31);
		assertEquals(0, test.getYear());
		assertEquals(1, test.getMonth());
		assertEquals(31, test.getDay());
	}
	
	@Test
	public void monthBetweenExemple(){
		OOSCDate test1 = new OOSCDate(0, 1, 1);
		OOSCDate test2 = new OOSCDate(0, 2, 1);
		assertEquals(0, DateInterface.monthBetween(test1, test2));
	}
	
	
}
