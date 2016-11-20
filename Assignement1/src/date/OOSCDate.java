package date;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.lang.IllegalArgumentException;

/**
 * OCL: The invariant has to be respected
 * 
 * @invariant 0 <= year && 1 <= day <= month.getNumberOfDays(year);
 * 
 */
public class OOSCDate implements DateInterface, Cloneable {

	/* ############################################# */
	/* ############################################# */
	/* Attributes */

	private int day;
	private Month month;
	private int year;

	/* ############################################# */
	/* ############################################# */
	/* Constructor */

	/**
	 * Construct a new date
	 * 
	 * @param year
	 *            The year (positive or null)
	 * @param month
	 *            The month
	 * @param day
	 *            The day, how has to correspond to the month and the year (0 <
	 *            day < 32)
	 * @throws assertionError
	 */
	public OOSCDate(int year, Month month, int day) {
		//assert (checkDate(year, month, day)) : "Date not valide";

		this.year = year;
		this.month = month;
		this.day = day;

		//assert (this.year == year) : "Someting went wrong is the year's instantation";
		//assert (this.month == month) : "Someting went wrong is the month's instantation";
		//assert (this.day == day) : "Someting went wrong is the day's instantation";
		//assert (invariant()) : "Date not valide";
	}

	/**
	 * Construct a new date
	 * 
	 * @param year
	 *            The year (positive or null)
	 * @param month
	 *            The month (1 for January and 12 for December)
	 * @param day
	 *            The day, how has to correspond to the month and the year (0 <
	 *            day < 32)
	 * @throws assertionError
	 */
	public OOSCDate(int year, int month, int day) {
		//assert (checkDate(year, month, day)) : "Date not valide";

		Month month_ = Month.month(month);
		this.year = year;
		this.month = month_;
		this.day = day;

		//assert (this.year == year) : "Someting went wrong is the year's instantation";
		//assert (this.month.getValue() == month) : "Someting went wrong is the month's instantation";
		//assert (this.day == day) : "Someting went wrong is the day's instantation";
		//assert (invariant()) : "Date not valide";
	}

	/**
	 * Construct the 1th January 0000 date.
	 */
	public OOSCDate() {
		this.year = 0;
		this.month = Month.JANUARY;
		this.day = 1;

		//assert (this.year == 0) : "Someting went wrong is the year's instantation";
		//assert (this.month == Month.JANUARY) : "Someting went wrong is the month's instantation";
		//assert (this.day == 1) : "Someting went wrong is the day's instantation";
		//assert (invariant()) : "Date not valide";
	}

	/* ############################################# */
	/* ############################################# */
	/* Set functions */

	@Override
	public void setDate(int year, int month, int day) {
		//assert (invariant()) : "The invariante is not respected";
		//assert (checkDate(year, month, day)) : "Date not valide";

		Month month_ = Month.month(month);
		setDate(year, month_, day);

		//assert (this.year == year) : "Someting went wrong is the year's assigment";
		//assert (this.month.getValue() == month) : "Sometin went wrong is the month's assigment";
		//assert (this.day == day) : "Someting went wrong is the day's assigment";
		//assert (invariant()) : "The invariante is not respected";
	}

	@Override
	public void setDate(int year, Month month, int day) {
		//assert (invariant()) : "The invariante is not respected";
		//assert (checkDate(year, month, day)) : "Date not valide";

		setYear(year);
		setMonth(month);
		setDay(day);

		//assert (this.year == year) : "Someting went wrong is the year's assigment";
		//assert (this.month == month) : "Someting went wrong is the month's assigment";
		//assert (this.day == day) : "Someting went wrong is the day's assigment";
		//assert (invariant()) : "The invariante is not respected";
	}

	@Override
	public void setYear(int year) {
		//assert (invariant()) : "The invariante is not respected";
		//assert (0 <= year) : "The year has to be greater or equal to 0";

		if (this.month == Month.FEBRUARY && this.day == 29 && !DateInterface.isLeapYear(year)) {
			this.day = 28;
		}
		this.year = year;

		//assert (this.year == year) : "Someting went wrong is the year's assigment";
		//assert (invariant()) : "The invariante is not respected";
	}

	@Override
	public void setMonth(int month) {
		//assert (invariant()) : "The invariante is not respected";
		Month month_ = Month.month(month);

		setMonth(month_);

		//assert (this.month.getValue() == month) : "Someting went wrong is the month's assigment";
		//assert (invariant()) : "The invariante is not respected";
	}

