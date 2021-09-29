import duke.exception.*;
import duke.task.*;

public class Parser {
    public static Commandtype processCommand(String str, int maxlength) {
        Commandtype type;
        if (str.equals("bye")) {
            type = Commandtype.BYE;
        } else if (str.equals("list")) {
            type = Commandtype.LIST;
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
