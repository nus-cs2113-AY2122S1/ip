package duke.processes.commands;

import duke.Duke;
import duke.exceptions.DateTimeException;
import duke.exceptions.TaskException;
import duke.exceptions.TimeException;
import duke.processes.tasks.Task;
import duke.processes.tasks.Deadlines;
import duke.processes.tasks.Event;
import duke.processes.tasks.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {

    protected static final int TIME_COMMAND = 4;
    protected static final int DEADLINE_LENGTH = 13;
    protected static final int EVENT_LENGTH = 10;
    protected static final int TODO_LENGTH = 9;

    protected static String taskDescription;
    protected static String taskTime;
    protected static String taskType;

    /* Indicates if and error occurs due to the wrong format typed by the user */
    protected static boolean isCorrectFormat;

    protected LocalDateTime formattedDate;
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HHmm");

    /**
     * Checks to see if the format of
     * the UserInput is correct and throws an error message if it is incorrect.
     * Additionally, parses to see which type of task is added and prepares the
     * required data for that Task type to by calling the prepare methods. Changes
     * the value of isCorrectFormat to false if the format typed by the user is invalid.
     *
     * @param command  an array of strings of the users response
     * @param response the direct string message of the users response
     */
    public AddCommand(String[] command, String response) {

        isCorrectFormat = true;

        try {
            if ((command.length == 1) || (command[1].isEmpty())) {
                throw new TaskException("please specify what to add");
            }
            taskType = command[1];
            if (!(taskType.equalsIgnoreCase("deadline") || taskType.equalsIgnoreCase("event")
                    || taskType.equalsIgnoreCase("todo"))) {
                throw new TaskException("Invalid Task entered please only add (deadline, event, todo)");
            }
            if ((command.length == 2) || (command[2].isEmpty())) {
                throw new TaskException("please specify task to add");
            }
            if (command[1].equalsIgnoreCase("todo")) {
                prepareTodo(response);
            } else {
                if (command[1].equalsIgnoreCase("deadline")) {
                    prepareDeadline(response);
                } else if (command[1].equalsIgnoreCase("event")) {
                    prepareEvent(response);
                }
            }
        } catch (TaskException | TimeException | DateTimeException e) {
            System.out.println(e.getMessage());
            isCorrectFormat = false;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("please check format. Use help function for guide!");
            isCorrectFormat = false;
        }
    }

    /**
     * initializes the specific Task of type ToDo
     *
     * @param response the direct string message of the users response
     */
    private void prepareTodo(String response) {

        taskTime = " ";
        formattedDate = LocalDateTime.now();
        taskDescription = response.substring(TODO_LENGTH);
    }

    /**
     * initializes the specific Task of type Event.
     * Throws TimeException when time is not specified by the user and Throws
     * DateTimeException when time indicated by user is in the past.
     *
     * @param response the direct string message of the users response
     * @throws TimeException     exception when user does not specify Date and Time
     * @throws DateTimeException exception when user specifies a Date and Time in the past
     */
    private void prepareEvent(String response) throws TimeException, DateTimeException {

        if (response.indexOf("/") <= 0) {
            throw new TimeException("when is it being held? " +
                    "[indicate by adding: /at-dd/MM/yyyy-HH:mm");
        }

        taskDescription = response.substring(EVENT_LENGTH, response.indexOf("/") - 1);
        String date = response.substring(response.indexOf("/") + TIME_COMMAND);

        try {
            formattedDate = LocalDateTime.parse(date, formatter);
            if (formattedDate.isBefore(LocalDateTime.now())) {
                throw new DateTimeException("Unfortunately we cannot travel back in Time. " +
                        "Please key in a valid date");
            }
        } catch (DateTimeParseException e) {
            throw new TimeException("please use this format for date and time: /at-dd/MM/yyyy-HHmm ");
        }
    }

    /**
     * initializes the specific Task of type Deadline.
     * Throws TimeException when time is not specified by the user and Throws
     * DateTimeException when time indicated by user is in the past.
     *
     * @param response the direct string message of the users response
     * @throws TimeException     exception when user does not specify Date and Time
     * @throws DateTimeException exception when user specifies a Date and Time in the past
     */
    private void prepareDeadline(String response) throws TimeException, DateTimeException {

        if (response.indexOf("/") <= 0) {
            throw new TimeException("when is it being held? " +
                    "[indicate by adding: /by-dd/MM/yyyy-HH:mm]");
        }
        taskDescription = response.substring(DEADLINE_LENGTH, response.indexOf("/") - 1);
        String date = response.substring(response.indexOf("/") + TIME_COMMAND);
        try {
            formattedDate = LocalDateTime.parse(date, formatter);
            if (formattedDate.isBefore(LocalDateTime.now())) {
                throw new DateTimeException("Unfortunately we cannot travel back in Time. Please key in a valid date");
            }
        } catch (DateTimeParseException e) {
            throw new TimeException("please use this format for date and time: /by-dd/MM/yyyy-HHmm ");
        }
    }

    /**
     * adds task to the main taskList array
     *
     * @param task the task to be added
     */
    private static void addToList(Task task) {

        Duke.taskList.add(task);
    }

    /**
     * executes the adding of a task the main taskList and returns a CommandResult which
     * would indicate whether the result is carried out successfully, if isCorrectFormat
     * is true
     *
     * @return returns a new CommandResult with a message indicating whether command was
     * executed successfully.
     */
    public CommandResult execute() {
        if (isCorrectFormat) {
            if (taskType.equalsIgnoreCase("event")) {
                Event event = new Event(taskDescription, formattedDate);
                addToList(event);
            } else if (taskType.equalsIgnoreCase("todo")) {
                ToDo task = new ToDo(taskDescription, formattedDate);
                addToList(task);
            } else if (taskType.equalsIgnoreCase("deadline")) {
                Deadlines work = new Deadlines(taskDescription, formattedDate);
                addToList(work);
            }

            return new CommandResult("Task added:" + taskDescription +
                    System.lineSeparator() + "Total no. of Tasks = " + (Duke.taskList.size()));
        } else {
            return new CommandResult("No task added");
        }
    }
}
