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

	private static final int LIMITE_YEAR_TESTED = 2000;

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

	@Test(expected = java.lang.Error.class)
	public void numberOfDaysInYearErrorTest() {
		int n;
		for (int y = -1; y > - (LIMITE_YEAR_TESTED); --y) {
			n = DateInterface.numberOfDaysInYear(y);
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
					numMonths += m.getValue();
					
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
			if(((y % 4 == 0) && (y % 100 != 0)) || (y % 400 == 0)){
				leapYear = true;
			}else{
				leapYear = false;
			}
				assertEquals(leapYear,DateInterface.isLeapYear(y));
		}
	}

	@Test(expected = java.lang.Error.class)
	public void isLeapYearErrorTest(){
		for (int y = 0; y > -(LIMITE_YEAR_TESTED); --y) {
				DateInterface.isLeapYear(y);
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

	@Test(expected = java.lang.Error.class)
	public void setDateErrorTest() {
		
		OOSCDate date = new OOSCDate();
		
		for(int y = -1000; y<1000; ++y){
			
			//year is negative
			if(y<0){
				
				for(Month m : Month.values()){
					for(int d=1; d<=m.getNumberOfDays(-y); ++d){
						date.setDate(y,m,d);
					}
				}
				
			}else{
				//year is positive
				for(int m = -100; m<100; ++m){
					//inexistent month
					if(m<=0 && m>13){
						for(int d=-100;d<100;++d){
							date.setDate(y,m,d);
						}
					//existent month
					}else{
						for(int d=-100;d<100;++d){
							//day inexistent in month
							if(0> d || Month.month(m).getNumberOfDays(y)<d){
								date.setDate(y,m,d);
							}
						}
					}
				}
			}
			//create date whose day is 28th of February in a leap year
			if(DateInterface.isLeapYear(y)){
				date.setDate(y, Month.FEBRUARY,28);
				
			//create date whose day is 29th of February in a non leap year	
			}else{
				date.setDate(y, Month.FEBRUARY,29);
			}
		}
	}

	
	@Test
	public void setYear() {

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
	}

	@Test(expected = java.lang.Error.class)
	public void setYearErrorTest() {
		
		OOSCDate date = new OOSCDate();
		
		for(int y = -1; y> -(LIMITE_YEAR_TESTED); --y){
			date.setYear(y);
		}
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
	
	@Test(expected = java.lang.Error.class)
	public void setMonthErrorTest() {
		
		OOSCDate date = new OOSCDate();
		
		for(int m = 100; m> -100; --m){
			if(m>12 || m<1){
				date.setMonth(m);
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

	@Test(expected = java.lang.Error.class)
	public void setDayErrorTest() {
		
		OOSCDate date = new OOSCDate();
		
		for(int d = 100; d> -100; --d){
			if(d>31 || d<1){
				date.setDay(d);
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
	public void addDaysExemple1() {
		OOSCDate date = new OOSCDate(2016, 2, 8);

		date.addDays(4);

		assertEquals(12, date.getDay());
	}
	
	@Test
	public void addDaysExemple2() {
		OOSCDate date = new OOSCDate(2016, 2, 8);

		date.addDays(0);
		assertEquals(8, date.getDay());
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

	@Test(expected = java.lang.Error.class)
	public void addDaysErrorTest() {
		
		OOSCDate date = new OOSCDate();
		
		for(int days = -1; days> -100; --days){
			for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
				for(Month m : Month.values()){
					for(int d=1; d<=m.getNumberOfDays(y); ++d){
			
						date = new OOSCDate(y,m,d);
						date.addDays(days);
					}
				}
			}			
		}
	}

	@Test
	public void addMonths() {

		Random r = new Random();
		int monthsToadd;
		
		OOSCDate date;
		LocalDate ldate,expectedDate;
		
		for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
			for(Month m : Month.values()){
				for(int d=1; d<=m.getNumberOfDays(y); ++d){
					date = new OOSCDate(y,m,d);
					ldate = LocalDate.of(y,m.getValue(),d);
										
					monthsToadd = r.nextInt(5000);

					date.addMonths(monthsToadd);
					expectedDate = ldate.plusMonths(monthsToadd);
	                
					assertEquals(expectedDate.getYear(),date.getYear());
					assertEquals(expectedDate.getMonthValue(), date.getMonth());
					
				}
			}
		}	
	}
	
	@Test(expected = java.lang.Error.class)
	public void addMonthsErrorTest() {
		
		OOSCDate date = new OOSCDate();
		
		for(int months = -1; months> -100; --months){
			for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
				for(Month m : Month.values()){
					for(int d=1; d<=m.getNumberOfDays(y); ++d){
			
						date = new OOSCDate(y,m,d);
						date.addMonths(months);;
					}
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

	@Test(expected = java.lang.Error.class)
	public void addYearsErrorTest() {
		
		OOSCDate date = new OOSCDate();
		
		for(int years = -1; years> -100; --years){
			for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
				for(Month m : Month.values()){
					for(int d=1; d<=m.getNumberOfDays(y); ++d){
			
						date = new OOSCDate(y,m,d);
						date.addYears(years);
					}
				}
			}			
		}
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

	@Test(expected = java.lang.Error.class)
	public void removeDaysErrorTest() {
		
		OOSCDate date = new OOSCDate();
		date.removeDays(-1);
	}
	
	@Test
	public void removeMonths() {


		Random r = new Random();
		int monthsToremove;

		OOSCDate date;
		LocalDate ldate,expectedDate;
		
		for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
			for(Month m : Month.values()){
				for(int d=1; d<=m.getNumberOfDays(y); ++d){
					
					date = new OOSCDate(y,m,d);
					ldate = LocalDate.of(y,m.getValue(),d);
					
					monthsToremove = r.nextInt(date.toNumberOfMonths());

					date.removeMonths(monthsToremove);
					expectedDate = ldate.minusMonths(monthsToremove);
					
					assertEquals(expectedDate.getYear(),date.getYear());
					assertEquals(expectedDate.getMonthValue(), date.getMonth());

				}
			}
		}
	}

	@Test(expected = java.lang.Error.class)
	public void removeMonthsErrorTest() {
		
		OOSCDate date = new OOSCDate();
		
		for(int months = -1; months> -100; --months){
			for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
				for(Month m : Month.values()){
					for(int d=1; d<=m.getNumberOfDays(y); ++d){
			
						date = new OOSCDate(y,m,d);
						date.removeMonths(months);
					}
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
					
					yearsToremove = r.nextInt(y) + 1;
					
					date = new OOSCDate(y,m,d);
					
					date.removeYears(yearsToremove);
					
					assertEquals(y-yearsToremove,date.getYear());
				}
			}
		}

	}

	@Test(expected = java.lang.Error.class)
	public void removeYearsErrorTest() {
		
		
		for(int years = -1; years> -100; --years){
			for(int y = 0; y<LIMITE_YEAR_TESTED; ++y){
				for(Month m : Month.values()){
					for(int d=1; d<=m.getNumberOfDays(y); ++d){
			
						OOSCDate date = new OOSCDate(y,m,d);
						date.removeYears(years);
					}
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
			d2 = r.nextInt(m2.getNumberOfDays(y2)) + 1;
			
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
	public void monthBetween() { // 12 months in one Year???
		
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
			d2 = r.nextInt(m2.getNumberOfDays(y2)) + 1;
			
			date1 = new OOSCDate(y1,m1,d1);
			date2 = new OOSCDate(y2,m2,d2);
			
			ldate1 = LocalDate.of(y1,m1.getValue(),d1);
			ldate2 = LocalDate.of(y2,m2.getValue(),d2);
			
			actDaysBetween = DateInterface.monthBetween(date1,date2);
			expDaysBetween = (int) ChronoUnit.MONTHS.between(ldate2, ldate1);
			
			System.out.println(date1.toString());
			System.out.println(date2.toString());
			System.out.println(actDaysBetween);
			System.out.println(expDaysBetween);
			System.out.println("");
			
			assertEquals(1 + expDaysBetween,actDaysBetween);
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
			
			int y2 = r.nextInt(5000);
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
		
		int expTimeBetween = 0,actTimeBetween;
		
		for (int i=0;i<100;++i){
			for(int j = 0; j < 3 ; ++j){
			Random r = new Random();
			
			y1 = r.nextInt(5000);
			m1 = Month.month(r.nextInt(12) + 1);
			d1 = r.nextInt(m1.getNumberOfDays(y1)) + 1;
			
			y2 = r.nextInt(5000);
			m2 = Month.month(r.nextInt(12) + 1);
			d2 = r.nextInt(m2.getNumberOfDays(y2)) + 1;
			
			date1 = new OOSCDate(y1,m1,d1);
			date2 = new OOSCDate(y2,m2,d2);
			
			ldate1 = LocalDate.of(y1,m1.getValue(),d1);
			ldate2 = LocalDate.of(y2,m2.getValue(),d2);
			
			actTimeBetween = date1.timeBetween(j, date2);
			
			switch (j){
				case 0: expTimeBetween = (int) ChronoUnit.YEARS.between(ldate2, ldate1);
					break;
				case 1: expTimeBetween = (int) ChronoUnit.DAYS.between(ldate2, ldate1);
					break;
				case 2: expTimeBetween = (int) ChronoUnit.MONTHS.between(ldate2, ldate1);
					break;
			}
			assertEquals(expTimeBetween,actTimeBetween);
			
			}
		}
		
	}

	@Test(expected = java.lang.AssertionError.class)
	public void timeBetweenErrorTest() {
		
		OOSCDate date1,date2;
		
		int y1,y2,d1,d2;
		Month m1,m2;
		
		for (int i=0;i<100;++i){
			for(int j = 0; j < 3 ; ++j){
				
				Random r = new Random();
				
				y1 = r.nextInt(5000);
				m1 = Month.month(r.nextInt(12) + 1);
				d1 = r.nextInt(m1.getNumberOfDays(y1)) + 1;
				
				y2 = r.nextInt(5000);
				m2 = Month.month(r.nextInt(12) + 1);
				d2 = r.nextInt(m2.getNumberOfDays(y1)) + 1;
			
				date1 = new OOSCDate(y1,m1,d1);
				date2 = new OOSCDate(y2,m2,d2);
				
				for(int k = 0; k<100; ++k){
					if(k != 0 && k != 1 && k != 2){
						date1.timeBetween(k,date2);
					}
				}	
			}		
		}
	}

	
	@Test
	public void synchWithUTCTimeserver() throws Exception {
	
		OOSCDate date = new OOSCDate();
		LocalDate ldate = LocalDate.now();
		
		date.synchWithUTCTimeserver();
		
		assertEquals(ldate.getYear(),date.getYear());
		assertEquals(ldate.getMonthValue(),date.getMonth());
		assertEquals(ldate.getDayOfMonth(),date.getDay());
	}
	
	
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
	
	@Test
	public void toDateinterface(){
		
		Random r = new Random();
		OOSCDate date;
		LocalDate ldate;
		LocalDate firstDate = LocalDate.of(0,1,1);
		int numdays,dateInNumbers;
		
		for(int i = 0 ; i< 4; i++){
			
			dateInNumbers = r.nextInt(4000)+1;
			
			date = (OOSCDate) DateInterface.toDateInterface(dateInNumbers);
			ldate = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());

			numdays= (int)ChronoUnit.DAYS.between(firstDate,ldate);
			assertEquals(1 + numdays,dateInNumbers);
		}
	}
	
	@Test(expected = java.lang.Error.class)
	public void toDateInterfaceErrorTest(){
		
		for(int i = 0; i> -4000; --i){
			DateInterface.toDateInterface(i);
		}
	}
	
	
		
		// add by Lauriane TODO: re-organize the function oder and add comment
	
	@Test
	public void toNumberOfDaysExemple1(){
		OOSCDate test = new OOSCDate(0, 1, 1);
		assertEquals(1, test.toNumberOfDays());
	}
	
	@Test
	public void toNumberOfDaysExemple2(){
		OOSCDate test = new OOSCDate(0, 1, 31);
		assertEquals(31, test.toNumberOfDays());
	}
	
	@Test
	public void toNumberOfDaysExemple3(){
		OOSCDate test = new OOSCDate(0, 2, 1);
		assertEquals(32, test.toNumberOfDays());
	}
	
	@Test
	public void toNumberOfDaysExemple4(){
		OOSCDate test = new OOSCDate(1, 2, 1);
		assertEquals(32 + 366, test.toNumberOfDays());
	}
	
	@Test
	public void toNumberOfDaysExemple5(){
		OOSCDate test = new OOSCDate(2, 2, 1);
		assertEquals(32 + 366 + 365, test.toNumberOfDays());
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
	public void toDateInterfaceExemple3(){
		DateInterface test = DateInterface.toDateInterface(32);
		assertEquals(0, test.getYear());
		assertEquals(2, test.getMonth());
		assertEquals(1, test.getDay());
	}
	@Test
	public void toDateInterfaceExemple4(){
		DateInterface test = DateInterface.toDateInterface(32 + 366);
		assertEquals(1, test.getYear());
		assertEquals(2, test.getMonth());
		assertEquals(1, test.getDay());
	}
	@Test
	public void toDateInterfaceExemple5(){
		DateInterface test = DateInterface.toDateInterface(32 + 366 + 365);
		assertEquals(2, test.getYear());
		assertEquals(2, test.getMonth());
		assertEquals(1, test.getDay());
	}

	
	
	
	
	
	
	@Test 
	public void toNumberOfMonthsExemple1(){
		OOSCDate test1 = new OOSCDate(0, 1, 1);
		assertEquals(1, test1.toNumberOfMonths());
	}
	
	@Test 
	public void toNumberOfMonthsExemple2(){
		OOSCDate test1 = new OOSCDate(0, 1, 31);
		assertEquals(1, test1.toNumberOfMonths());
	}
	
	@Test 
	public void toNumberOfMonthsExemple3(){
		OOSCDate test1 = new OOSCDate(1, 1, 31);
		assertEquals(13, test1.toNumberOfMonths());
	}
	
	@Test
	public void monthBetweenExemple1(){
		OOSCDate test1 = new OOSCDate(0, 1, 1);
		OOSCDate test2 = new OOSCDate(0, 1, 31);
		assertEquals(0, DateInterface.monthBetween(test1, test2));
	}
	@Test
	public void monthBetweenExemple2(){
		OOSCDate test1 = new OOSCDate(0, 1, 1);
		OOSCDate test2 = new OOSCDate(0, 2, 27);
		assertEquals(-1, DateInterface.monthBetween(test1, test2));
	}
	@Test
	public void monthBetweenExemple3(){
		OOSCDate test1 = new OOSCDate(1, 1, 1);
		OOSCDate test2 = new OOSCDate(0, 1, 31);
		assertEquals(12, DateInterface.monthBetween(test1, test2));
	}
	
	
	@Test 
	public void removeMonthsExemple1(){
		OOSCDate test1 = new OOSCDate(1, 5, 1);
		test1.removeMonths(2);
		assertEquals(3, test1.getMonth());
	}
	@Test 
	public void removeMonthsExemple2(){
		OOSCDate test1 = new OOSCDate(1, 1, 1);
		test1.removeMonths(1);
		assertEquals(12, test1.getMonth());
		assertEquals(0, test1.getYear());
	}
	
	@Test 
	public void removeDaysExemple1(){
		OOSCDate test1 = new OOSCDate(1, 1, 2);
		test1.removeDays(1);
		assertEquals(1, test1.getDay());
		assertEquals(1, test1.getMonth());
		assertEquals(1, test1.getYear());
	}
	
	@Test 
	public void removeDaysExemple2(){
		OOSCDate test1 = new OOSCDate(1, 1, 1);
		test1.removeDays(1);
		assertEquals(31, test1.getDay());
		assertEquals(12, test1.getMonth());
		assertEquals(0, test1.getYear());
	}
	

	
}
