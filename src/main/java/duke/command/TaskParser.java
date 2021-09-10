package duke.command;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import duke.task.*;

final public class TaskParser {
    static private List<Class<? extends Task>> taskTypes = new ArrayList<Class<? extends Task>>();

    public static void registerTaskType(Class<? extends Task> taskType) {
	taskTypes.add(taskType);
    }

    public static Task parseTask(String[] args) {
        String description;
        Task newTask = null;
        switch (args[0]) {
            case "deadline":
                int byPosition = Arrays.asList(args).indexOf("/by");
                description = String.join(" ", Arrays.copyOfRange(args, 1, byPosition));
                String by = String.join(" ", Arrays.copyOfRange(args, byPosition + 1, args.length));
                newTask = new Deadline(description, by);
                break;
            case "event":
                int atPosition = Arrays.asList(args).indexOf("/at");
                description = String.join(" ", Arrays.copyOfRange(args, 1, atPosition));
                String at = String.join(" ", Arrays.copyOfRange(args, atPosition + 1, args.length));
                newTask = new Event(description, at);
                break;
            case "todo":
		if (args.length == 1) {
		    throw new ArrayIndexOutOfBoundsException();
		}
                description = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                newTask = new Todo(description);
                break;
	    default:
		throw new UnsupportedOperationException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return newTask;
    }
}
