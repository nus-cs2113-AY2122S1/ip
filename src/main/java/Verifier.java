import java.util.Arrays;
import java.util.List;

public class Verifier {

    public static final List<String> ONE_PART_COMMAND = Arrays.asList("list");
    public static final List<String> TWO_PART_COMMAND = Arrays.asList("todo", "done", "deadline",
            "event", "delete", "find");
    
    
    public static boolean isDelete(String input) {
        return input.equals("delete");
    }

    public static boolean isFind(String input) {
        return input.equals("find");
    }


    public static boolean isInvalidOnePartCmd(String[] inputs, int commandLength) {
        return commandLength > 1 && isOnePartCmd(inputs[0]);
    }

    public static boolean isNotBye(String command) {
        return !command.equals("bye");
    }

    public static boolean isInvalidTwoPartCmd(String[] inputs, int commandLength) {
        return commandLength == 1 && isTwoPartCmd(inputs[0]);
    }
    
    public static boolean isOnePartCmd(String s) {
        return ONE_PART_COMMAND.contains(s);
    }

    public static boolean isTwoPartCmd(String s) {
        return TWO_PART_COMMAND.contains(s);
    }


    public static boolean isInvalidTaskCount(int taskCount, int taskNumber) {
        return taskNumber <= 0 || taskNumber > taskCount;
    }
    
    public static boolean isDone(String s) {
        return s.equals("done");
    }

    public static boolean isEvent(String s) {
        return s.equals("event");
    }

    public static boolean isDeadline(String s) {
        return s.equals("deadline");
    }

    public static boolean isTodo(String s) {
        return s.equals("todo");
    }

    public static boolean isList(String s) {
        return s.equals("list");
    }
    
}
