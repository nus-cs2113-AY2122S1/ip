package unker.task;

import java.util.regex.Matcher;
import unker.util.StringUtil;

public class TaskFactory {
    
    public static ToDo createToDoTask(String data) {
        return new ToDo(data);
    }
    
    public static Deadline createDeadlineTask(String data) {
        Matcher deadlineMatcher = StringUtil.parseUserInput(Deadline.DEADLINE_DATA_PATTERN, data);
        if (deadlineMatcher != null) {
            return new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2));
        } else {
            return null;
        }
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
