package date;

import java.util.Calendar;

public class OOSCDateTime extends OOSCDate implements DateTimeInterface{
	
	public static int SECONDS_IN_MINUTE = 60;
	public static int SECONDS_IN_HOUR = 60 * SECONDS_IN_MINUTE;
	public static int SECONDS_IN_DAY = 24 * SECONDS_IN_HOUR;
	
	public static int MINUTES_IN_HOURS = 60;
	
	public static int HOURS_IN_DAY = 24;
	
	
	/* ############################################# */
	/* ############################################# */
	/* Attributes */

	private Integer hours;
	private Integer minutes;
	private Integer seconds;
	
	
	/* ############################################# */
	/* ############################################# */
	/* Constructor */
	
    /**
     * Creates new object and assigns current system time to it.
     */
    public OOSCDateTime() {
    	
        Calendar systemCalendar = Calendar.getInstance();
        int systemYear = systemCalendar.get(Calendar.YEAR);
        int systemMonth = systemCalendar.get(Calendar.MONTH);
        int systemDay = systemCalendar.get(Calendar.DAY_OF_MONTH);
        int systemHours = systemCalendar.get(Calendar.HOUR_OF_DAY);
        int systemMinutes = systemCalendar.get(Calendar.MINUTE);
        int systemSeconds = systemCalendar.get(Calendar.SECOND);
        setDateTime(systemYear, systemMonth, systemDay, systemHours, systemMinutes, systemSeconds);
    }
    
    /**
     * Set hours, minutes and seconds.
     *
     * @param hours  hours to set, from 0 to 23
     * @param minutes minutes to set, from 0 to 59
     * @param seconds seconds to set, from 0 to 59
     */
	@Override
	public void setTime(int hours, int minutes, int seconds) {
		
        int year = getYear();
        int month = getMonth();
        int day = getDay();
        assertDateTime(year, month, day, hours, minutes, seconds);
        setHours(hours);
        setMinutes(minutes);
        setSeconds(seconds);
        assertEqualsDateTime(year, month, day, hours, minutes, seconds);
        
	}
	
	 /**
	  * Returns time value in String format
	  * @return time in String of format: "hh:mm:ss"
	  */
	@Override
	public String getTime() {
       return getHours() + ":" + getMinutes() + ":" + getSeconds();
	}
	
    /**
     * Set year, month, day, hours, minutes and seconds.
     *
     * @param hours   hours to set, from 0 to 23
     * @param minutes minutes to set, from 0 to 59
     * @param seconds seconds to set, from 0 to 59
     */
	@Override
	public void setDateTime(int year, int month, int day, int hours, int minutes, int seconds) {
		setDate(year, month, day);
		setTime(hours, minutes, seconds);
	}
	
    /**
     * Set hours.
     * @param hours   hours to set, from 0 to 23
     */
	@Override
	public void setHours(int hour) {
		assertHours(hour);
		this.hours = hour;
	}
	
    /**
     * Set minutes.
     * @param minutes   minutes to set, from 0 to 59
     */
	@Override
	public void setMinutes(int minutes) {
		assertMinutes(minutes);
		this.minutes = minutes;
	}
	
    /**
     * Set seconds.
     * @param seconds   seconds to set, from 0 to 59
     */
	@Override
	public void setSeconds(int seconds) {
		assertSeconds(seconds);
		this.seconds = seconds;
	}

	 /**
	  * Getter for Hours
	  * @return  hours from 0 to 23
	  */
	@Override
	public int getHours() {
		assertHours(this.hours);
		return hours;
	}

	 /**
	  * Getter for Minutes
	  * @return  Minutes from 0 to 59
	  */
	@Override
	public int getMinutes() {
		assertMinutes(this.minutes);
		return minutes;
	}

	 /**
	  * Getter for Seconds
	  * @return  seconds from 0 to 59
	  */
	@Override
	public int getSeconds() {
		assertSeconds(this.seconds);
		return seconds;
	}
	
    /**
     * Add hours, date/time will adjust accordingly
     *
     * @param hoursToAdd hours to add, should be >= 0
     */
	@Override
	public void addHours(int hoursToAdd) {
		assert(hoursToAdd >= 0);
		
		int inSeconds = (getHours() + hoursToAdd) * SECONDS_IN_HOUR;
		int daysToAdd = (getHours() + hoursToAdd) / HOURS_IN_DAY;
		int remainingHours = (inSeconds % SECONDS_IN_DAY ) / SECONDS_IN_HOUR ;
		addDays(daysToAdd);
		setHours(remainingHours);
		
	}
	
    /**
     * Add minutes, date/time will adjust accordingly
     *
     * @param minutesToAdd minutes to add, should be >= 0
     */
	@Override
	public void addMinutes(int minutesToAdd) {
		assert(minutesToAdd >= 0);
		
		int remainingMinutes = (getMinutes() + minutesToAdd) % MINUTES_IN_HOURS;
		int hoursToAdd = (getMinutes() + minutesToAdd) / MINUTES_IN_HOURS ;
		
		addHours(hoursToAdd);

		setMinutes(remainingMinutes);

	}
	
    /**
     * Add seconds, date/time will adjust accordingly
     *
     * @param addSeconds seconds to add, should be >= 0
     */
	@Override
	public void addSeconds(int secondsToAdd) {
		assert(secondsToAdd >= 0);

		int minutesToAdd = secondsToAdd / SECONDS_IN_MINUTE ;
		addMinutes(minutesToAdd);
		
		int remainingSeconds = ((secondsToAdd % SECONDS_IN_DAY ) % SECONDS_IN_HOUR ) % SECONDS_IN_MINUTE;
		int newSeconds = getSeconds() + remainingSeconds;
		setSeconds(newSeconds);
	}

