package date;

public interface DateInterface {

	/**
	 * OCL: The date has to be valid
	 * 
	 * @invariant checkDate(year, month, day)
	 */

	public static final int DATETYPE_YEAR = 0;
	public static final int DATETYPE_DAY = 2;
	public static final int DATETYPE_MONTH = 1;

	/* ############################################# */
	/* ############################################# */
	/* Set functions */

	/**
	 * OCL: setDate(int year, int month, int day) Assume that the date is
	 * correct
	 * 
	 * @pre checkDate(year, month, day)
	 * @post getYear() == year
	 * @post getMonth() == month
	 * @post getDay() == day
	 * 
	 * 
	 *       Set a new date (has be valid)
	 * 
	 * @param year
	 *            The year (positive or null)
	 * @param month
	 *            The month (1 for January and 12 for December)
	 * @param day
	 *            The day, how has to correspond to the month and the year (0 <
	 *            day < 32)
	 */
	public void setDate(int year, int month, int day);

	/**
	 * OCL: setDate(int year, Month month, int day) Assume that the date is
	 * correct
	 * 
	 * @pre checkDate(year, month, day)
	 * @post getYear() == year
	 * @post getMonth() == month.getValue()
	 * @post getDay() == day
	 * 
	 * 
	 *       Set a new date (has be valid)
	 * 
	 * @param year
	 *            The year (positive or null)
	 * @param month
	 *            The month
	 * @param day
	 *            The day, how has to correspond to the month and the year (0 <
	 *            day < 32)
	 */
	public void setDate(int year, Month month, int day);

	/**
	 * 
	 * OCL: setYear(int year) Assume that the year is positive or null
	 * 
	 * @pre 0 <= year
	 * @post getYear() == year
	 * @post getMonth() == getMonth()@pre
	 * 
	 * 
	 *       Set the year. Look that the days will automatically be readjusted
	 *       to correspond to the new month in case of invalidity. Example: 29
	 *       February 2000 and set to 1999-> 28 February 1999
	 * 
	 * @param month
	 *            The month (1 for January and 12 for December)
	 */
	public void setYear(int year);

	/**
	 * OCL: setMonth(int month) Assume that the month is between 1 (included)
	 * and 12 (included)
	 * 
	 * @pre 0 < month < 13
	 * @post getYear() == getYear()@pre
	 * @post getMonth() == month
	 * 
	 * 
	 *       Set the month. Look that the days will automatically be readjusted
	 *       to correspond to the new month in case of invalidity. Example: 31
	 *       August and set to September-> 30 September
	 * 
	 * @param month
	 *            The month (1 for January and 12 for December)
	 */
	public void setMonth(int month);

	/**
	 * OCL: setMonth(Month month)
	 * 
	 * @post getYear() == getYear()@pre
	 * @post getMonth() == month.getValue
	 * 
	 * 
	 *       Set the month. Look that the days will automatically be readjusted
	 *       to correspond to the new month in case of invalidity. Example: 31
	 *       August and set to September-> 30 September
	 * 
	 * @param month
	 *            The month
	 */
	public void setMonth(Month month);

	/**
	 * OCL: setDay(int day) Assume that the day is valid for the current day and
	 * year
	 * 
	 * @pre 0 < month <= month.getNumberOfDays(year)
	 * @post getYear() == year
	 * @post getMonth() == getMonth()@pre
	 * @post getDay() == day
	 * 
	 * 
	 *       Set the day. Has to correspond to the current year and month
	 * 
	 * @param day
	 *            The day, how has to correspond to the month and the year (0 <
	 *            day < 32)
	 */
	public void setDay(int day);

	/* ############################################# */
	/* Get functions */

	/**
	 * OCL: Assume nothing: immutable
	 * 
	 * 
	 * Getter for year
	 * 
	 * @return The year (positive or null)
	 */
	public int getYear();

	/**
	 * OCL: Assume nothing: immutable
	 * 
	 * Getter for the month
	 * 
	 * @return The month (1 for January and 12 for December)
	 */
	public int getMonth();

