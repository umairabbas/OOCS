package date;

public class OOSCDateTime extends OOSCDate implements DateTimeInterface{

	
    /**
     * Number of seconds in a minute. Should be 60. But it might change in case the Earth starts to spin slower or faster.
     */
    public static int SECONDS_IN_MINUTE = 60;

    /**
     * Number of seconds in an hour. It might change in case the Earth starts to spin slower or faster.
     */
    public static int SECONDS_IN_HOUR = 60 * SECONDS_IN_MINUTE;

    /**
     * Creates new object and assigns current system time to it.
     */
    public OOSCDateTime() {
        //timestampSeconds = 0;
//        final Calendar systemCalendar = Calendar.getInstance();
//        final int systemYear = systemCalendar.get(Calendar.YEAR);
//        final int systemMonth = systemCalendar.get(Calendar.MONTH);
//        final int systemDay = systemCalendar.get(Calendar.DAY_OF_MONTH);
//        final int systemHours = systemCalendar.get(Calendar.HOUR_OF_DAY);
//        final int systemMinutes = systemCalendar.get(Calendar.MINUTE);
//        final int systemSeconds = systemCalendar.get(Calendar.SECOND);
   //     setDateTime(systemYear, systemMonth, systemDay, systemHours, systemMinutes, systemSeconds);
    }
    
    /**
     * Set hours, minutes and seconds.
     *
     * @param hours   hours to set, from 0 to 23
     * @param minutes minutes to set, from 0 to 59
     * @param seconds seconds to set, from 0 to 59
     */
	@Override
	public void setTime(int hours, int minutes, int seconds) {
		// TODO Auto-generated method stub
        final int year = getYear();
        final int month = getMonth();
        final int day = getDay();
        assertDateTime(year, month, day, hours, minutes, seconds);
        // save time
        // assert again!
	}

	@Override
	public void setDateTime(int year, int month, int day, int hours, int minutes, int seconds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHours(int hour) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMinutes(int minutes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSeconds(int seconds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getHours() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinutes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSeconds() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addHours(int hoursToAdd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMinutes(int minutesToAdd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSeconds(int secondsToAdd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeHours(int hoursToRemove) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMinutes(int minutesToRemove) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeSeconds(int secondsToRemove) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long timeBetween(int type, DateTimeInterface date1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getTotalSeconds() {
		// TODO Auto-generated method stub
		return 0;
	}
	
    /**
     * Check that the date and time are in a consistent state.
     */
    protected static void assertDateTime(int year, int month, int day, int hours, int minutes, int seconds) {
    	
    	//OMG WHY in the world?
    	assert(DateInterface.checkDate(year, month, day));
    	
        assertTime(hours, minutes, seconds);
    }

    /**
     * Check that the time is in a consistent state.
     */
    protected static void assertTime(int hours, int minutes, int seconds) {
//        assertHours(hours);
//        assertMinutes(minutes);
//        assertSeconds(seconds);
    }
	

}
