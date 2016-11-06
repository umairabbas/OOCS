package date;
import static org.junit.Assert.*;

import org.junit.Test;

import date.Month;
import date.OOSCDate;

public class MonthTest {

	@Test
	public void coorectValue(){
		assertEquals(Month.JANUARY.getValue(), 1);
		assertEquals(Month.FEBRUARY.getValue(), 2);
		assertEquals(Month.MARCH.getValue(), 3);
		assertEquals(Month.APRIL.getValue(), 4);
		
		assertEquals(Month.MAY.getValue(), 5);
		assertEquals(Month.JUNE.getValue(), 6);
		assertEquals(Month.JULY.getValue(), 7);
		assertEquals(Month.AUGUST.getValue(), 8);
		
		assertEquals(Month.SEPTEMBER.getValue(), 9);
		assertEquals(Month.OCTOBER.getValue(), 10);
		assertEquals(Month.NOVEMBER.getValue(), 11);
		assertEquals(Month.DECEMBER.getValue(), 12);
	}
	
	@Test
	public void coorectDaysInMonth(){
		for(int y= 0; y<3000; ++y){
			assertEquals(Month.JANUARY.getNumberOfDays(y), 31);
			
			if(OOSCDate.isLeapYear(y)){
				assertEquals(Month.FEBRUARY.getNumberOfDays(y), 29);
			}
			else {
				assertEquals(Month.FEBRUARY.getNumberOfDays(y), 28);
			}
			assertEquals(Month.MARCH.getNumberOfDays(y), 31);
			assertEquals(Month.APRIL.getNumberOfDays(y), 30);
			
			assertEquals(Month.MAY.getNumberOfDays(y), 31);
			assertEquals(Month.JUNE.getNumberOfDays(y), 30);
			assertEquals(Month.JULY.getNumberOfDays(y), 31);
			assertEquals(Month.AUGUST.getNumberOfDays(y), 31);
			
			assertEquals(Month.SEPTEMBER.getNumberOfDays(y), 30);
			assertEquals(Month.OCTOBER.getNumberOfDays(y), 31);
			assertEquals(Month.NOVEMBER.getNumberOfDays(y), 30);
			assertEquals(Month.DECEMBER.getNumberOfDays(y), 31);
		}
	}
	
	@Test
	public void monthName(){
		assertEquals(Month.JANUARY.toString(), "January");
		assertEquals(Month.FEBRUARY.toString(), "February");
		assertEquals(Month.MARCH.toString(), "March");
		assertEquals(Month.APRIL.toString(), "April");
		
		assertEquals(Month.MAY.toString(), "May");
		assertEquals(Month.JUNE.toString(), "June");
		assertEquals(Month.JULY.toString(), "July");
		assertEquals(Month.AUGUST.toString(), "August");
		
		assertEquals(Month.SEPTEMBER.toString(), "September");
		assertEquals(Month.OCTOBER.toString(), "October");
		assertEquals(Month.NOVEMBER.toString(), "November");
		assertEquals(Month.DECEMBER.toString(), "December");
	}
	
	@Test
	public void nextMonth(){
		assertEquals(Month.JANUARY.nextMonth(), Month.FEBRUARY);
		assertEquals(Month.FEBRUARY.nextMonth(), Month.MARCH);
		assertEquals(Month.MARCH.nextMonth(), Month.APRIL);
		assertEquals(Month.APRIL.nextMonth(), Month.MAY);
		
		assertEquals(Month.MAY.nextMonth(), Month.JUNE);
		assertEquals(Month.JUNE.nextMonth(), Month.JULY);
		assertEquals(Month.JULY.nextMonth(), Month.AUGUST);
		assertEquals(Month.AUGUST.nextMonth(), Month.SEPTEMBER);
		
		assertEquals(Month.SEPTEMBER.nextMonth(), Month.OCTOBER);
		assertEquals(Month.OCTOBER.nextMonth(), Month.NOVEMBER);
		assertEquals(Month.NOVEMBER.nextMonth(), Month.DECEMBER);
		assertEquals(Month.DECEMBER.nextMonth(), Month.JANUARY);
	}
	
}
