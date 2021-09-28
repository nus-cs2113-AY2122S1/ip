package duke.task;

import duke.ErrorHandling.ErrorStaticString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task{

    private static final String TASK_SYMBOL = "[D]";
    private final String deadlineDateTime;

    public Deadline(String description, String deadlineDateTime){
        super(description);
        this.deadlineDateTime = deadlineDateTime;
    }

    private Matcher findDate(String stringToSearch){
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(stringToSearch);
        if(matcher.find()){
            return matcher;
        }
        return null;
    }

    private Matcher findTime(String stringToSearch){
        Pattern pattern = Pattern.compile(TIME_PATTERN);
        Matcher matcher = pattern.matcher(stringToSearch);
        if(matcher.find()){
            return matcher;
        }
        return null;
    }

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

    private String convertStringToTime(){
        Matcher timeMatcher = findTime(deadlineDateTime);
        if(timeMatcher != null){
            int startIndexOfTime = timeMatcher.start();
            int endIndexOfTime = timeMatcher.end();
            return deadlineDateTime.substring(startIndexOfTime,endIndexOfTime);
        }
        return EMPTY_STRING;
    }

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

    @Override
    public String toString(){
    return TASK_SYMBOL + super.toString() + "(by: " + getTime(convertStringToTime(),false) + " " + getDate(convertStringToDate(),false) + ")";
    }

    @Override
    public String toFile(){
        return TASK_SYMBOL + SEPARATOR + super.toFile() + SEPARATOR + getTime(convertStringToTime(),true) + " " + getDate(convertStringToDate(),true);
    }
}
