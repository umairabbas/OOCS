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
		assertEquals("00:00:00", test.getTime());
	}
	
	
	
	@Test(expected = java.lang.Error.class)
	public void setHours() {
		OOSCDateTime test = new OOSCDateTime();
		test.setHours(24);
		
		
	}
	

	

	@Test(expected = java.lang.Error.class)
	public void addMinutesZero() {
		OOSCDateTime test = new OOSCDateTime();
		test.addMinutes(0);
	}
	
/*	@Test
	public void addMinutesTest() {
		OOSCDateTime test = new OOSCDateTime();
		test.setTime(4, 0, 0);
		test.addMinutes(1);
		assertEquals(4, test.getHours());
		assertEquals(1, test.getMinutes());
		assertEquals(0, test.getSeconds());
	}*/
	
	public void addMinutesTestNewHour() {
		OOSCDateTime test = new OOSCDateTime();
		test.setTime(4, 55, 0);
		test.addMinutes(20);
		assertEquals(15, test.getMinutes());
		assertEquals(5, test.getHours());
		assertEquals(0, test.getSeconds());
	}
	

//	public void addMinutesLoop(){
//		OOSCDateTime test = new OOSCDateTime();
//		for (int i = 0; i < 24; i++) {
//			for (int j = 0; j < 60; j++) {
//				for (int k = 0; k < 60; k++) {
//		test.setTime(i, j, k);
//		test.addMinutes(20);
//		assertEquals(15, test.getMinutes());
//		assertEquals(5, test.getHours());
//		assertEquals(0, test.getSeconds());
//				}
				
		
	}
	

