package date;
import java.util.HashMap;
import java.util.Map;



	public enum Month {
	    JANUARY(1, 31, "January"),FEBRUARY(2, 28, "February"),MARCH(3, 31, "March"),
	    APRIL(4, 30, "April"),MAY(5, 31, "May"),JUNE(6, 30, "June"),
	    JULY(7, 31, "July"),AUGUST(8, 31, "August"),SEPTEMBER(9, 30, "September"),
	    OCTOBER(10, 31, "October"),NOVEMBER(11, 30, "November"),DECEMBER(12, 31, "December");
	    
	    private final int value;
	    private final int dayNumber;
	    private final String english;
	    
	    private static Map<Integer, Month> map = new HashMap<Integer, Month>();
	    static {
	        for (Month month : Month.values()) {
	            map.put(month.value, month);
	        }
	    }
	    
	    private Month(int value, int dayNumber, String english){ // TODO: rebust 
	    	assert(0 < value && value < 13): "value has to be between 0 and 13";
	        assert(0 < dayNumber && dayNumber < 32); // TODO check?????
	        assert(english.length() > 2) : "The english name can not be less than 3 characters";
	    	
	    	this.value = value;	  
	    	this.dayNumber = dayNumber;
	    	this.english = english; 
	    	
	        assert(this.value == value);
	        assert(this.dayNumber == dayNumber);
	    }
	    /**
	     * Return the number corresponding to the month
	     * @return The number corresponding to the month. (1 for January and 12 for December)
	     */
	    public int getValue() {
	        return value;
	    }
	    /**
	     * Return the number of days corresponding to the month at this year
	     * @param year The year in witch the month is
	     * @return the number of days corresponding to the month at this year
	     */
	    public int getNumberOfDays(int year){
	    	if(this == FEBRUARY && DateInterface.isLeapYear(year)){
	    		return 29;
	    	}
	    	else{
	    		return dayNumber;
	    	}
	    }
	    /**
	     * Create the Month corresponding to the value.
	     * @param month The value of the month (1 for January and 12 for December)
	     * @return The corresponding Month
	     */
	    public static Month month(int month){
	    	assert(0 < month && month < 13): "Invalide month value";
	    	return map.get(month);
	    }
	    /**
	     * Return the following month. Example: 
	     * January -> February and December -> January
	     * @return the following month
	     */
	    public Month nextMonth(){
	    	int newValue = (value + 1) % 12;
	    	if(newValue == 0) newValue = 12;
	    	return Month.month(newValue);
	    }
	    @Override
	    /**
	     * To the English name
	     */
	    public String toString(){
	    	return english;
	    }
	}