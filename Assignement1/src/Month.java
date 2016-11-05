import java.util.HashMap;
import java.util.Map;



	public enum Month {
	    JANUARY(1, 31),FEBRUARY(2, 28),MARCH(3, 31),APRIL(4, 30),MAY(5, 31),JUNE(6, 30),
	    JULY(7, 31),AUGUST(8, 31),SEPTEMBER(9, 30),OCTOBER(10, 31),NOVEMBER(11, 30),DECEMBER(12, 31);
	    
	    private final int value;
	    private final int dayNumber;
	    private static Map<Integer, Month> map = new HashMap<Integer, Month>();
	    private Month(int value, int dayNumber){ // TODO: rebust 
	    	assert(0 < value && value < 13): "value has to be between 0 and 13";
	        assert(0 < dayNumber && dayNumber < 32); // TODO check?????
	    	
	    	this.value = value;	  
	    	this.dayNumber = dayNumber;
	    	
	        assert(this.value == value);
	        assert(this.dayNumber == dayNumber);
	    }
	    static {
	        for (Month month : Month.values()) {
	            map.put(month.value, month);

	        }
	    }
	    public int getValue() {
	        return value;
	    }
	    public int getNumberOfDays(int year){
	    	if(this == FEBRUARY && OOSCDate.isLeapYear(year)){
	    		return 29;
	    	}
	    	else{
	    		return dayNumber;
	    	}
	    }
	    public static Month month(int month){
	    	assert(0 < month && month < 13): "Invalide month value";
	    	return map.get(month);
	    }
	    public Month nextMonth(){
	    	int newValue = (value + 1) % 12;
	    	return Month.month(newValue);
	    }
	}