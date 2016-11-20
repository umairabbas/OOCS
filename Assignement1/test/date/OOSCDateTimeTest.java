package date;

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

public class OOSCDateTimeTest {

	@Test(expected = java.lang.Error.class)
	public void setTimeErrorHours() {

		OOSCDateTime test = new OOSCDateTime();
		test.setTime(-1, 0, 0);
	}
	@Test(expected = java.lang.Error.class)
	public void setTimeErrorMinutes() {

		OOSCDateTime test = new OOSCDateTime();
		test.setTime(0, -1, 0);
	}
	@Test(expected = java.lang.Error.class)
	public void setTimeErrorSeconds() {

		OOSCDateTime test = new OOSCDateTime();
		test.setTime(0, 0, -1);
	}
	@Test(expected = java.lang.Error.class)
	public void setTimeErrorHoursUpperbound() {

		OOSCDateTime test = new OOSCDateTime();
		test.setTime(24, 0, 0);
	}
	@Test(expected = java.lang.Error.class)
	public void setTimeErrorMinutesUpperbound() {

		OOSCDateTime test = new OOSCDateTime();
		test.setTime(0, 60, 0);
	}
	@Test(expected = java.lang.Error.class)
	public void setTimeErrorSecondsUpperbound() {

		OOSCDateTime test = new OOSCDateTime();
		test.setTime(0, 0, 60);
	}
	@Test
	public void setTime() {
		OOSCDateTime test = new OOSCDateTime();
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 60; j++) {
				for (int k = 0; k < 60; k++) {

					test.setTime(i, j, k);
					assertEquals(i, test.getHours());
					assertEquals(j, test.getMinutes());
					assertEquals(k, test.getSeconds());
				}
			}
		}
	}
	
	@Test
	public void getTimetest() {
		OOSCDateTime test = new OOSCDateTime();
		test.setTime(0,0,0);
		assertTrue("0:0:0".equals(test.getTime()));
	}
	@Test(expected = java.lang.Error.class)
	public void setDateTimeTestYear(){
		OOSCDateTime test = new OOSCDateTime();
		test.setDateTime(-1, 0, 0, 0, 0, 0);
		test.setTime(0,0,0);		
	}
	@Test(expected = java.lang.Error.class)
	public void setDateTimeTestMonth(){
		OOSCDateTime test = new OOSCDateTime();
		test.setDateTime(0, -1, 0, 0, 0, 0)	;
	}
	@Test(expected = java.lang.Error.class)
	public void setDateTimeTestDay(){
		OOSCDateTime test = new OOSCDateTime();
		test.setDateTime(0, 0, -1, 0, 0, 0);		
	}
	@Test(expected = java.lang.Error.class)
	public void setDateTimeTestHour(){
		OOSCDateTime test = new OOSCDateTime();
		test.setDateTime(0, 0, 0, -1, 0, 0);		
	}
	@Test(expected = java.lang.Error.class)
	public void setDateTimeTestMinutes(){
		OOSCDateTime test = new OOSCDateTime();
		test.setDateTime(0, 0, 0, 0, -1, 0);		
	}
	@Test(expected = java.lang.Error.class)
	public void setDateTimeTestSeconds(){
		OOSCDateTime test = new OOSCDateTime();
		test.setDateTime(0, 0, 0, 0, 0, -1);		
	}
	
	@Test(expected = java.lang.Error.class)
	public void setHoursLowerBound() {
		OOSCDateTime test = new OOSCDateTime();
		test.setHours(-1);
		
	}
	@Test(expected = java.lang.Error.class)
	public void setHoursUpperBound() {
		OOSCDateTime test = new OOSCDateTime();
		test.setHours(24);
		
	}
	@Test
	public void setHoursTest() {
		OOSCDateTime test = new OOSCDateTime();
		for (int i = 0; i < 24; i++)
		test.setHours(i);
		
	}
	@Test(expected = java.lang.Error.class)
	public void setMinutesLowerBound() {
		OOSCDateTime test = new OOSCDateTime();
		test.setMinutes(-1);
		
	}
	@Test(expected = java.lang.Error.class)
	public void setMinutesUpperBound() {
		OOSCDateTime test = new OOSCDateTime();
		test.setMinutes(60);
		
	}
	@Test
	public void setMinutesTest() {
		OOSCDateTime test = new OOSCDateTime();
		for (int i = 0; i < 60; i++)
		test.setMinutes(i);
		
	}
	
	@Test(expected = java.lang.Error.class)
	public void setSecondsLowerBound() {
		OOSCDateTime test = new OOSCDateTime();
		test.setSeconds(-1);
		
	}
	@Test(expected = java.lang.Error.class)
	public void setSecondsUpperBound() {
		OOSCDateTime test = new OOSCDateTime();
		test.setSeconds(60);
		
	}
	@Test
	public void setSecondsTest() {
		OOSCDateTime test = new OOSCDateTime();
		for (int i = 0; i < 60; i++)
		test.setSeconds(i);
		
	}
	
	@Test
	public void getHoursTest() {
		OOSCDateTime test = new OOSCDateTime();
		for (int i = 0; i < 24; i++){
			test.setHours(i);
			assertEquals(i, test.getHours());
		}
	}
	

	@Test
	public void getMinutesTest() {
		OOSCDateTime test = new OOSCDateTime();
		for (int i = 0; i < 60; i++){
			test.setMinutes(i);
			assertEquals(i, test.getMinutes());
		}
	}
	@Test
	public void getSecondsTest() {
		OOSCDateTime test = new OOSCDateTime();
		for (int i = 0; i < 60; i++){
			test.setSeconds(i);
			assertEquals(i, test.getSeconds());
		}
	}
	
	@Test
	public void addHoursTest() {
		OOSCDateTime test = new OOSCDateTime();
		test.setTime(0, 0, 0);
		test.addHours(2);
		assertEquals(2, test.getHours());
	}

	@Test
	public void addHoursNewDay() {
		OOSCDateTime test = new OOSCDateTime();
		test.setDateTime(1, 1, 1, 23, 0, 0);
		test.addHours(1);
		assertEquals(0, test.getHours());
		assertEquals(2, test.getDay());
	}
	@Test
	public void addHoursNewMonth() {
		OOSCDateTime test = new OOSCDateTime();
		test.setDateTime(1, 1, 31, 23, 0, 0);
		test.addHours(1);
		assertEquals(0, test.getHours());
		assertEquals(1, test.getDay());
		assertEquals(2, test.getMonth());
	}
	@Test
	public void addHoursNewYear() {
		OOSCDateTime test = new OOSCDateTime();
		test.setDateTime(1, 12, 31, 23, 0, 0);
		test.addHours(1);
		assertEquals(0, test.getHours());
		assertEquals(1, test.getDay());
		assertEquals(1, test.getMonth());
		assertEquals(2, test.getYear());
	}

	
	@Test
	public void addMinutesTest() {
		OOSCDateTime test = new OOSCDateTime();
		test.setTime(0, 0, 0);
		test.addMinutes(1);
		assertEquals(0, test.getHours());
		assertEquals(1, test.getMinutes());
		assertEquals(0, test.getSeconds());
	}
	@Test
	public void addMinutesTestNewHour() {
		OOSCDateTime test = new OOSCDateTime();
		test.setTime(4, 55, 0);
		test.addMinutes(20);
		assertEquals(15, test.getMinutes());
		assertEquals(5, test.getHours());
		assertEquals(0, test.getSeconds());
	}
		
		
	}
	

