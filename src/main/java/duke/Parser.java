package duke;

import duke.commands.Command;
import duke.commands.ListCommand;
import duke.commands.AddCommand;
import duke.commands.FindCommand;
import duke.commands.NoCommand;
import duke.commands.DoneCommand;
import duke.commands.DeleteCommand;
import duke.tasks.TaskList;
import duke.tasks.TaskType;

public class Parser {

    public final String LIST = "list";
    public final String TODO = "todo";
    public final String DEADLINE = "deadline";
    public final String EVENT = "event";
    public final String FIND = "find";
    public final String DONE = "done";
    public final String DELETE = "delete";
    public final String BYE = "bye";

    //executes program and updates task list according to user command
    public Command parseCommand(TaskList taskList, String line) {
        final String[] splitLine = line.split(" ", 2);
        final String command = splitLine[0];
        final String userCommand = line.replaceAll("^" + command + " ", "");
        if (command.equals(LIST)) {
            return new ListCommand(taskList);
        } else if (command.equals(TODO)) {
            return new AddCommand(taskList, userCommand, TaskType.TODO);
        } else if (command.equals(DEADLINE)) {
            return new AddCommand(taskList, userCommand, TaskType.DEADLINE);
        } else if (command.equals(EVENT)) {
            return new AddCommand(taskList, userCommand, TaskType.EVENT);
        } else if (command.equals(FIND)) {
            return new FindCommand(taskList, userCommand);
        } else if (command.equals(DONE)) {
            return new DoneCommand(taskList, userCommand);
        } else if (command.equals(DELETE)) {
            return new DeleteCommand(taskList, userCommand);
        }
        return new NoCommand(taskList);
    }
}
