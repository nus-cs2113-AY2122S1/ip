package duke;

import duke.command.*;

public class CommandParser {
    private static final String EXIT_CMD = "bye";
    private static final String LIST_CMD = "list";
    private static final String DONE_CMD = "done";
    private static final String TODO_CMD = "todo";
    private static final String DEADLINE_CMD = "deadline";
    private static final String EVENT_CMD = "event";
    private static final String DELETE_CMD = "delete";

    public static Command parse(String command) {
        command = command.strip();

        if (command.equals(EXIT_CMD)) {
            return new ExitCommand();
        } else if (command.equals(LIST_CMD)) {
            return new ListCommand();
        } else if (command.startsWith(DONE_CMD)) {
            String parsedInput = command.split(" ")[1];
            int taskNo = Integer.parseInt(parsedInput);
            return new DoneCommand(taskNo - 1);
        } else if (command.startsWith(TODO_CMD)) {
            String parsedInput = command.replaceFirst(TODO_CMD, "");
            String todo = parsedInput.strip();
            return new TodoCommand(todo);
        } else if (command.startsWith(DEADLINE_CMD)) {
            String[] parsedInput = command.replaceFirst(DEADLINE_CMD, "").split("/by ");
            String deadlineTitle = parsedInput[0].strip();
            String deadlineDue = parsedInput[1].strip();
            return new DeadlineCommand(deadlineTitle, deadlineDue);
        } else if (command.startsWith(EVENT_CMD)) {
            String[] parsedInput = command.replaceFirst(EVENT_CMD, "").split("/at ");
            String eventTitle = parsedInput[0].strip();
            String eventTime = parsedInput[1].strip();
            return new EventCommand(eventTitle, eventTime);
        } else if (command.startsWith(DELETE_CMD)) {
            String parsedInput = command.split(" ")[1];
            int taskNo = Integer.parseInt(parsedInput);
            return new DeleteCommand(taskNo - 1);
        } else {
            return new InvalidCommand();
        }
    }
}