	@Override
	public void setMonth(Month month) {
		//assert (invariant()) : "The invariante is not respected";

		if (this.day > month.getNumberOfDays(year)) {
			this.day = month.getNumberOfDays(year);
		}
		this.month = month;

		//assert (this.month == month) : "Someting went wrong is the month's assigment";
		//assert (invariant()) : "The invariante is not respected";
	}

	@Override
	public void setDay(int day) {
		//assert (invariant()) : "The invariante is not respected";
		//assert (checkDate(year, month, day)) : "The day is not valide for this month and year";

		this.day = day;

		//assert (this.day == day) : "Someting went wrong is the day assigment";
		//assert (invariant()) : "The invariante is not respected";
	}

	/* ############################################# */
	/* Get functions */

	@Override
	public int getYear() {
		//assert (invariant()) : "The invariante is not respected";

		int y = this.year;

		//assert (0 <= y) : "The returned year is not correct";
		//assert (invariant()) : "The invariante is not respected";

		return y;
	}

	@Override
	public int getMonth() {
		//assert (invariant()) : "The invariante is not respected";

		int m = this.month.getValue();

		//assert (0 < m && m < 13) : "The returned month is not correct";
		//assert (invariant()) : "The invariante is not respected";

		return m;
	}

	@Override
	public int getDay() {
		//assert (invariant()) : "The invariante is not respected";

		int d = this.day;

		//assert (checkDate(year, month, d)) : "The returned day is not correct";
		//assert (invariant()) : "The invariante is not respected";

		return d;
	}

	/* ############################################# */
	/* Add functions */

	@Override
	public void addDays(int daysToAdd) {
		//assert (invariant()) : "The invariante is not respected";
		//assert (0 <= daysToAdd) : "You can not add negative days";
		DateInterface pre = (DateInterface) this.clone();
		DateInterface newDate = DateInterface.toDateInterface(toNumberOfDays() + daysToAdd);
		setDate(newDate.getYear(), newDate.getMonth(), newDate.getDay());

		
		//assert (this.toNumberOfDays() == pre.toNumberOfDays() + daysToAdd) : "OCL conditions not respected";
		//assert (invariant()) : "The invariante is not respected";
	}

	@Override
	public void addMonths(int monthsToAdd) {
		//assert (invariant()) : "The invariante is not respected";
		//assert (0 <= monthsToAdd) : "You can not add negative month";
		DateInterface pre = (DateInterface) this.clone();

	
		int yearToAdd = (this.month.getValue() + monthsToAdd - 1) / 12;
		int newMonth = (this.month.getValue() + monthsToAdd - 1) % 12 + 1;

		addYears(yearToAdd);
		
		setMonth(newMonth);

		//assert (this.toNumberOfMonths() == pre.toNumberOfMonths() + monthsToAdd) : "OCL conditions not repected";
		//assert (invariant()) : "The invariante is not respected";
	}

	@Override
	public void addYears(int yearsToAdd) {
		//assert (invariant()) : "The invariante is not respected";
		//assert (0 <= yearsToAdd) : "You can not add negative year";
		DateInterface pre = (DateInterface) this.clone();

		setYear(year + yearsToAdd);

		//assert (this.getYear() == pre.getYear() + yearsToAdd) : "OCL conditions not repected";
		//assert (invariant()) : "The invariante is not respected";
	}

	/* ############################################# */
	/* remove functions */

	@Override
	public void removeDays(int daysToRemove) {
		//assert (invariant()) : "The invariante is not respected";
		//assert (0 <= daysToRemove) : "You can not remove negative amount of days";
		//assert (0 <= toNumberOfDays() - daysToRemove) : "You can not remove more days that there is till J.C";
		DateInterface pre = (DateInterface) this.clone();
		
		
		DateInterface newDate = DateInterface.toDateInterface(toNumberOfDays() - daysToRemove);
		setDate(newDate.getYear(), newDate.getMonth(), newDate.getDay());

		
		assert(this.toNumberOfDays() == pre.toNumberOfDays() - daysToRemove): "OCL conditions not repected";
		//assert (invariant()) : "The invariante is not respected";
	}

