public interface DateInterface {
 public static final int DATETYPE_YEAR = 0;
 public static final int DATETYPE_DAY = 2;
 public static final int DATETYPE_MONTH = 1;
 
 public void setDate(int year, int month, int day);
 public void setYear(int year);
 public void setMonth(int month);
 public void setDay(int day);
 
 public int getYear();
 public int getMonth();
 public int getDay();
 
 public void addDays(int daysToAdd);
 public void addMonths(int monthsToAdd);
 public void addYears(int yearsToAdd);
 
 public void removeDays(int daysToRemove);
 public void removeMonths(int monthsToRemove);
 public void removeYears(int yearsToRemove);
 
 public int daysBetween(DateInterface date1, DateInterface date2);
 public int timeBetween(int type, DateInterface date1);
 public void synchWithUTCTimeserver();
 
 
 // added functions
 public int toNumberOfDays();
 public int toNumberOfMonths();
 public DateInterface toDateInterface(int numberOfDays);
 public int monthBetween(DateInterface date1, DateInterface date2);
 
 
 
 @Override
 public String toString();
}