	/**
	 * OCL: Assume nothing: immutable
	 * 
	 * Getter for the day
	 * 
	 * @return The day (0 < day < 32)
	 */
	public int getDay();

	/* ############################################# */
	/* Add functions */

	/**
	 * OCL addDays(int daysToAdd) Assume that the number of day to add is positive or null
	 * 
	 * @pre 0 <= daysToAdd
	 * @post toNumberOfDays() == toNumberOfDays()@pre + daysToAdd
	 * 
	 * Add a number of day to this date
	 * 
	 * @param daysToAdd
	 *            The number of days you want to add to this date. Has to be
	 *            greater or equal to zero
	 */
	public void addDays(int daysToAdd);

	/**
	 * OCL addMonths(int monthsToAdd) Assume that the number of month to add is positive or null
	 * 
	 * @pre 0 <= monthsToAdd
	 * @post toNumberOfMonths() = toNumberOfMonths()@pre + monthsToAdd
	 * 
	 * Add a number of month to this date. Look that the days will automatically
	 * be readjusted to correspond to the new month in case of invalidity.
	 * Example: 31 August + 1 month -> 30 September
	 * 
	 * @param monthsToAdd
	 *            The number of month you want to add to this date. Has to be
	 *            greater or equal to zero
	 */
	public void addMonths(int monthsToAdd);

	/**
	 * OCL addYears(int yearsToAdd) Assume that the number of year to add is positive or null
	 * 
	 * @pre 0 <= yearsToAdd
	 * @post getYear() = getYear()@pre + yearsToAdd
	 * 
	 * Add a number of year to this date. Look that the days will automatically
	 * be readjusted to correspond to the new year in case of invalidity.
	 * Example: 29 February 2000 + 1 year -> 28 February 2001
	 * 
	 * @param yearsToAdd
	 *            The number of year you want to add to this date. Has to be
	 *            greater or equal to zero
	 */
	public void addYears(int yearsToAdd);

	/* ############################################# */
	/* remove functions */

	/**
	 * OCL: removeDays(int daysToRemove) Assume that the number of day to remove is positive or null
	 * 
	 * @pre 0 <= daysToRemove
	 * @post removeDays() == removeDays()@pre - daysToAdd
	 * 
	 * 
	 * Remove a number of day to this date
	 * 
	 * @param daysToRemove
	 *            The number of days you want to remove to this date. Has to be
	 *            greater or equal to zero
	 */
	public void removeDays(int daysToRemove);

	/**
	 * OCL: removeMonths(int monthsToRemove) Assume that the number of month to remove is positive or null
	 * 
	 * @pre 0 <= monthsToRemove
	 * @post removeMonths() == removeMonths()@pre - monthsToRemove
	 * 
	 * 
	 * Remove a number of month to this date. Look that the days will
	 * automatically be readjusted to correspond to the new month in case of
	 * invalidity. Example: 31 July - 1 month -> 30 June
	 * 
	 * @param monthsToRemove
	 *            The number of month you want to remove to this date. Has to be
	 *            greater or equal to zero
	 */
	public void removeMonths(int monthsToRemove);

	/**
	 * OCL: removeYears(int yearsToRemove) Assume that the number of year to remove is positive or null
	 * 
	 * @pre 0 <= yearsToRemove
	 * @post removeYears() == removeYears()@pre - yearsToRemove
	 * 
	 * 
	 * Remove a number of year to this date. Look that the days will
	 * automatically be readjusted to correspond to the new year in case of
	 * invalidity. Example: 29 February 2000 - 1 year -> 28 February 1999
	 * 
	 * @param yearsToAdd
	 *            The number of year you want to remove to this date. Has to be
	 *            greater or equal to zero
	 */
	public void removeYears(int yearsToRemove);

	/* ############################################# */
	/* Certain-time-between functions */

