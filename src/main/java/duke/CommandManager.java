package duke;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.DeleteTaskCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Events;
import duke.task.ToDos;

public class CommandManager {
    //Commands stored as string constants
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";

    /**
     * Extracts command word from user input and processes the command.
     * @param userInput User's input
     * @return Command object based on the extracted command word.
     * @throws DukeException Throws exception to aid in identifying errors
     */
    public static Command processCommand(String userInput) throws DukeException {
        if (userInput.equalsIgnoreCase(EXIT_COMMAND)) {
            return new ExitCommand();
        } else if (userInput.equalsIgnoreCase(LIST_COMMAND)) {
            return new ListCommand();
        } else if ((userInput.toLowerCase()).contains(DONE_COMMAND)) {
            int taskIndex = extractTaskIndex(userInput);
            return new DoneCommand(taskIndex - 1);
        } else if ((userInput.toLowerCase()).contains(TODO_COMMAND)) {
            //4 is index after todo in input string
            String toDoDescription = userInput.substring(4);
            if (toDoDescription.equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return new AddTaskCommand(new ToDos(toDoDescription));
        } else if ((userInput.toLowerCase()).contains(EVENT_COMMAND)) {
            try {
                int endOfDescriptionIndex = userInput.indexOf("/");
                //5 is index after "event" in input string
                String eventDescription = userInput.substring(5, endOfDescriptionIndex);
                //3 is no of chars after "at"
                String at = userInput.substring(endOfDescriptionIndex + 3);
                if (at.equals("")) {
                    throw new DukeException("☹ OOPS!!! Please indicate when the event happens after /at");
                }
                return new AddTaskCommand(new Events(eventDescription, at));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! Please key in command in format: \"event taskDescription /at eventLocation\".");
            }
        } else if ((userInput.toLowerCase()).contains(DEADLINE_COMMAND)) {
            try {
                int endOfDescriptionIndex = userInput.indexOf("/");
                //8 is index after "deadline" in input string
                String deadlineDescription = userInput.substring(8, endOfDescriptionIndex);
                //3 is no of chars after 'by'
                String by = userInput.substring(endOfDescriptionIndex + 3);
                if (by.equals("")) {
                    throw new DukeException("☹ OOPS!!! Please indicate the deadline after /by");
                }
                return new AddTaskCommand(new Deadline(deadlineDescription, by));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! Please key in command in format: \"deadline taskDescription /by deadline\".");
            }
        } else if ((userInput.toLowerCase()).contains(DELETE_COMMAND)) {
            int taskIndex = extractTaskIndex(userInput);
            return new DeleteTaskCommand(taskIndex - 1);
        }
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Extracts index of task to delete or mark as done
     * @param userInput User input read as a string
     * @return taskIndex Returns index of task to delete or mark as done
     * @throws DukeException Throws exception to aid in identifying errors
     */
    private static int extractTaskIndex(String userInput) throws DukeException {
        String[] extractedCommand = userInput.split(" ");
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(extractedCommand[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Please provide a valid number!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Please specify index of the task!");
        }
        return taskIndex;
    }
}