	@Override
	public void removeMonths(int monthsToRemove) {
		//assert (invariant()) : "The invariante is not respected";
		//assert (0 <= monthsToRemove) : "You can not remove negative amount of days";
		//assert (0 <= toNumberOfMonths() - monthsToRemove) : "You can not remove more month that there is till J.C";
		DateInterface pre = (DateInterface) this.clone();
		
		
		int nbMonth = toNumberOfMonths() - monthsToRemove;
		removeYears(nbMonth / 12);
		int newValueMonth = nbMonth % 12;
		setMonth(newValueMonth);

		
		assert(this.toNumberOfMonths() == pre.toNumberOfMonths() - monthsToRemove): "OCL conditions not repected";
		//assert (invariant()) : "The invariante is not respected";
	}

	@Override
	public void removeYears(int yearsToRemove) {
		//assert (invariant()) : "The invariante is not respected";
		//assert (0 < yearsToRemove) : "The yearsToAdd as to be greater than 0";
		//assert (0 <= year - yearsToRemove);
		DateInterface pre = (DateInterface) this.clone();
		
		setYear(year - yearsToRemove);

		assert(this.getYear() == pre.getYear() - yearsToRemove): "OCL conditions not repected";
		//assert (invariant()) : "The invariante is not respected";
	}

	/* ############################################# */
	/* Certain-time-between functions */

	@Override
	public int timeBetween(int type, DateInterface date1) throws IllegalArgumentException {
		//assert (invariant()) : "The invariante is not respected";
		//assert (type == DATETYPE_DAY || type == DATETYPE_MONTH || type == DATETYPE_YEAR) : "type is wrong";

		if (type == DATETYPE_DAY) {
			int dayBetween = DateInterface.daysBetween(this, date1);

			DateInterface test = (DateInterface) this.clone();
			test.addDays(dayBetween);
			//assert (test.equals(date1)) : "Addind the days between the 2 dates at this, should be equal to data1";
			//assert (invariant()) : "The invariante is not respected";

			return dayBetween;
		} else if (type == DATETYPE_MONTH) {
			int monthBetween = DateInterface.monthBetween(this, date1);

			DateInterface test = (DateInterface) this.clone();
			test.addMonths(monthBetween);
			//assert (test.equals(date1)) : "Addind the month between the 2 dates at this, should be equal to data1";
			//assert (invariant()) : "The invariante is not respected";

			return monthBetween;
		} else if (type == DATETYPE_YEAR) {
			int yearBetween = DateInterface.yearBetween(this, date1);

			DateInterface test = (DateInterface) this.clone();
			test.addYears(yearBetween);
			//assert (test.equals(date1)) : "Addind the year between the 2 dates at this, should be equal to data1";
			//assert (invariant()) : "The invariante is not respected";

			return yearBetween;
		} else {
			throw new IllegalArgumentException("Type have to 0, 1 or 2");
		}
	}

	/* ############################################# */
	/* Other function */

	@Override
	public void synchWithUTCTimeserver() throws Exception {
		// get URL content
		URL url = new URL("http://www.timeapi.org/utc/now");
		URLConnection conn = url.openConnection();

		// open the stream and put it into BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		String inputLine = br.readLine(); // throws IOException
		if (inputLine == null) {
			throw new NullPointerException("No data in http://www.timeapi.org/utc/now");
		} else {
			String[] format = inputLine.split("T");
			if (format.length < 2) {
				throw new IllegalArgumentException("The format is invalid: y-m-dT...");
			} else {
				String[] ymd = format[0].split("-");
				if (ymd.length != 3) {
					throw new IllegalArgumentException("The format is invalid: y-m-dT...");
				} else {
					int y = Integer.parseInt(ymd[0]);
					int m = Integer.parseInt(ymd[1]);
					int d = Integer.parseInt(ymd[2]);

					setDate(y, m, d);
				}
			}
		}

		br.close();

	}

	/* ############################################# */
	/* Convert into an other representation */

	@Override
	public int toNumberOfDays() {
		//assert (invariant()) : "The invariant is not respected";
		int nbDays = 0;

		// add number of days from year 0 till this year
		for (int y = 0; y < year; ++y) {
			nbDays += DateInterface.numberOfDaysInYear(y);
		}
		// add number of days from January 0 till this month
		for (int m = 1; m < month.getValue(); ++m) {
			nbDays += Month.month(m).getNumberOfDays(year);
		}
		// add number of days in this month
		nbDays += day;

		
		

		//DateInterface test = DateInterface.toDateInterface(nbDays); use!!!! 
		//assert (this.equals(test)) : "toNumberOfDays() and toDateInterface(...) sould be inverse function";
		//assert (invariant()) : "The invariant is not respected";

		return nbDays;
	}

