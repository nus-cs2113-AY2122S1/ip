package unker.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.regex.Matcher;
import unker.util.StringUtil;

public class TaskFactory {
    
    public static ToDo createToDoTask(String data) {
        return new ToDo(data);
    }
    
    public static Deadline createDeadlineTask(String data) {
        Matcher deadlineMatcher = StringUtil.parseUserInput(Deadline.DEADLINE_DATA_PATTERN, data);
        if (deadlineMatcher == null) {
            return null;
        }
        LocalDate date = LocalDate.parse(deadlineMatcher.group(2));
        LocalTime time = LocalTime.parse(deadlineMatcher.group(3));
        return new Deadline(deadlineMatcher.group(1), LocalDateTime.of(date, time));
    }
    
    public static Event createEventTask(String data) {
        Matcher eventMatcher = StringUtil.parseUserInput(Event.EVENT_DATA_PATTERN, data);
        if (eventMatcher != null) {
            return new Event(eventMatcher.group(1), eventMatcher.group(2));
        } else {
            return null;
        }
    }
    
}
