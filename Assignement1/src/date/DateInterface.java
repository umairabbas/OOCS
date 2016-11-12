package date;
public interface DateInterface {
 public static final int DATETYPE_YEAR = 0;
 public static final int DATETYPE_DAY = 2;
 public static final int DATETYPE_MONTH = 1;
 
 /* ############################################# */
 /* ############################################# */
 /* Set functions */
 
 /**
  * Set a new date (has be valid)
  * @param year The year (positive or null)
  * @param month The month (1 for January and 12 for December)
  * @param day The day, how has to correspond to the month and the year  (0 < day < 32)
  */
 public void setDate(int year, int month, int day);
 
 /**
  * Set a new date (has be valid)
  * @param year The year (positive or null)
  * @param month The month
  * @param day The day, how has to correspond to the month and the year  (0 < day < 32)
  */
 public void setDate(int year, Month month, int day);
 
 /**
  * Set the year. Look that the days will automatically be readjusted to correspond 
  * to the new month in case of invalidity. Example: 
  * 29 February 2000 and set to 1999-> 28 February 1999
  * @param month The month (1 for January and 12 for December)
  */ 
 public void setYear(int year);
 /**
  * Set the month. Look that the days will automatically be readjusted to correspond 
  * to the new month in case of invalidity. Example: 
  * 31 August and set to September-> 30 September
  * @param month The month (1 for January and 12 for December)
  */
 public void setMonth(int month);
 /**
  * Set the month. Look that the days will automatically be readjusted to correspond 
  * to the new month in case of invalidity. Example: 
  * 31 August and set to September-> 30 September
  * @param month The month
  */
 public void setMonth(Month month);
 /**
  * Set the day. Has to correspond to the current year and  month
  * @param day The day, how has to correspond to the month and the year (0 < day < 32)
  */
 public void setDay(int day);
 
 /* ############################################# */
 /* Get functions */
 
 /**
  * Getter for year 
  * @return The year (positive or null)
  */
 public int getYear();
 /**
  * Getter for the month
  * @return The month (1 for January and 12 for December)
  */
 public int getMonth();
 /**
  * Getter for the day
  * @return The day (0 < day < 32)
  */
 public int getDay();
 
 
 /* ############################################# */
 /* Add functions */
 
 /**
  * Add a number of day to this date
  * @param daysToAdd The number of days you want to add to this date. Has to be greater or equal to zero
  */
 public void addDays(int daysToAdd);
 /**
  * Add a number of month to this date. Look that the days will automatically be readjusted to correspond 
  * to the new month in case of invalidity. Example: 
  * 31 August + 1 month -> 30 September
  * @param monthsToAdd The number of month you want to add to this date. Has to be greater or equal to zero
  */
 public void addMonths(int monthsToAdd);
 /**
  * Add a number of year to this date. Look that the days will automatically be readjusted to correspond 
  * to the new year in case of invalidity. Example: 
  * 29 February 2000 + 1 year -> 28 February 2001
  * @param yearsToAdd The number of year you want to add to this date. Has to be greater or equal to zero
  */
 public void addYears(int yearsToAdd);
 
 
 /* ############################################# */
 /* remove functions */
 
 /**
  * Remove a number of day to this date
  * @param daysToRemove The number of days you want to remove to this date. Has to be greater or equal to zero
  */
 public void removeDays(int daysToRemove);
 /**
  * Remove a number of month to this date. Look that the days will automatically be readjusted to correspond 
  * to the new month in case of invalidity. Example: 
  * 31 July - 1 month -> 30 June
  * @param monthsToRemove The number of month you want to remove to this date. Has to be greater or equal to zero
  */
 public void removeMonths(int monthsToRemove);
 /**
  * Remove a number of year to this date. Look that the days will automatically be readjusted to correspond 
  * to the new year in case of invalidity. Example: 
  * 29 February 2000 - 1 year -> 28 February 1999
  * @param yearsToAdd The number of year you want to remove to this date. Has to be greater or equal to zero
  */
 public void removeYears(int yearsToRemove);
 
 
 /* ############################################# */
 /* Certain-time-between functions */
 
 /**
  * Compute the number of day between two dates.
  * @param date1 The first date
  * @param date2 The second date
  * @return the number of day between two dates. If date1 is greater than date2, then the result will 
  * be positive. If the two days are identical, the result will be 0, and if date1 is smaller than date2, 
  * then the result will be negative.
  */
 public int daysBetween(DateInterface date1, DateInterface date2);
 /**
  * Compute the number of month between two dates.
  * @param date1 The first date
  * @param date2 The second date
  * @return the number of month between two dates. If date1 is greater than date2, then the result will 
  * be positive. If the two days have the same month and the same year, the result will be 0, and if date1 is smaller than date2, 
  * then the result will be negative.
  * Note that days have no importance in this computation. Example:
  * monthBetween 28 January 2000 and 4 January 2000 -> 0
  */
 public int monthBetween(DateInterface date1, DateInterface date2); // added function
 /**
  * Compute the number of year between two dates.
  * @param date1 The first date
  * @param date2 The second date
  * @return the number of year between two dates. If date1's year is greater than date2's year, then the result will 
  * be positive. If the two days have the same month and the same year, the result will be 0, and if date1's year is 
  * smaller than date2's year, then the result will be negative.
  * Note that days and months have no importance in this computation. Example:
  * yearBetween 28 January 2000 and 4 June 2000 -> 0
  */
 public int yearBetween(DateInterface date1, DateInterface date2);
 
