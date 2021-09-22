import java.util.Arrays;
import java.util.List;

/**
 * A boolean sort of verification system that checks against conditions and returns true/false. 
 */
public class Verifier {
    /**
     * List of commands that only have one part to it.
     */
    public static final List<String> ONE_PART_COMMAND = Arrays.asList("list");

    /**
     * List of commands that have two parts to it.
     */
    public static final List<String> TWO_PART_COMMAND = Arrays.asList("todo", "done", "deadline", "event", "delete");
    
    
    public boolean isDelete(String input) {
        return input.equals("delete");
    }

    /**
     * Returns true if it is an invalid one part command.
     * 
     * @param inputs An array representing the different parts of the user input.
     * @param commandLength Number of parts of user input.
     * @return True if it is a one part command with more than one part in the input else return false.
     */
    public boolean isInvalidOnePartCmd(String[] inputs, int commandLength) {
        return commandLength > 1 && isOnePartCmd(inputs[0]);
    }

    public boolean isNotBye(String command) {
        return !command.equals("bye");
    }

    /**
     * Returns true if it is an invalid two part command.
     *
     * @param inputs An array representing the different parts of the user input.
     * @param commandLength Number of parts of user input.
     * @return True if it is a two part command with one part in the input else return false.
     */
    public boolean isInvalidTwoPartCmd(String[] inputs, int commandLength) {
        return commandLength == 1 && isTwoPartCmd(inputs[0]);
    }
    
    public boolean isOnePartCmd(String s) {
        return ONE_PART_COMMAND.contains(s);
    }

    public boolean isTwoPartCmd(String s) {
        return TWO_PART_COMMAND.contains(s);
    }

    /**
     * Returns true if input task count is invalid below 0 or more than the number of task in the list.
     * 
     * @param taskCount The number of tasks in the TaskList.
     * @param taskNumber The task number the user typed into as input.
     * @return true if task number is below 0 or more than the number of tasks in the list else return false.
     */
    public static boolean isInvalidTaskCount(int taskCount, int taskNumber) {
        return taskNumber <= 0 || taskNumber > taskCount;
    }
    
    public boolean isDone(String s) {
        return s.equals("done");
    }

    public boolean isEvent(String s) {
        return s.equals("event");
    }

    public boolean isDeadline(String s) {
        return s.equals("deadline");
    }

    public boolean isTodo(String s) {
        return s.equals("todo");
    }

    public boolean isList(String s) {
        return s.equals("list");
    }
    
}