	@Override
	public int toNumberOfMonths() {
		//assert (invariant()) : "The invariant is not respected";
		return year * 12 + getMonth() - 1;
	}

	/* ############################################# */
	/* ############################################# */
	/* Invariant function */
	/**
	 * OCL: Assume nothing: immutable
	 * 
	 * 
	 * Check if the date is valid
	 * 
	 * @param year
	 *            The year (positive or null)
	 * @param month
	 *            The month
	 * @param day
	 *            The day how as to correspond this the month and the year
	 * @return True if the date is valid, false otherwise.
	 */
	public static Boolean checkDate(int year, Month month, int day) {
		return 0 <= year && 1 <= day && day <= month.getNumberOfDays(year);
	}

	/**
	 * OCL: Assume nothing: immutable
	 * 
	 * 
	 * Check if the date is valid
	 * 
	 * @param year
	 *            The year (positive or null)
	 * @param month
	 *            The month (1 for January and 12 for December)
	 * @param day
	 *            The day how as to correspond this the month and the year
	 * @return True if the date is valid, false otherwise.
	 */
	public static Boolean checkDate(int year, int month, int day) {
		Month month_ = Month.month(month);
		return checkDate(year, month_, day);
	}

	/**
	 * OCL: Assume nothing: immutable
	 * 
	 * 
	 * Check if the current date is valid
	 * 
	 * @return True if the current date is valid, false otherwise.
	 */
	private Boolean invariant() {
		return checkDate(this.year, this.month, this.day);
	}

	/* ############################################# */
	/* ############################################# */
	/* Common object function */

	@Override
	public Object clone() {
		//assert (invariant()) : "The invariante is not respected";
		OOSCDate clone = new OOSCDate(year, month, day);

		//assert (getYear() == clone.getYear()) : "Problem when cloning the year";
		//assert (getMonth() == clone.getMonth()) : "Problem when cloning the month";
		//assert (getDay() == clone.getDay()) : "Problem when cloning the day";
		//assert (invariant()) : "The invariante is not respected";

		return clone;
	}

	@Override
	public boolean equals(Object other) {
		//assert (invariant()) : "The invariante is not respected";

		if (other == null)
			return false;
		else if (other == this)
			return true;
		else if (!(other instanceof OOSCDate))
			return false;
		else {
			OOSCDate oos = (OOSCDate) other;
			Boolean isEqual = true;
			isEqual = isEqual && this.year == oos.year;
			isEqual = isEqual && this.month.equals(oos.month);
			isEqual = isEqual && this.day == oos.day;

			//assert (!isEqual || (getYear() == oos.getYear())) : "The 2 years has to be equal";
			//assert (!isEqual || (getMonth() == oos.getMonth())) : "The 2 months has to be equal";
			//assert (!isEqual || (getDay() == oos.getDay())) : "The 2 days has to be equal";
			//assert (invariant()) : "The invariante is not respected";
			return isEqual;
		}
	}

	@Override
	public String toString() {
		//assert (invariant()) : "The invariante is not respected";
		return "Year: " + year + ",Month: " + month.toString() + ",Day: " + day;
	}

	/**
	 * Assert that the given date is equal to the input date.
	 *
	 * @param year
	 *            year, from 1 to MAX_SUPPORTED_YEAR.
	 * @param month
	 *            month, from 1 to 12.
	 * @param day
	 *            day, from 1 to the maximal day in the input month with respect
	 *            to the input year.
	 */
	protected void assertEqualsDate(int year, int month, int day) {
		assert isEqualsDate(year, month, day);
	}

	/**
	 * Determine if the given date is equal to the input date.
	 *
	 * @param year
	 *            year, from 1 to MAX_SUPPORTED_YEAR.
	 * @param month
	 *            month, from 1 to 12.
	 * @param day
	 *            day, from 1 to the maximal day in the input month with respect
	 *            to the input year.
	 * @return true if the given date is equal to the input date and false
	 *         otherwise.
	 */
	protected boolean isEqualsDate(int year, int month, int day) {
		return getYear() == year && getMonth() == month && getDay() == day;
	}

}
