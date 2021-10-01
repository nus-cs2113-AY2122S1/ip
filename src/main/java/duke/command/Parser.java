package duke.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import duke.task.Todo;
import duke.task.Task;
import duke.task.Event;
import duke.task.Deadline;
import java.time.LocalDate;

final public class Parser {
    static private final List<Class<? extends Task>> taskTypes =
        new ArrayList<>();

    public static void registerTaskType(Class<? extends Task> taskType) {
        taskTypes.add(taskType);
    }

    public static Task parseTask(String[] args) {
        String description;
        Task newTask;
        switch (args[0]) {
        case "deadline":
            int byPosition = Arrays.asList(args).indexOf("/by");
            description =
                String.join(" ", Arrays.copyOfRange(args, 1, byPosition));
            String byStr = String.join(
                " ", Arrays.copyOfRange(args, byPosition + 1, args.length));
            LocalDate byDate = LocalDate.parse(byStr);
            newTask = new Deadline(description, byDate);
            break;
        case "event":
            int atPosition = Arrays.asList(args).indexOf("/at");
            description =
                String.join(" ", Arrays.copyOfRange(args, 1, atPosition));
            String atStr = String.join(
                " ", Arrays.copyOfRange(args, atPosition + 1, args.length));
            LocalDate atDate = LocalDate.parse(atStr);
            newTask = new Event(description, atDate);
            break;
        case "todo":
            if (args.length == 1) {
                throw new ArrayIndexOutOfBoundsException();
            }
            description =
                String.join(" ", Arrays.copyOfRange(args, 1, args.length));
            newTask = new Todo(description);
            break;
        default:
            throw new UnsupportedOperationException(
                "OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return newTask;
    }
}