    /**
     * Subtract hours, date/time will adjust accordingly
     *
     * @param hoursToRemove hours to subtract, should be > 0
     */
	@Override
	public void removeHours(int hoursToRemove) {
		assert(hoursToRemove > 0);
		
		int inSeconds = hoursToRemove * SECONDS_IN_HOUR;
		int daysToRemove = hoursToRemove / HOURS_IN_DAY;
		int remainingHours = (inSeconds % SECONDS_IN_DAY ) / SECONDS_IN_HOUR ;
		removeDays(daysToRemove);
		
		int newHours = getHours() - remainingHours;
		setHours(newHours);
	}

    /**
     * Subtract minutes, date/time will adjust accordingly
     *
     * @param minutesToRemove minutes to subtract, should be > 0
     */
	@Override
	public void removeMinutes(int minutesToRemove) {
		assert(minutesToRemove > 0);

		int inSeconds = minutesToRemove * SECONDS_IN_MINUTE;
		int hoursToRemove = minutesToRemove / SECONDS_IN_HOUR ;
		removeHours(hoursToRemove);
		
		int remainingMinutes = ((inSeconds % SECONDS_IN_DAY ) %  SECONDS_IN_HOUR) / SECONDS_IN_MINUTE;
		int newMinutes = getMinutes() - remainingMinutes;
		setMinutes(newMinutes);
	}

    /**
     * Subtract seconds, date/time will adjust accordingly
     *
     * @param secondsToRemove seconds to subtract, should be > 0
     */
	@Override
	public void removeSeconds(int secondsToRemove) {
		assert(secondsToRemove > 0);
		
		int minutesToRemove = secondsToRemove / SECONDS_IN_MINUTE ;
		removeMinutes(minutesToRemove);
		
		int remainingSeconds = ((secondsToRemove % SECONDS_IN_DAY ) % SECONDS_IN_HOUR ) % SECONDS_IN_MINUTE;
		int newSeconds = getSeconds() + remainingSeconds;
		setSeconds(newSeconds);
		
	}

    /**
     * Calculate difference between two times. The dates must be exactly same.
     *
     * @param type  type of the difference: seconds, minutes, hours.
     * @param time time to compare against.
     * @return either number of hours or minutes or seconds between times.
     */
	@Override
	public long timeBetween(int type, DateTimeInterface time) {
		
		if(type == DATETYPE_HOUR){
			assert (time.getHours() >= 0);
			assert (this.getHours() >=0);
			int hourBetween = time.getHours() - this.getHours();
			
			return (hourBetween < 0 ? -hourBetween : hourBetween);
		}
		else if(type == DATETYPE_MINUTE){
			
			assert (time.getMinutes() >= 0);
			assert (this.getMinutes() >=0);
			int minBetween = time.getMinutes() - this.getMinutes();
			
			return ( minBetween < 0 ? - minBetween :  minBetween);
		}
		else if(type == DATETYPE_SECOND){
			
			assert (time.getSeconds() >= 0);
			assert (this.getSeconds() >=0);
			int secBetween = time.getSeconds() - this.getSeconds();
			
			return (secBetween < 0 ? -secBetween : secBetween);
		}
		else{
			// TODO: throw error
			return 0;
		}
	}

    /**
     * Check that the date and time are in a consistent state.
     */
    protected static void assertDateTime(int year, int month, int day, int hours, int minutes, int seconds) {
    	
    	assert(checkDate(year, month, day));
        assertTime(hours, minutes, seconds);
    }

    /**
     * Check that the time is in a consistent state.
     * @param hours hours to check
     * @param minutes minutes to check
     * @param seconds seconds to check
     */
    protected static void assertTime(int hours, int minutes, int seconds) {
        assertHours(hours);
        assertMinutes(minutes);
        assertSeconds(seconds);
    }

    /**
     * Check that hours are in a consistent state.
     * @param hours hours to check
     */
    protected static void assertHours(int hours){
        assert isValidHours(hours): "Wrong value of Hours" + hours;
        return;
    }

    /**
     * Check that minutes are in a consistent state.
     * @param minutes minutes to check
     */
    protected static void assertMinutes(int minutes) {
        assert isValidMinutes(minutes);
    }

    /**
     * Check that seconds are in a consistent state.
     * @param seconds seconds to check
     */
    protected static void assertSeconds(int seconds) {
        assert isValidSeconds(seconds);
    }

    /**
     * Check that hours is valid
     * @return true if hours are within boundaries, false otherwise
     */
    protected static boolean isValidHours(int hours) {
        return 0 <= hours && hours < 24;
    }

    /**
     * Check that minutes is valid
     * @return true if minutes are within boundaries, false otherwise
     */
    protected static boolean isValidMinutes(int minutes) {
        return 0 <= minutes && minutes < 60;
    }

    /**
     * Check that seconds is valid
     * @return true if seconds are within boundaries, false otherwise
     */
    protected static boolean isValidSeconds(int seconds) {
        return 0 <= seconds && seconds < 60;
    }

    /**
     * Assert that the given date and time corresponds to the current object's state.
     */
    protected void assertEqualsDateTime(int year, int month, int day, int hours, int minutes, int seconds) {
        assertEqualsDate(year, month, day);
        assertEqualsTime(hours, minutes, seconds);
    }

    private void assertEqualsTime(int hours, int minutes, int seconds) {
        assert isEqualsTime(hours, minutes, seconds);
    }

    private boolean isEqualsTime(int hours, int minutes, int seconds) {
        return getHours() == hours && getMinutes() == minutes && getSeconds() == seconds;
    }
	

}