	/**
	 * OCL: timeBetween(int type, DateInterface date1) Assume that the type is correct (1, 2 or 3)
	 * 
	 * @pre type == 0 || type == 1 || type == 2
	 * @post if(type == 0) return == yearBetween(this, date1))
	 * @post if(type == 1) return == monthBetween(this, date1))
	 * @post if(type == 2) return == dayBetween(this, date1))
	 * 
	 * 
	 * Compute time between the current dates in terms of day, month or year.
	 * 
	 * @param type
	 *            1: Compute the difference in year, 2: Compute the difference
	 *            in months, 3: Compute the difference in years
	 * @param date1
	 *            The date you want to compute the difference with.
	 * @return the number of day, month or year between this and date1.
	 *         Respectively same result as daysBetween(this, date1),
	 *         monthBetween(this, date1), yearBetween(this, date1)
	 */
	public int timeBetween(int type, DateInterface date1) throws IllegalArgumentException;

	/* ############################################# */
	/* Other function */


	/**
	 * OCL: Assume nothing: immutable
	 * 
	 * @post checkDate(year, month, day) and 
	 * 
	 * Synchronize this with the server date available at address
	 * http://www.timeapi.org/utc/now
	 *
	 * @throws Exception: if no connection: IOException
	 * if no access to the data: NullPointerException
	 * if the format is not correct: IllegalArgumentException
	 * if the parsing get wrong: NumberFormatException
	 */
	public void synchWithUTCTimeserver() throws Exception;

	/* ############################################# */
	/* Convert into an other representation */
	
	/**
	 * OCL: Assume nothing: immutable
	 * 
	 * @post @pre == toDateInterface(return)
	 * 
	 * 
	 * Transform the current date (this) in the number of day till year 0
	 * 0000. Example: toNumberOfDays 1 January 0000 -> 1
	 * 
	 * @return the number of day till year 0
	 */
	public int toNumberOfDays();

	/**
	 * OCL: Assume nothing: immutable
	 * 
	 * 
	 * Transform the current date (this) in the number of month till year 0
	 * 0000. Example: toNumberOfMonths 1 January 0000 -> 1 
	 * toNumberOfMonths 31 January 0000 -> 1
	 * 
	 * @return the number of month till 1 January 0000
	 */
	public int toNumberOfMonths();

	/* ############################################# */
	/* ############################################# */
	/* Static functions */

	/**
	 * OCL: toDateInterface(int numberOfDays) Assume the numberOfDays is
	 * positive
	 * 
	 * @pre 0< numberOfDays
	 * @post return.toNumberOfDays() = numberOfDays
	 * 
	 * 
	 *       Transform the number of day till year 0 in DateInterface
	 * 
	 * @param numberOfDays
	 *            number of day till year 0 (1 January 0000 == 1)
	 * @return the corresponding DateInterface
	 */
	public static DateInterface toDateInterface(int numberOfDays) {
		assert (0 < numberOfDays) : "The number of day has to be positif (not null)";

		DateInterface date;
		int year = 0;
		Month month = Month.JANUARY;

		while (numberOfDays > numberOfDaysInYear(year)) {
			numberOfDays -= numberOfDaysInYear(year);
			year++;
		}
		while (numberOfDays > month.getNumberOfDays(year)) {
			numberOfDays -= month.getNumberOfDays(year);
			month = month.nextMonth();
		}
		
		date = new OOSCDate(year, month, numberOfDays);

		//assert (date.toNumberOfDays() == numberOfDays) : "toNumberOfDays() and toDateInterface(...) should be inverse function";
		return date;
	}

	/* ############################################# */
	/* Functions to calculate time between two dates */

	/**
	 * OCL: Assume nothing: immutable
	 * 
	 * @post date1.addDays(return) == date2.toNumberOfDays()
	 * 
	 * 
	 *       Compute the number of day between two dates.
	 * 
	 * @param date1
	 *            The first date
	 * @param date2
	 *            The second date
	 * @return the number of day between two dates. If date1 is greater than
	 *         date2, then the result will be positive. If the two days are
	 *         identical, the result will be 0, and if date1 is smaller than
	 *         date2, then the result will be negative.
	 */
	public static int daysBetween(DateInterface date1, DateInterface date2) {
		int daysBt = date1.toNumberOfDays() - date2.toNumberOfDays();

		if(daysBt >= 0){
			DateInterface test = DateInterface.clone(date1);
			test.addDays(daysBt);
			assert (test.equals(date2)) : "Addind the days between the 2 dates at date1, should be equal to data2";
		}
		else{
			DateInterface test = DateInterface.clone(date2);
			test.addDays(-daysBt);
			assert (test.equals(date1)) : "Addind the days between the 2 dates at date1, should be equal to data2";
		}
		
		return daysBt;
	}

