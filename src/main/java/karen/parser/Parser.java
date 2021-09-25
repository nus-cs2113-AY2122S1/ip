package karen.parser;

import karen.exception.ValidityAndErrorCheck;
import karen.exception.IncorrectDescriptionFormatException;
import karen.exception.NoDescriptionException;
import karen.command.Command;
import karen.program.ProgramManager;
import karen.tasklist.TaskList;
import karen.tasklist.task.Deadline;
import karen.tasklist.task.Event;
import karen.tasklist.task.ToDo;
import karen.ui.Ui;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private static TaskList taskList;
    private static ProgramManager programManager;

    public Parser(TaskList taskList, ProgramManager programManager) {
        this.taskList = taskList;
        this.programManager = programManager;
    }




    public String parseFind(String rawUserInput) throws NoDescriptionException, IncorrectDescriptionFormatException {
        ValidityAndErrorCheck.checkCommandDescriptionExceptions(FIND_COMMAND,rawUserInput);

        String[] inputSplit = rawUserInput.split(" ",2);
        String keyword = inputSplit[1].trim();
        return keyword;
    }

    public LocalDate parseShow(String rawUserInput)
            throws NoDescriptionException, IncorrectDescriptionFormatException, DateTimeParseException {
        ValidityAndErrorCheck.checkCommandDescriptionExceptions(SHOW_COMMAND, rawUserInput);

        String[] inputWords = rawUserInput.split(" ",0);
        String dateString = inputWords[1].trim();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate parsedDate = LocalDate.parse(dateString, dateFormatter);
        return parsedDate;
    }

    public Event parseEvent(String rawUserInput)
            throws DateTimeParseException, NoDescriptionException, IncorrectDescriptionFormatException {
        ValidityAndErrorCheck.checkTaskExceptions(EVENT_COMMAND, EVENT_SEPARATOR, rawUserInput);

        String fullTaskDescription = parseFullTaskDescription(rawUserInput);

        String[] descriptionWords = fullTaskDescription.split(EVENT_SEPARATOR, 2);
        String at = descriptionWords[1].trim();

        int endIndex = fullTaskDescription.indexOf(EVENT_SEPARATOR);
        String eventTask = fullTaskDescription.substring(0, endIndex);

        Event event = new Event(fullTaskDescription, eventTask, at);
        return event;
    }

    public Deadline parseDeadline(String rawUserInput)
            throws DateTimeParseException, NoDescriptionException, IncorrectDescriptionFormatException {
        ValidityAndErrorCheck.checkTaskExceptions(DEADLINE_COMMAND, DEADLINE_SEPARATOR, rawUserInput);

        String fullTaskDescription = parseFullTaskDescription(rawUserInput);

        String[] descriptionWords = fullTaskDescription.split(DEADLINE_SEPARATOR, 2);
        String by = descriptionWords[1].trim();

        int endIndex = fullTaskDescription.indexOf(DEADLINE_SEPARATOR);
        String deadlineTask = fullTaskDescription.substring(0, endIndex);

        Deadline deadline = new Deadline(fullTaskDescription, deadlineTask, by);
        return deadline;
    }

    public ToDo parseToDo(String rawUserInput) throws NoDescriptionException, IncorrectDescriptionFormatException {
        ValidityAndErrorCheck.checkTaskExceptions(TODO_COMMAND, "", rawUserInput);
        String fullTaskDescription = parseFullTaskDescription(rawUserInput);
        ToDo todo = new ToDo(fullTaskDescription);
        return todo;
    }

    public int parseDone(String rawUserInput) throws NoDescriptionException, IncorrectDescriptionFormatException {
        ValidityAndErrorCheck.checkCommandDescriptionExceptions(DONE_COMMAND, rawUserInput);
        String[] inputWords = rawUserInput.split(" ", 0);
        int doneIndex = Integer.parseInt(inputWords[1]) - 1;
        return doneIndex;
    }

    public int parseDelete(String rawUserInput) throws NoDescriptionException, IncorrectDescriptionFormatException {
        ValidityAndErrorCheck.checkCommandDescriptionExceptions(DELETE_COMMAND, rawUserInput);
        String[] inputWords = rawUserInput.split(" ", 0);
        //find the index of the task to delete and the task itself
        int deleteIndex = Integer.parseInt(inputWords[1]) - 1;
        return deleteIndex;
    }


    public String parseCommand(String rawUserInput) {
        String[] inputWords = rawUserInput.toLowerCase().split(" ", 0);
        String taskCommand = inputWords[0];
        return taskCommand;
    }

    public String parseFullTaskDescription(String rawUserInput) {
        int startIndex = rawUserInput.indexOf(" ") + 1;
        String FullTaskDescription = rawUserInput.substring(startIndex);
        return FullTaskDescription;
    }

    public void parseInput(String rawUserInput) {
        String inputCommand = parseCommand(rawUserInput);
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
                command.executeByeCommand(rawUserInput);
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
}
