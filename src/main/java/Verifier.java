import java.util.Arrays;
import java.util.List;

public class Verifier {

    public static final List<String> ONE_PART_COMMAND = Arrays.asList("list");
    public static final List<String> TWO_PART_COMMAND = Arrays.asList("todo", "done", "deadline", "event", "delete");
    
    
    public boolean isDelete(int commandLength, String input) {
        return commandLength == 2 && input.equals("delete");
    }


    public boolean isInvalidOnePartCmd(String[] inputs, int commandLength) {
        return commandLength > 1 && isOnePartCmd(inputs[0]);
    }

    public boolean isNotBye(String command) {
        return !command.equals("bye");
    }

    public boolean isInvalidTwoPartCmd(String[] inputs, int commandLength) {
        return commandLength == 1 && isTwoPartCmd(inputs[0]);
    }
    
    public boolean isOnePartCmd(String s) {
        return ONE_PART_COMMAND.contains(s);
    }

    public boolean isTwoPartCmd(String s) {
        return TWO_PART_COMMAND.contains(s);
    }


    public static boolean isInvalidTaskCount(int taskCount, int taskNumber) {
        return taskNumber <= 0 || taskNumber > taskCount;
    }
    
    public boolean isDone(int commandLength, String s) {
        return commandLength == 2 && s.equals("done");
    }

    public boolean isEvent(int commandLength, String s) {
        return commandLength == 2 && s.equals("event");
    }

    public boolean isDeadline(int commandLength, String s) {
        return commandLength == 2 && s.equals("deadline");
    }

    public boolean isTodo(int commandLength, String s) {
        return commandLength == 2 && s.equals("todo");
    }

    public boolean isList(int commandLength, String s) {
        return commandLength == 1 && s.equals("list");
    }
    
}
