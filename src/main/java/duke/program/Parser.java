package duke.program;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;

public class Parser {

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";

    private static final String TASK_TYPE_ICON_TODO = "T";
    private static final String TASK_TYPE_ICON_DEADLINE = "D";
    private static final String TASK_TYPE_ICON_EVENT = "E";

    private static final String SEPARATOR_SPACE = " ";

    /**
     * Parses user input into a command for execution.
     * @param line full user input string.
     * @return command based on given user input.
     */
    public Command parseUserInput(String line) {

        String[] lineArgs = line.split(SEPARATOR_SPACE);
        String command = lineArgs[0];

            if (line.equals(COMMAND_BYE)) {
                return new ByeCommand();
            } else if (command.equals(COMMAND_LIST)) {
                return new ListCommand();
            } else if (command.equals(COMMAND_DONE)) {
                return new DoneCommand(lineArgs);
            } else if (command.equals(COMMAND_DELETE)) {
                return new DeleteCommand(lineArgs);
            } else if (command.equals(COMMAND_TODO)) {
                return new AddCommand(line,TASK_TYPE_ICON_TODO);
            } else if (command.equals(COMMAND_DEADLINE)) {
                return new AddCommand(line, TASK_TYPE_ICON_DEADLINE);
            } else if (command.equals(COMMAND_EVENT)) {
                return new AddCommand(line, TASK_TYPE_ICON_EVENT);
            } else if (command.equals(COMMAND_FIND)) {
                return new FindCommand(lineArgs);
            } else {
                return new InvalidCommand();
            }

    }
}