	/**
	 * OCL: Assume nothing: immutable
	 * 
	 * 
	 *       Compute the number of month between two dates.
	 * 
	 * @param date1
	 *            The first date
	 * @param date2
	 *            The second date
	 * @return the number of month between two dates. If date1 is greater than
	 *         date2, then the result will be positive. If the two days have the
	 *         same month and the same year, the result will be 0, and if date1
	 *         is smaller than date2, then the result will be negative. Note
	 *         that days have no importance in this computation. Example:
	 *         monthBetween 28 January 2000 and 4 January 2000 -> 0
	 */
	public static int monthBetween(DateInterface date1, DateInterface date2) {
		int monthBt = date1.toNumberOfMonths() - date2.toNumberOfMonths();
		assert (date1.toNumberOfMonths() == monthBt + date2.toNumberOfMonths()) : "Adding the month between the 2 dates at date1, should be equal to data2";
		return monthBt;
	}

	/**
	 * OCL: Assume nothing: immutable
	 * 
	 * @post date1.addYear(return) == date2.toNumberOfDays()
	 * 
	 * 
	 *       Compute the number of year between two dates.
	 * 
	 * @param date1
	 *            The first date
	 * @param date2
	 *            The second date
	 * @return the number of year between two dates. If date1's year is greater
	 *         than date2's year, then the result will be positive. If the two
	 *         days have the same month and the same year, the result will be 0,
	 *         and if date1's year is smaller than date2's year, then the result
	 *         will be negative. Note that days and months have no importance in
	 *         this computation. Example: yearBetween 28 January 2000 and 4 June
	 *         2000 -> 0
	 */
	public static int yearBetween(DateInterface date1, DateInterface date2) {
		int yearBetween = date1.getYear() - date1.getYear();

		DateInterface test = DateInterface.clone(date1);
		test.addYears(yearBetween);
		assert (test.equals(date2)) : "Addind the year between the 2 dates at date1, should be equal to data2";

		return yearBetween;
	}

	/* ############################################# */
	/* ############################################# */
	/* Functions about leap */

	/**
	 * OCL: isLeapYear(int year) assume that the year is positive or null
	 * 
	 * @pre 0 <= year
	 * 
	 * 
	 *      Compute if the year is leap.
	 * 
	 * @param year
	 *            the year (positive or null)
	 * @return True if the year is leap, false otherwise
	 */
	public static Boolean isLeapYear(int year) {
		assert (0 <= year) : "Negative year are not aload";
		
		Boolean isLeap = (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));

		assert (isLeap == (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)));
		assert (0 <= year) : "Negative year are not aload";
		return isLeap;
	}

	/**
	 * OCL: isLeapYear(int year) assume that the year is positive or null
	 * 
	 * @pre 0 <= year
	 * 
	 * 
	 *      Compute the number of days in year. 366 is this year is leap, 365
	 *      otherwise
	 * 
	 * @param year
	 *            the year (positive or null)
	 * @return the number of days in this year
	 */
	public static int numberOfDaysInYear(int year) {
		assert (0 <= year) : "Negative year are not aload";

		if (isLeapYear(year))
			return 366;
		else
			return 365;
	}

	/* ############################################# */
	/* ############################################# */
	/* Other functions */



	/**
	 * OCL: Assume nothing: immutable
	 * 
	 * 
	 * Clone other. No side effect
	 * 
	 * @param other
	 *            The DateInterface that you want to clone
	 * @return
	 */
	public static DateInterface clone(DateInterface other) {
		DateInterface clone = new OOSCDate(other.getYear(), other.getMonth(), other.getDay());
		return clone;
	}

	@Override
	public String toString();

}
