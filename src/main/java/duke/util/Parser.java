package duke.util;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalDate;

public class Parser {
    static final int MAX_STORED_TASKS = 100;
    static final int TODO_OFFSET = 5;
    static final int DEADLINE_OFFSET = 9;
    static final int EVENT_OFFSET = 6;
    static final int DATE_OFFSET = 4;

    private final Scanner userInput = new Scanner(System.in);
    private String userInputString;

    public boolean parseNextLine() {
        userInputString = userInput.nextLine();
        return !userInputString.equalsIgnoreCase("bye");
    }

    public Command processCommands(UI ui) {
        System.out.println(UI.DIVIDING_LINE);

        try {
            if (userInputString.equals("list")) {
                return new ListCommand();
            } else if (userInputString.startsWith("done ")) {
                String[] words = userInputString.split(" ");
                int completeIndex;

                try {
                    completeIndex = Integer.parseInt(words[1]) - 1;
                } catch (NumberFormatException e) {
                    throw new DukeException(DukeException.INDEX_INVALID);
                }

                if (completeIndex >= 0 && completeIndex < Task.getTotalTasks()) {
                    return new DoneCommand(completeIndex);
                } else {
                    throw new DukeException(DukeException.INDEX_OOB);
                }
            } else if (userInputString.startsWith("delete ")) {
                String[] words = userInputString.split(" ");
                int deleteIndex;

                try {
                    deleteIndex = Integer.parseInt(words[1]) - 1;
                } catch (NumberFormatException e) {
                    throw new DukeException(DukeException.INDEX_INVALID);
                }

                if (deleteIndex >= 0 && deleteIndex < Task.getTotalTasks()) {
                    return new DeleteCommand(deleteIndex);
                } else {
                    throw new DukeException(DukeException.INDEX_OOB);
                }
            } else {
                if (Task.getTotalTasks() >= MAX_STORED_TASKS) {
                    throw new DukeException(DukeException.TASK_ARRAY_FULL);
                }

                int slashIndex = userInputString.indexOf('/');
                String taskSubstring;
                String timeSubstring = userInputString.substring(slashIndex + DATE_OFFSET);
                LocalDate parsedDate;

                if (userInputString.startsWith("todo ")) {
                    taskSubstring = userInputString.substring(TODO_OFFSET);
                    if (taskSubstring.isBlank()) {
                        throw new DukeException(DukeException.TODO_BLANK_DESC);
                    }
                    return new AddCommand(new Todo(taskSubstring));
                } else if (userInputString.startsWith("deadline ")) {
                    if (slashIndex < 0) {
                        throw new DukeException(DukeException.DEADLINE_NO_SLASH);
                    }
                    if (timeSubstring.isBlank()) {
                        throw new DukeException(DukeException.DEADLINE_BLANK_DATE);
                    }
                    if (DEADLINE_OFFSET >= slashIndex - 1) {
                        throw new DukeException(DukeException.DEADLINE_BLANK_DESC);
                    }
                    try {
                        parsedDate = LocalDate.parse(timeSubstring);
                    } catch (DateTimeParseException e) {
                        throw new DukeException(DukeException.DATE_INVALID);
                    }
                    taskSubstring = userInputString.substring(DEADLINE_OFFSET, slashIndex - 1);
                    return new AddCommand(new Deadline(taskSubstring, parsedDate));
                } else if (userInputString.startsWith("event ")) {
                    if (slashIndex < 0) {
                        throw new DukeException(DukeException.EVENT_NO_SLASH);
                    }
                    if (timeSubstring.isBlank()) {
                        throw new DukeException(DukeException.EVENT_BLANK_DATE);
                    }
                    if (EVENT_OFFSET >= slashIndex - 1) {
                        throw new DukeException(DukeException.EVENT_BLANK_DESC);
                    }
                    try {
                        parsedDate = LocalDate.parse(timeSubstring);
                    } catch (DateTimeParseException e) {
                        throw new DukeException(DukeException.DATE_INVALID);
                    }
                    taskSubstring = userInputString.substring(EVENT_OFFSET, slashIndex - 1);
                    return new AddCommand(new Event(taskSubstring, parsedDate));
                } else {
                    throw new DukeException(DukeException.COMMAND_INVALID);
                }
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return null;
    }
}
