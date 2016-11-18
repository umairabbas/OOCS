package date;

public interface DateTimeInterface extends DateInterface{

    int DATETYPE_HOUR = 0;
    int DATETYPE_MINUTE = 2;
    int DATETYPE_SECOND = 1;

    void setTime(int hours, int minutes, int seconds);
    
    String getTime();

    void setDateTime(int year, int month, int day, int hours, int minutes, int seconds);

    void setHours(int hour);

    void setMinutes(int minutes);

    void setSeconds(int seconds);

    int getHours();

    int getMinutes();

    int getSeconds();

    void addHours(int hoursToAdd);

    void addMinutes(int minutesToAdd);

    void addSeconds(int secondsToAdd);

    void removeHours(int hoursToRemove);

    void removeMinutes(int minutesToRemove);

    void removeSeconds(int secondsToRemove);

    long timeBetween(int type, DateTimeInterface date1);

}
