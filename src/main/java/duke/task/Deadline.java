package duke.task;

import duke.ErrorHandling.ErrorStaticString;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represent a DeadLine Task
 */
public class Deadline extends Task{

    private static final String TASK_SYMBOL = "[D]";
    private final String deadlineDateTime;

    /**
     * Constructor for DeadLine Task
     * Set description of DeadLine
     * Set Date and Time of DeadLine
     */
    public Deadline(String description, String deadlineDateTime){
        super(description);
        this.deadlineDateTime = deadlineDateTime;
    }

    /**
     * Find first instance of a date by finding a string in a pattern:4int-2int-2int
     *
     * @param stringToSearch String that contain date
     * @return Null if no date is found or return a matcher object that found date
     */
    private Matcher findDate(String stringToSearch){
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(stringToSearch);
        if(matcher.find()){
            return matcher;
        }
        return null;
    }

    /**
     * Find first instance of time by finding a fixed pattern:2int:2int
     *
     * @param stringToSearch String that contain time
     * @return null if no time found or return matcher object that found time
     */
    private Matcher findTime(String stringToSearch){
        Pattern pattern = Pattern.compile(TIME_PATTERN);
        Matcher matcher = pattern.matcher(stringToSearch);
        if(matcher.find()){
            return matcher;
        }
        return null;
    }

    /**
     * Extract string containing date
     *
     * @return string containing date or Empty String if no date found
     */
    @Override
    public String convertStringToDate(){
        Matcher dateMatcher = findDate(deadlineDateTime);
        if(dateMatcher != null){
            int startIndexOfDate = dateMatcher.start();
            int endIndexOfDate = dateMatcher.end();
            return deadlineDateTime.substring(startIndexOfDate,endIndexOfDate);
        }
        return EMPTY_STRING;
    }

    /**
     * Extract string containing Time
     *
     * @return string containing time or Empty String if no time found
     */
    private String convertStringToTime(){
        Matcher timeMatcher = findTime(deadlineDateTime);
        if(timeMatcher != null){
            int startIndexOfTime = timeMatcher.start();
            int endIndexOfTime = timeMatcher.end();
            return deadlineDateTime.substring(startIndexOfTime,endIndexOfTime);
        }
        return EMPTY_STRING;
    }

    /**
     * Process date in string form and reformat it
     *
     * @param date String containing date of task
     * @param isForFile Boolean variable to decide format of date return
     * @return String of reformatted date depending on whether it being printed or save into text file
     */
    @Override
    public String getDate(String date, boolean isForFile) {
        if(!date.isEmpty()) {
            try {
                LocalDate parseDate = LocalDate.parse(date);
                if (isForFile){
                    return parseDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                }
                return parseDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            }catch (DateTimeParseException e){
                System.out.println(ErrorStaticString.ERROR_DATE_TIME_PARSE);
            }
        }
        return EMPTY_STRING;
    }

    /**
     * Process Time in string form and reformat it
     *
     * @param time String containing time of task
     * @param isForFile Boolean variable to decide format of date return
     * @return String of reformatted  time depending on whether it being printed or save into text file
     */
    private String getTime(String time, boolean isForFile) {
        if(!time.isEmpty()) {
            try {
                LocalTime parseTime = LocalTime.parse(time);
                if(isForFile){
                    return parseTime.format(DateTimeFormatter.ofPattern("hh:mm"));
                }
                return parseTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
            }catch (DateTimeParseException e){
                System.out.println(ErrorStaticString.ERROR_DATE_TIME_PARSE);
            }
        }
        if(getDate(convertStringToDate(),false).equals(EMPTY_STRING)) {
            return deadlineDateTime;
        }
        return EMPTY_STRING;
    }

    /**
     * Format string to print to user
     *
     * @return Formatted String containing date and time if present
     */
    @Override
    public String toString(){
    return TASK_SYMBOL + super.toString() + "(by: " + getTime(convertStringToTime(),false) + " " + getDate(convertStringToDate(),false) + ")";
    }

    /**
     * Format string to write to text file
     *
     * @return Formatted String containing date and time if present
     */
    @Override
    public String toFile(){
        return TASK_SYMBOL + SEPARATOR + super.toFile() + SEPARATOR + getTime(convertStringToTime(),true) + " " + getDate(convertStringToDate(),true);
    }
}
