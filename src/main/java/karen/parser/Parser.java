package karen.parser;

import karen.exception.ValidityAndErrorCheck;
import karen.exception.IncorrectDescriptionFormatException;
import karen.exception.NoDescriptionException;
import karen.command.Command;
import karen.program.ProgramManager;
import karen.tasklist.TaskList;
import karen.tasklist.task.Deadline;
import karen.tasklist.task.Event;
import karen.tasklist.task.Task;
import karen.tasklist.task.ToDo;
import karen.ui.Ui;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String BYE_COMMAND = "bye";
    private static final String FIND_COMMAND = "find";
    private static final String SHOW_COMMAND = "show";
    private static final String DEADLINE_SEPARATOR = " /by ";
    private static final String EVENT_SEPARATOR = " /at ";
    private static final String TASK_DONE_STATUS = "X";

    private static TaskList taskList;
    private static ProgramManager programManager;

    public Parser(TaskList taskList, ProgramManager programManager) {
        this.taskList = taskList;
        this.programManager = programManager;
    }

    /**
     * Returns the query Keyword that user wants to use to search for matching Tasks in taskList,
     * as a String.
     *
     * This method takes in raw user input and then parses it to obtain the search query Keyword
     * input by user.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @return query Keyword given by user for searching matching tasks
     * @throws NoDescriptionException if Find Command is given without a query Keyword
     * @throws IncorrectDescriptionFormatException if Find Command is given with incorrect formatting
     */
    public String parseFind(String rawUserInput) throws NoDescriptionException, IncorrectDescriptionFormatException {
        ValidityAndErrorCheck.checkCommandDescriptionExceptions(FIND_COMMAND,rawUserInput);

        String[] inputSplit = rawUserInput.split(" ",2);
        String keyword = inputSplit[1].trim();
        return keyword;
    }

    /**
     * Returns the query Date that user wants to use to search for Tasks in taskList occurring on
     * that given date.
     *
     * This method takes in raw user input and then parses it to obtain the search query Date given
     * by user.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @return query Date given by user for searching Tasks occurring on that date.
     * @throws NoDescriptionException if Show Command is given without any Date
     * @throws IncorrectDescriptionFormatException if Show Command is given with incorrect formatting
     * @throws DateTimeParseException if Show Command has incorrect formatting for Date given by user/
     * invalid Date
     */
    public LocalDate parseShow(String rawUserInput)
            throws NoDescriptionException, IncorrectDescriptionFormatException, DateTimeParseException {
        ValidityAndErrorCheck.checkCommandDescriptionExceptions(SHOW_COMMAND, rawUserInput);

        String[] inputWords = rawUserInput.split(" ",0);
        String dateString = inputWords[1].trim();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate parsedDate = LocalDate.parse(dateString, dateFormatter);
        return parsedDate;
    }

    /**
     * Returns an Event object to be added into the TaskList.
     *
     * This method takes in the raw user input as a parameter and then parses
     * it to obtain the description of the Event task to be done, as well as
     * the date and time it occurs. An event object is then constructed
     * using the parsed information and returned.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @return the Event object constructed from the user inputs
     * @throws NoDescriptionException if an Event command is given without any task description
     * @throws IncorrectDescriptionFormatException if an Event command is given with incorrect formatting
     */
    public Event parseEvent(String rawUserInput) throws NoDescriptionException, IncorrectDescriptionFormatException {
        // checks if the input for Event Command is given correctly, ie. correct formatting and task description is given
        ValidityAndErrorCheck.checkTaskExceptions(EVENT_COMMAND, EVENT_SEPARATOR, rawUserInput);
        String fullTaskDescription = parseFullTaskDescription(rawUserInput);
        // obtains a list of 2 Strings, which includes the task description and the at string
        String[] descriptionWords = fullTaskDescription.split(EVENT_SEPARATOR, 2);
        String eventTaskDescription = descriptionWords[0].trim();
        String at = descriptionWords[1].trim();

        LocalDate date = parseDate(at);
        LocalTime time = parseTime(at);

        Event event = new Event(eventTaskDescription, at, date, time);
        return event;
    }

    /**
     * Returns a Deadline object to be added into the TaskList.
     *
     * This method takes in the raw user input as a parameter and then parses
     * it to obtain the description of the Deadline task to be done, as well as
     * the date and time to complete the task. A Deadline object is then constructed
     * using the parsed information and returned.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @return the Deadline object constructed from the user inputs
     * @throws NoDescriptionException if a Deadline command is given without any task description
     * @throws IncorrectDescriptionFormatException if a Deadline command is given with incorrect formatting
     */
    public Deadline parseDeadline(String rawUserInput) throws NoDescriptionException, IncorrectDescriptionFormatException {
        // checks if the input for Deadline Command is given correctly, ie. correct formatting and task description is given
        ValidityAndErrorCheck.checkTaskExceptions(DEADLINE_COMMAND, DEADLINE_SEPARATOR, rawUserInput);
        String fullTaskDescription = parseFullTaskDescription(rawUserInput);
        // obtains a list of 2 Strings, which includes the task description and the by string
        String[] descriptionWords = fullTaskDescription.split(DEADLINE_SEPARATOR, 2);
        String deadlineTaskDescription = descriptionWords[0].trim();
        String by = descriptionWords[1].trim();

        LocalDate date = parseDate(by);
        LocalTime time = parseTime(by);

        Deadline deadline = new Deadline(deadlineTaskDescription, by, date, time);
        return deadline;
    }

    /**
     * Returns a ToDo object to be added into the TaskList.
     *
     * This method takes in the raw user input as a parameter and then parses
     * it to obtain the ToDo task description. A ToDo object is then constructed
     * using the task description and returned.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @return the Deadline object constructed from the user inputs
     * @throws NoDescriptionException if a ToDo command is given without any task description
     * @throws IncorrectDescriptionFormatException if a ToDo command is given with incorrect formatting
     */
    public ToDo parseToDo(String rawUserInput) throws NoDescriptionException, IncorrectDescriptionFormatException {
        // checks if there is a task description given
        ValidityAndErrorCheck.checkTaskExceptions(TODO_COMMAND, "", rawUserInput);
        String todoTaskDescription = parseFullTaskDescription(rawUserInput);
        ToDo todo = new ToDo(todoTaskDescription);
        return todo;
    }

    /**
     * Returns the index of the task in TaskList that is to be marked as done.
     *
     * This method takes in the raw user input as a parameter and then parses
     * it to obtain the index of the task in the TaskList to be marked as done.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @return index of the task in the TaskList to be marked as done
     * @throws NoDescriptionException if a Done Command is given without any task number
     * @throws IncorrectDescriptionFormatException if a Done Command is given with incorrect formatting
     */
    public int parseDone(String rawUserInput) throws NoDescriptionException, IncorrectDescriptionFormatException {
        // checks if there are exactly 2 elements in the rawUserInput, and a number is given as task number.
        ValidityAndErrorCheck.checkCommandDescriptionExceptions(DONE_COMMAND, rawUserInput);
        String[] inputWords = rawUserInput.split(" ");
        int doneIndex = Integer.parseInt(inputWords[1]) - 1;
        return doneIndex;
    }

    /**
     * Returns the index of the task that is to be removed from TaskList.
     *
     * This method takes in the raw user input as a parameter and then parses
     * it to obtain the index of the task in the TaskList to be removed.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @return index of the task in the TaskList to be removed
     * @throws NoDescriptionException if a Delete Command is given without any task number
     * @throws IncorrectDescriptionFormatException if a Delete Command is given with incorrect formatting
     */
    public int parseDelete(String rawUserInput) throws NoDescriptionException, IncorrectDescriptionFormatException {
        // checks if there are exactly 2 elements in the rawUserInput, and a number is given as task number.
        ValidityAndErrorCheck.checkCommandDescriptionExceptions(DELETE_COMMAND, rawUserInput);
        String[] inputWords = rawUserInput.split(" ");
        int deleteIndex = Integer.parseInt(inputWords[1]) - 1;
        return deleteIndex;
    }

    /**
     * This method takes in the raw user input as a parameter and then parses
     * it to check if it is a valid Bye Command.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @throws NoDescriptionException if a Bye Command is given with incorrect formatting
     * @throws IncorrectDescriptionFormatException if a Bye Command is given with incorrect formatting
     */
    public void parseBye(String rawUserInput) throws NoDescriptionException, IncorrectDescriptionFormatException {
        //checks if there is exactly 1 word, ie. "bye"
        ValidityAndErrorCheck.checkCommandDescriptionExceptions("bye", rawUserInput);
    }

    /**
     * Returns the type of input command given by the user as a String
     *
     * This method takes in the raw user input as a parameter and then parses
     * it to obtain the type of command given by the user to be executed in
     * the program, as a String.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @return the input command type given by user as a String.
     */
    public String parseInputCommand(String rawUserInput) {
        String[] inputWords = rawUserInput.toLowerCase().split(" ");
        String inputCommand = inputWords[0];
        return inputCommand;
    }

    /**
     * Returns the full task description of the Task command given by the user
     *
     * This method takes in the raw user input as a parameter when one of the
     * Task commands has been given by user. The raw user input is then parsed
     * to obtain the full description of the task. If it is a ToDo task, the
     * full description is just the task description. If it is a Deadline or
     * Event task, the full description includes both the task description as
     * well as the date and time to be done.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @return full description of the task as a String
     */
    public String parseFullTaskDescription(String rawUserInput) {
        int startIndex = rawUserInput.indexOf(" ") + 1;
        String FullTaskDescription = rawUserInput.substring(startIndex);
        return FullTaskDescription;
    }

    /**
     * Returns the date of task as a LocalDate from the given input String containing
     * both the date and time of the task.
     *
     * This method only can read date String that are formatted as "dd-MM-yyyy", eg.
     * "21-02-2019".
     *
     * @param dateAndTime String containing both date and time of the task
     * @return a LocalDate object to represent the date of task
     */
    public static LocalDate parseDate(String dateAndTime) {
        String[] splitDateAndTime = dateAndTime.split(" ", 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String dateString = splitDateAndTime[0].trim();
        LocalDate date = LocalDate.parse(dateString, formatter);
        return date;
    }

    /**
     * Returns the time of task as a LocalDate from the given input String containing
     * both the date and time of the task.
     *
     * This method only can read time String that are formatted as "HHmm", eg.
     * "1830". If there is no input time, this method returns null.
     *
     * @param dateAndTime String containing both date and time of the task
     * @return a LocalTime object to represent the time of task
     */
    public static LocalTime parseTime(String dateAndTime) {
        String[] splitDateAndTime = dateAndTime.split(" ", 0);

        if (splitDateAndTime.length == 2) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");

            String timeString = splitDateAndTime[1].trim();
            LocalTime time = LocalTime.parse(timeString, formatter);
            return time;
        }
        return null;
    }

    /**
     * This method parses the raw user input to find out the input command given by user.
     * The input command is then executed using the Command Object. This method also catches
     * any exceptions thrown during the parsing and execution of commands, and prints their
     * respective error messages.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     */
    public void parseInput(String rawUserInput) {
        String inputCommand = parseInputCommand(rawUserInput);
        Command command = new Command(inputCommand, this.programManager, taskList);
        try {
            switch (inputCommand) {
            case FIND_COMMAND:
                String keyword = parseFind(rawUserInput);
                command.executeFindCommand(keyword, taskList.getTaskList());
                break;
            case SHOW_COMMAND:
                LocalDate localdate = parseShow(rawUserInput);
                command.executeShowCommand(localdate, taskList.getTaskList());
                break;
            case LIST_COMMAND:
                command.executeListCommand();
                break;
            case DONE_COMMAND:
                int doneIndex = parseDone(rawUserInput);
                command.executeDoneCommand(doneIndex);
                break;
            case TODO_COMMAND:
                ToDo todo = parseToDo(rawUserInput);
                command.executeToDoCommand(todo);
                break;
            case DEADLINE_COMMAND:
                Deadline deadline = parseDeadline(rawUserInput);
                command.executeDeadlineCommand(deadline);
                break;
            case EVENT_COMMAND:
                Event event = parseEvent(rawUserInput);
                command.executeEventCommand(event);
                break;
            case DELETE_COMMAND:
                int deleteIndex = parseDelete(rawUserInput);
                command.executeDeleteCommand(deleteIndex);
                break;
            case BYE_COMMAND:
                parseBye(rawUserInput);
                command.executeByeCommand();
                break;
            default:
                Ui.printInvalidCommandMessage();
                break;
            }
        } catch (NoDescriptionException e) {
            Ui.printNoDescriptionMessage();
        } catch (IncorrectDescriptionFormatException e) {
            Ui.printIncorrectDescriptionFormatMessage();
        } catch (NumberFormatException e) {
            Ui.printNumberFormatMessage();
        } catch (IndexOutOfBoundsException e) {
            Ui.printIndexOutOfBoundsMessage();
        } catch (IOException e) {
            Ui.printIOExceptionMessage();
        } catch (DateTimeParseException e) {
            Ui.printDateTimeErrorMessage();
        }

    }

    /**
     * Returns a Task object in the form of either a Deadline, an Event or a ToDo.
     *
     * This method takes in data from the storage file as a String and then parses it
     * to obtain the task type, the task status, the task description, as well as the
     * task date and time, if any. The Task object is then constructed accordingly with
     * respect to the task type and parsed information, which will then be returned.
     *
     * @param fileData a String containing information about a task in the storage file
     * @return Task object in the form of either a Deadline, an Event or a ToDo.
     */
    public static Task parseFileData(String fileData) {
        String splitData[] = fileData.split("@");

        String taskType = splitData[0].toLowerCase();
        String taskStatus = splitData[1];
        String taskDescription = splitData[2].trim();

        Task task = new Task(taskDescription);

        switch (taskType) {
        case TODO_COMMAND:
            task = new ToDo(taskDescription);
            break;
        case DEADLINE_COMMAND:
            String by = splitData[3].trim();
            LocalDate deadlineDate = parseDate(by);
            LocalTime deadlineTime = parseTime(by);
            task = new Deadline(taskDescription, by, deadlineDate, deadlineTime);
            break;
        case EVENT_COMMAND:
            String at = splitData[3].trim();
            LocalDate eventDate = parseDate(at);
            LocalTime eventTime = parseTime(at);
            task = new Event(taskDescription, at, eventDate, eventTime);
            break;
        default:
            Ui.printIOExceptionMessage();
            break;
        }
        if (taskStatus.equals(TASK_DONE_STATUS)) {
            task.markAsDone();
        }
        return task;
    }
}
