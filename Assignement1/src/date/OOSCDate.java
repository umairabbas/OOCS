package date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class OOSCDate implements DateInterface, Cloneable{
	
	/* ############################################# */
	/* ############################################# */
	/* Attributes */

	private Integer day;
	private Month month;
	private Integer year;
	
	
	/* ############################################# */
	/* ############################################# */
	/* Constructor */
	
	public OOSCDate(int year, Month month, int day) throws AssertionError{
		assert(checkDate(year, month, day)): "Date not valide";
		
		this.year = year;
		this.month = month;
		this.day = day;
		
		assert(this.year == year): "Someting went wrong is the year's instantation";
		assert(this.month == month): "Someting went wrong is the month's instantation";
		assert(this.day == day): "Someting went wrong is the day's instantation";
		assert(invariant()) : "Date not valide";
	}
	public OOSCDate(int year, int month, int day){
		assert(checkDate(year, month, day)): "Date not valide";
		
		Month month_ = Month.month(month);
		this.year = year;
		this.month = month_;
		this.day = day;
		
		assert(this.year == year): "Someting went wrong is the year's instantation";
		assert(this.month.getValue() == month): "Someting went wrong is the month's instantation";
		assert(this.day == day): "Someting went wrong is the day's instantation";
		assert(invariant()) : "Date not valide";
	}
	public OOSCDate() {
		this.year = 0;
		this.month = Month.JANUARY;
		this.day = 1;
		
		assert(this.year == 0): "Someting went wrong is the year's instantation";
		assert(this.month == Month.JANUARY): "Someting went wrong is the month's instantation";
		assert(this.day == 1): "Someting went wrong is the day's instantation";
		assert(invariant()) : "Date not valide";
	}
	
	/* ############################################# */
	/* ############################################# */
	/* Set functions */
	
	@Override
	public void setDate(int year, int month, int day) {
		assert(invariant()) : "The invariante is not respected";
		assert(checkDate(year, month, day)): "Date not valide";
		
		Month month_ = Month.month(month);
		setDate(year, month_, day);

		assert(this.year == year): "Someting went wrong is the year's assigment";
		assert(this.month.getValue() == month): "Sometin went wrong is the month's assigment";
		assert(this.day == day): "Someting went wrong is the day's assigment";
		assert(invariant()) : "The invariante is not respected";
	}

	public void setDate(int year, Month month, int day){
		assert(invariant()) : "The invariante is not respected";
		assert(checkDate(year, month, day)): "Date not valide";
		
		setYear(year);
		setMonth(month);
		setDay(day);
		
		assert(this.year == year): "Someting went wrong is the year's assigment";
		assert(this.month == month): "Someting went wrong is the month's assigment";
		assert(this.day == day): "Someting went wrong is the day's assigment";
		assert(invariant()) : "The invariante is not respected";
	}
	
	@Override
	public void setYear(int year) {
		assert(invariant()) : "The invariante is not respected";
		assert(0 <= year) : "The year has to be greater or equal to 0";
		
		if(this.month == Month.FEBRUARY && this.day == 29 && !isLeapYear(year)){
			this.day = 28;
		}
		this.year =  year;
		
		assert(this.year ==  year) : "Someting went wrong is the year's assigment";
		assert(invariant()) : "The invariante is not respected";
	}
	

	@Override
	public void setMonth(int month) {
		assert(invariant()) : "The invariante is not respected";
		Month month_ = Month.month(month);

		setMonth(month_);

		assert(this.month.getValue() == month) : "Someting went wrong is the month's assigment";
		assert(invariant()) : "The invariante is not respected";		
	}

	public void setMonth(Month month){
		assert(invariant()) : "The invariante is not respected";
		
		if(this.day > month.getNumberOfDays(year)){
			this.day = month.getNumberOfDays(year);
		}
		this.month = month;

		assert(this.month == month) : "Someting went wrong is the month's assigment";
		assert(invariant()) : "The invariante is not respected";		
	}
	
	@Override
	public void setDay(int day) {
		assert(invariant()) : "The invariante is not respected";
		assert(checkDate(year, month, day)) : "The day is not valide for this month and year";
		
		this.day = day;
		
		assert(this.day == day): "Someting went wrong is the day assigment";
		assert(invariant()) : "The invariante is not respected";
	}

	
	/* ############################################# */
	/* Get functions */
	
	@Override
	public int getYear() {
		assert(invariant()) : "The invariante is not respected";
		
		int y = this.year;
		
		assert(0 <= y) : "The returned year is not correct";
		assert(invariant()) : "The invariante is not respected";
		
		return y;
	}

	@Override
	public int getMonth() {
		assert(invariant()) : "The invariante is not respected";
		
		int m = this.month.getValue();
		
		assert(0 < m && m < 13) : "The returned month is not correct";
		assert(invariant()) : "The invariante is not respected";
		
		return m;
	}

	@Override
	public int getDay() {
		assert(invariant()) : "The invariante is not respected";
		
		int d = this.day;
		
		assert(checkDate(year, month, d)) : "The returned day is not correct";
		assert(invariant()) : "The invariante is not respected";
		
		return d;
	}
	
	/* ############################################# */
	/* Add functions */
	

	@Override
	public void addDays(int daysToAdd) {
		assert(invariant()) : "The invariante is not respected";
		assert(0 <= daysToAdd): "You can not add negative days";
		
		DateInterface newDate = toDateInterface(toNumberOfDays() + daysToAdd);
		setDate(newDate.getYear(), newDate.getMonth(), newDate.getDay());
		
		DateInterface test = (DateInterface) this.clone(); //TODO: Prevent for parsing errors
		test.removeDays(daysToAdd);
		test.addDays(daysToAdd);
		assert(this.equals(test)) : "Adding days and remove them sould be inverse function";
		assert(invariant()) : "The invariante is not respected";
	}
	
	@Override
	public void addMonths(int monthsToAdd) {
		assert(invariant()) : "The invariante is not respected";
		assert(0 <= monthsToAdd): "You can not add negative month";
		
		int yearToAdd = (this.month.getValue() + monthsToAdd) / 12;
		int newMonth = (this.month.getValue() + monthsToAdd) % 12;
			
		addYears(yearToAdd);
		setMonth(newMonth);
		
		DateInterface test = (DateInterface) this.clone(); //TODO: Prevent for parsing errors
		test.removeMonths(monthsToAdd);
		test.addMonths(monthsToAdd);
		assert(this.equals(test)) : "Adding months and remove them sould be inverse function";
		assert(invariant()) : "The invariante is not respected";
	}

	@Override
	public void addYears(int yearsToAdd) {
		assert(invariant()) : "The invariante is not respected";
		assert(0 <= yearsToAdd): "You can not add negative year";
		
		setYear(year + yearsToAdd);
		
		DateInterface test = (DateInterface) this.clone(); //TODO: Prevent for parsing errors
		test.removeYears(yearsToAdd);;
		test.addYears(yearsToAdd);;
		assert(this.equals(test)) : "Adding years and remove them sould be inverse function";
		assert(invariant()) : "The invariante is not respected";
	}
	
	
	/* ############################################# */
	/* remove functions */

	@Override
	public void removeDays(int daysToRemove) {
		assert(invariant()) : "The invariante is not respected";
		assert(0 <= daysToRemove) : "You can not remove negative amount of days";
		assert(0 <= toNumberOfDays() - daysToRemove) : "You can not remove more days that there is till J.C";
		
		DateInterface newDate = toDateInterface(toNumberOfDays() - daysToRemove);
		setDate(newDate.getYear(), newDate.getMonth(), newDate.getDay());
		
		DateInterface test = (DateInterface) this.clone(); //TODO: Prevent for parsing errors
		test.addDays(daysToRemove);
		test.removeDays(daysToRemove);
		assert(this.equals(test)) : "Adding days and remove them sould be inverse function";
		assert(invariant()) : "The invariante is not respected";
	}
	
	@Override
	public void removeMonths(int monthsToRemove) {
		assert(invariant()) : "The invariante is not respected";
		assert(0 <= monthsToRemove) : "You can not remove negative amount of days";
		assert(0 <= toNumberOfMonths() - monthsToRemove) : "You can not remove more month that there is till J.C";

		int nbMonth = toNumberOfMonths() - monthsToRemove;
		removeYears(nbMonth / 12);
		int newValueMonth = nbMonth % 12;
		setMonth(newValueMonth);
		
		DateInterface test = (DateInterface) this.clone(); //TODO: Prevent for parsing errors
		test.addMonths(monthsToRemove);
		test.removeMonths(monthsToRemove);
		assert(this.equals(test)) : "Adding months and remove them sould be inverse function";
		assert(invariant()) : "The invariante is not respected";
	}
	
	@Override
	public void removeYears(int yearsToRemove) {
		assert(invariant()) : "The invariante is not respected";
		assert(0 < yearsToRemove): "The yearsToAdd as to be greater than 0";
		assert(0 <= year - yearsToRemove);
		
		setYear(year - yearsToRemove);
		
		DateInterface test = (DateInterface) this.clone(); //TODO: Prevent for parsing errors
		test.addYears(yearsToRemove);
		test.removeYears(yearsToRemove);
		assert(this.equals(test)) : "Adding year and remove them sould be inverse function";
		assert(invariant()) : "The invariante is not respected";
	}

	
	/* ############################################# */
	/* Certain-time-between functions */
	
	@Override
	public int daysBetween(DateInterface date1, DateInterface date2) {
		assert(invariant()) : "The invariante is not respected";
		
		int daysBt = date1.toNumberOfDays() - date2.toNumberOfDays();
		
		DateInterface test = clone(date1);
		test.addDays(daysBt);
		assert(test.equals(date2)) : "Addind the days between the 2 dates at date1, should be equal to data2";
		assert(invariant()) : "The invariante is not respected";
		
		return daysBt;	
	}
	
	@Override
	public int monthBetween(DateInterface date1, DateInterface date2){
		assert(invariant()) : "The invariante is not respected";
		
		int monthBt = date1.toNumberOfMonths() - date2.toNumberOfMonths();
		
		DateInterface test = clone(date1);
		test.addMonths(monthBt);
		assert(test.equals(date2)) : "Addind the month between the 2 dates at date1, should be equal to data2";
		assert(invariant()) : "The invariante is not respected";
		
		return monthBt;	
	}


	@Override
	public int timeBetween(int type, DateInterface date1) {
		assert(invariant()) : "The invariante is not respected";
		assert(type == DATETYPE_DAY || type == DATETYPE_MONTH || type == DATETYPE_YEAR) : "type is wrong";
		
		if(type == DATETYPE_DAY){
			int dayBetween = daysBetween(this, date1);
			
			DateInterface test = (DateInterface) this.clone(); //TODO: Error parsing
			test.addDays(dayBetween);
			assert(test.equals(date1)) : "Addind the days between the 2 dates at this, should be equal to data1";
			assert(invariant()) : "The invariante is not respected";
			
			return dayBetween;
		}
		else if(type == DATETYPE_MONTH){
			int monthBetween =  monthBetween(this, date1);
			
			DateInterface test = (DateInterface) this.clone(); //TODO: Error parsing
			test.addMonths(monthBetween);
			assert(test.equals(date1)) : "Addind the month between the 2 dates at this, should be equal to data1";
			assert(invariant()) : "The invariante is not respected";
			
			return monthBetween;
		}
		else if(type == DATETYPE_YEAR){
			int yearBetween =  this.getYear() - date1.getYear();
			
			DateInterface test = (DateInterface) this.clone(); //TODO: Error parsing
			test.addYears(yearBetween);
			assert(test.equals(date1)) : "Addind the year between the 2 dates at this, should be equal to data1";
			assert(invariant()) : "The invariante is not respected";
			
			return yearBetween;
		}
		else{
			// TODO: throw error
			return 0;
		}
	}
	
	/* ############################################# */
	/* Other function */
	

	@Override
	public void synchWithUTCTimeserver() {
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
					// TODO: Throw errors
				}
				else{
//					String[] oosc = format[0].split("T");
					// TODO: Deal with erros
				}
				
			}
			
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/* ############################################# */
	/* Convert into an other representation */
	
	@Override
	public int toNumberOfDays(){
		assert(invariant()) : "The invariante is not respected";
		int nbDays = 0;
		
		// add number of days from year 0 till this year
		for(int y=0; y < year; ++y){ 
			nbDays += numberOfDaysInYear(year);
		}
		// add number of days from January 0 till this month
		for(int m=1; m <= month.getValue(); ++m){
			nbDays += Month.month(m).getNumberOfDays(year);
		}
		// add number of days in this month
		nbDays += day;
		
		DateInterface test  = toDateInterface(nbDays);
		assert(this.equals(test)) : "toNumberOfDays() and toDateInterface(...) sould be inverse function";
		assert(invariant()) : "The invariante is not respected";
		
		return nbDays;
	}
	
	@Override
	public int toNumberOfMonths(){
		assert(invariant()) : "The invariante is not respected";
		return year * 12 + year;
	}
	
	
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
	/* Static functions */
	
	
	public static Boolean isLeapYear(int year){ 
		assert(0 <= year): "Negative year are not aload";
		Boolean isLeap = ((year % 4 == 0) && (year % 100 == 0) && (year % 400 == 0));
		
		assert(isLeap == ((year % 4 == 0) && (year % 100 == 0) && (year % 400 == 0)));
		assert(0 <= year): "Negative year are not aload";
		return isLeap;
	}
	
	public static int numberOfDaysInYear(int year){
		assert(0 <= year): "Negative year are not aload";
		
		if(isLeapYear(year)) return 366;
		else return 365;
	}
	
	/* ############################################# */
	/* ############################################# */
	/* Invariant function */
	
	private Boolean checkDate(int year, Month month, int day){
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
			if(isLeapYear(year)){
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
	private Boolean checkDate(int year, int month, int day){
		Month month_ = Month.month(month);
		return checkDate(year, month_, day);
	}
	
	private Boolean invariant(){
		return checkDate(this.year, this.month, this.day);
	}

	
	
	
	/* ############################################# */
	/* ############################################# */
	/* Common object function */
	
	
	@Override
	public Object clone() {
		assert(invariant()) : "The invariante is not respected";
		OOSCDate clone = new OOSCDate(year, month, day);
		
		assert(getYear() == clone.getYear()) : "Problem when cloning the year";
		assert(getMonth() == clone.getMonth()) : "Problem when cloning the month";
		assert(getDay() == clone.getDay()) : "Problem when cloning the day";
		assert(invariant()) : "The invariante is not respected";
		
	    return clone;
	}
	public DateInterface clone(DateInterface other) {	
		assert(invariant()) : "The invariante is not respected";
		OOSCDate clone = new OOSCDate(other.getYear(), other.getMonth(), other.getDay());
		
		assert(getYear() == clone.getYear()) : "Problem when cloning the year";
		assert(getMonth() == clone.getMonth()) : "Problem when cloning the month";
		assert(getDay() == clone.getDay()) : "Problem when cloning the day";
		assert(invariant()) : "The invariante is not respected";
	    return clone;
	}
	
	@Override
	public boolean equals(Object other){
		assert(invariant()) : "The invariante is not respected";
		
		if (other == null) return false;
		else if (other == this) return true;
		else if (!(other instanceof OOSCDate)) return false;
		else{
			OOSCDate oos = (OOSCDate) other;
			Boolean isEqual = true; 
			isEqual = isEqual && this.year == oos.year;
			isEqual = isEqual && this.month == oos.month;
			isEqual = isEqual && this.day == oos.day;
			
			
			assert(!isEqual || (getYear() == oos.getYear())) : "The 2 years has to be equal";
			assert(!isEqual || (getMonth() == oos.getMonth())) : "The 2 months has to be equal";
			assert(!isEqual || (getDay() == oos.getDay())) : "The 2 days has to be equal";
			assert(invariant()) : "The invariante is not respected";
			return isEqual;
		}
	}
	
	@Override
	public String toString(){
		assert(invariant()) : "The invariante is not respected";
		return "Year: " + year + ",Month: "  + month.toString() + ",Day: "  + day;
	}
	
	
	
	
	
	
}
