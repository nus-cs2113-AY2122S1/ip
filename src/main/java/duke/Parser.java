package duke;

import duke.commands.*;
import duke.tasks.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeParseException;

/**
 * Reads and formats all inputs from the user.
 */

public class Parser {
    /**
     * Returns a Command depending on the input from the user.
     * DukeException is thrown if it is unable to understand the command.
     *
     * @param input Innput from user from the commandline
     * @param taskList List of Existing task the user has
     * @param ui Duke object to handle responses to the user
     * @param storage object used to save all current tasks in Duke
     * @return Executable command.
     * @throws DukeException throws an exception when there is an error with the user input.
     */
    public static Command parse(String input, TaskList taskList, Ui ui, Storage storage) throws DukeException{
        if (input.contentEquals("bye")) {
            return new ByeCommand(ui);
        } else if (input.startsWith("todo")) {
            String taskDescription = input.replaceFirst("todo", "");
            taskDescription = taskDescription.stripLeading();
            if (taskDescription.isEmpty()) {
                throw new DukeException("Description cannot be empty!");
            }
            Task t = new Task(taskDescription);
            return new AddTaskCommand(taskList, t, ui, storage);
        } else if (input.startsWith("event")) {
            input = input.replaceFirst("event", "");
            String[] parsedInput = input.split("/at");
            if(parsedInput.length < 2){
                throw new DukeException("Please use the correct format! [EVENT DESCRIPTION] /at [YEAR-MONTH-DAY] [TIME]");
            }
            if (parsedInput[1].stripLeading().isEmpty()) {
                throw new DukeException("Date and time cannot be empty!");
        }
            String[] dateAndTime = parsedInput[1].stripLeading().split(" ");
            String[] dateCheck = dateAndTime[0].split("-");
            if(dateAndTime.length != 2){
                throw new DukeException("Please use the correct format for date and time! [EVENT DESCRIPTION] /at [YEAR-MONTH-DAY] [TIME] ");
            }
            if(dateCheck.length != 3){
                throw new DukeException("Please use the correct format for date and time! [EVENT DESCRIPTION] /at [YEAR-MONTH-DAY] [TIME] ");
            }
            if(Integer.parseInt(dateAndTime[1]) > 2400){
                throw new DukeException("PLease enter a number between 0 and 2400 when describing the time");
            }
            Task t = new Event(parsedInput[0], dateAndTime[0], dateAndTime[1]);
            return new AddTaskCommand(taskList, t, ui,storage);
        } else if (input.startsWith("deadline")) {
            input = input.replaceFirst("deadline", "");
            String[] parsedInput = input.split("/by");
            if(parsedInput.length < 2){
                throw new DukeException("Please use the correct format! [DEADLINE DESCRIPTION] /by [YEAR-MONTH-DAY] [TIME]");
            }
            if (parsedInput[1].stripLeading().isEmpty()) {
                throw new DukeException("Date and time cannot be empty!");
            }
            String[] dateAndTime = parsedInput[1].stripLeading().split(" ");
            String[] dateCheck = dateAndTime[0].split("-");
            if(dateAndTime.length != 2){
                throw new DukeException("Please use the correct format for date and time! [DEADLINE DESCRIPTION] /by [YEAR-MONTH-DAY] [TIME] ");
            }
            if(dateCheck.length != 3){
                throw new DukeException("Please use the correct format for date and time! [DEADLINE DESCRIPTION] /by [YEAR-MONTH-DAY] [TIME] ");
            }
            if(Integer.parseInt(dateAndTime[1]) > 2400){
                throw new DukeException("PLease enter a number between 0 and 2400 when describing the time");
            }
            Task t = new Deadline(parsedInput[0], dateAndTime[0], dateAndTime[1]);
            return new AddTaskCommand(taskList, t, ui,storage);
        } else if (input.startsWith("done")) {
            String[] parsedInput = input.split(" ");
            if (parsedInput.length < 2) {
                throw new DukeException("Sorry done ____ cannot  be empty");
            }
            if (Integer.parseInt(parsedInput[1]) >= taskList.size() + 1) {
                throw new DukeException("That is not in the list");
            }
            int task_index = Integer.parseInt(parsedInput[1]);
            return new DoneCommand(taskList, task_index - 1, ui,storage);
        } else if (input.startsWith("delete")) {
            String[] parsedInput = input.split(" ");
            if (parsedInput.length < 2) {
                throw new DukeException("Sorry done ____ cannot  be empty");
            }
            if (Integer.parseInt(parsedInput[1]) >= taskList.size() + 1) {
                throw new DukeException("That is not in the list");
            }
            int task_index = Integer.parseInt(parsedInput[1]);
            return new DeleteCommand(taskList, task_index - 1, ui, storage);
        }else if (input.startsWith("list")) {
            return new ListCommand(taskList, ui);
        } else if (input.startsWith("find")) {
            String[] parsedInput = input.split(" ");
            if(parsedInput.length != 2){
                throw new DukeException("Please follow the format: find [DESCRIPTION]");
            }
            return new FindCommand(taskList, ui, parsedInput[1]);
        }else{
            throw new DukeException("Sorry I dont understand");
        }
    }
}