 /**
  * Compute time between the current dates in terms of day, month or year. 
  * @param type 1: Compute the difference in year, 2: Compute the difference in months, 3: Compute the difference in years 
  * @param date1 The date you want to compute the difference with. 
  * @return the number of day, month or year between this and date1. Respectively same result as 
  * daysBetween(this, date1), monthBetween(this, date1), yearBetween(this, date1)
  */
 public int timeBetween(int type, DateInterface date1);
 
 /* ############################################# */
/* Other function */
 
 /**
  * Synchronize this with the server date available at address http://www.timeapi.org/utc/now
  */
 public void synchWithUTCTimeserver();
 
 
 /* ############################################# */
 /* Convert into an other representation */
 
 //TODO: toNumberOfDays 1 January 0000 -> 0????
 /**
  * Transform the current date (this) in the number of day till 1 January 0000. Example: 
  * toNumberOfDays 1 January 0000 -> 1 
  * @return the number of day till 1 January 0000
  */
 public int toNumberOfDays();
//TODO: toNumberOfDays 1 January 0000 -> 0????
 /**
  * Transform the current date (this) in the number of month till 1 January 0000. Example: 
  * toNumberOfMonths 1 January 0000 -> 1 
  * toNumberOfMonths 31 January 0000 -> 1 
  * @return the number of month till 1 January 0000
  */
 public int toNumberOfMonths();
 
	/**
	 * Transform the number of day till the 1 January 0000 in DateInterface
	 * @param numberOfDays number of day till the 1 January 0000
	 * @return the corresponding DateInterface
	 */
	public static DateInterface toDateInterface(int numberOfDays){
		//assert(invariant()) : "The invariante is not respected";
		assert(0 < numberOfDays): "The number of day has to be positif (not null)";
		
		DateInterface date;
		int year = 0;
		Month month = Month.JANUARY;
		
		while(numberOfDays >= numberOfDaysInYear(year)){
			numberOfDays -= numberOfDaysInYear(year);
			year++;
		}
		while(numberOfDays >= month.getNumberOfDays(year)){
			numberOfDays -= month.getNumberOfDays(year);
			month = month.nextMonth();
		}

		date = new OOSCDate(year, month, numberOfDays);
		
		assert(date.toNumberOfDays() == numberOfDays): "toNumberOfDays() and toDateInterface(...) sould be inverse function";
		//assert(invariant()) : "The invariante is not respected";
		return date;
	}
 
 
 
 
 
	/* ############################################# */
	/* ############################################# */
	/* Functions about leap */
	
	/**
	 * Compute if the year is leap. 
	 * @param year the year (positive or null)
	 * @return True if the year is leap, false otherwise 
	 */
	public static Boolean isLeapYear(int year){ 
		assert(0 <= year): "Negative year are not aload";
		Boolean isLeap = ((year % 4 == 0) && (year % 100 == 0) && (year % 400 == 0));
		
		assert(isLeap == ((year % 4 == 0) && (year % 100 == 0) && (year % 400 == 0)));
		assert(0 <= year): "Negative year are not aload";
		return isLeap;
	}
	
	/**
	 * Compute the number of days in year. 366 is this year is leap, 365 otherwise
	 * @param year the year (positive or null)
	 * @return the number of days in this year 
	 */
	public static int numberOfDaysInYear(int year){
		assert(0 <= year): "Negative year are not aload";
		
		if(isLeapYear(year)) return 366;
		else return 365;
	}
	

	
	
	
	/* ############################################# */
	/* ############################################# */
	/* Invariant function */
	
	
	/**
	 * Check if the date is valid
	 * @param year The year (positive or null)
	 * @param month The month 
	 * @param day The day how as to correspond this the month and the year
	 * @return True if the date is valid, false otherwise. 
	 */
	public static Boolean checkDate(int year, Month month, int day){
		Boolean check = null;

		switch (month) {
		case JANUARY:
		case MARCH:
		case MAY:
		case JULY:
		case AUGUST:
		case OCTOBER:
		case DECEMBER:
			check = 0 < day && day < 32; 
			break;	
		case FEBRUARY:
			if(DateInterface.isLeapYear(year)){
				check = (0 < day && day < 30);
			}
			else{
				check = (0 < day && day < 29);
			}
			break;
		case APRIL:
		case JUNE:
		case SEPTEMBER:
		case NOVEMBER:
			check = (0 < day && day < 31);
			break;
		}

		assert(check != null);	
		return check && year >= 0;
	}
	/**
	 * Check if the date is valid
	 * @param year The year (positive or null)
	 * @param month The month (1 for January and 12 for December)
	 * @param day The day how as to correspond this the month and the year
	 * @return True if the date is valid, false otherwise. 
	 */
	public static Boolean checkDate(int year, int month, int day){
		Month month_ = Month.month(month);
		return checkDate(year, month_, day);
	}

 

 
	/**
	 * Clone other. No side effect
	 * @param other The DateInterface that you want to clone
	 * @return
	 */
	public static DateInterface clone(DateInterface other) {	
//		assert(invariant()) : "The invariante is not respected";
		OOSCDate clone = new OOSCDate(other.getYear(), other.getMonth(), other.getDay());
		
//		assert(invariant()) : "The invariante is not respected";
	    return clone;
	}
	
	 @Override
	 public String toString();
 
}