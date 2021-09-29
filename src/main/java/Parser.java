import duke.exception.*;
import duke.task.*;

/**
 * Parser deals with the command that user typed in
 */
public class Parser {
    /**
     * process the user command
     * @param str the string the user typed in
     * @return the command type
     */
    public static Commandtype processCommand(String str) {
        Commandtype type;
        if (str.equals("bye")) {
            type = Commandtype.BYE;
        } else if (str.equals("list")) {
            type = Commandtype.LIST;
        } else if (str.contains("find")) {
            type = Commandtype.FIND;
        } else if (str.contains("todo")) {
            type = Commandtype.TODO;
        } else if (str.contains("deadline")) {
            type = Commandtype.DEADLINE;
        } else if (str.contains("event")) {
            type = Commandtype.EVENT;
        } else if (str.contains("delete")) {
            type = Commandtype.DELETE;
        } else if (str.contains("done")) {
            type = Commandtype.DONE;
        } else {
            type = Commandtype.UNKNOWN;
        }
        return type;
    }
}
