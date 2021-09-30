package duke;


import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.DeleteCommand;
import duke.command.InvalidCommand;

public class Parser {
    private static final String CMD_EXIT = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_DONE = "done";
    private static final String CMD_TODO = "todo";
    private static final String CMD_DEADLINE = "deadline";
    private static final String CMD_EVENT = "event";
    private static final String CMD_DELETE = "delete";

    /**
     * @param input String input from user
     * @return Command object corresponding to input command
     */
    public static Command parse(String input) {
        input = input.strip();

        if (input.equals(CMD_EXIT)) {
            return new ExitCommand();
        } else if (input.equals(CMD_LIST)) {
            return new ListCommand();
        } else if (input.startsWith(CMD_DONE)) {
            String parsedInput = input.split(" ")[1];
            int taskNo = Integer.parseInt(parsedInput);
            return new DoneCommand(taskNo - 1);
        } else if (input.startsWith(CMD_TODO)) {
            String parsedInput = input.replaceFirst(CMD_TODO, "");
            String todo = parsedInput.strip();
            return new TodoCommand(todo);
        } else if (input.startsWith(CMD_DEADLINE)) {
            String[] parsedInput = input.replaceFirst(CMD_DEADLINE, "").split("/by ");
            String deadlineTitle = parsedInput[0].strip();
            String deadlineDue = parsedInput[1].strip();
            return new DeadlineCommand(deadlineTitle, deadlineDue);
        } else if (input.startsWith(CMD_EVENT)) {
            String[] parsedInput = input.replaceFirst(CMD_EVENT, "").split("/at ");
            String eventTitle = parsedInput[0].strip();
            String eventTime = parsedInput[1].strip();
            return new EventCommand(eventTitle, eventTime);
        } else if (input.startsWith(CMD_DELETE)) {
            String parsedInput = input.split(" ")[1];
            int taskNo = Integer.parseInt(parsedInput);
            return new DeleteCommand(taskNo - 1);
        } else {
            return new InvalidCommand();
        }
    }
}
