package kate.parser;

import kate.command.ByeCommand;
import kate.command.Command;
import kate.command.DeadlineCommand;
import kate.command.DeleteCommand;
import kate.command.DoneCommand;
import kate.command.EventCommand;
import kate.command.FindCommand;
import kate.command.HelpCommand;
import kate.command.ListCommand;
import kate.command.ToDoCommand;
import kate.exception.EmptyFieldException;
import kate.exception.EmptyTaskException;
import kate.exception.FileCorruptedException;
import kate.exception.InvalidCommandException;
import kate.exception.InvalidDateTimeException;
import kate.exception.InvalidFieldException;
import kate.task.Task;
import kate.tasklist.TaskList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private static final int LENGTH_TODO = 4;
    private static final int LENGTH_DEADLINE = 8;
    private static final int LENGTH_EVENT = 5;
    private static final int LENGTH_DELETE = 6;
    private static final int LENGTH_FIND = 4;

    private static final int LENGTH_DEADLINE_ARRAY = 3;
    private static final int LENGTH_EVENT_ARRAY = 4;
    private static final int LENGTH_DEADLINE_ARRAY_PARSED = 2;
    private static final int LENGTH_EVENT_ARRAY_PARSED = 3;

    private static final String DELIM_PIPE = " \\| ";
    private static final String DELIM_SPACE = " ";
    private static final String DELIM_BY = " /by ";
    private static final String DELIM_AT = " /at ";

    private static final int LIMIT_ARRAY_DEADLINE = 2;
    private static final int LIMIT_ARRAY_EVENT = 2;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");

    private static final String DEFAULT_TIME = "2359";
    private static final String DEFAULT_DURATION = "0";


    public Parser() {
    }

    /**
     * Extracts the command parameter from the user input
     *
     * @param userInput Input provided by user
     * @return Command object of the given valid command
     * @throws InvalidCommandException If command is invalid
     */
    public static Command extractCommand(String userInput) throws InvalidCommandException {
        String[] inputArr = userInput.split(DELIM_SPACE);
        String givenCommand = inputArr[0].toUpperCase();

        switch (givenCommand) {
        case "TODO":
            return new ToDoCommand(userInput);
        case "DEADLINE":
            return new DeadlineCommand(userInput);
        case "EVENT":
            return new EventCommand(userInput);
        case "LIST":
            return new ListCommand();
        case "DONE":
            return new DoneCommand(userInput);
        case "DELETE":
            return new DeleteCommand(userInput);
        case "FIND":
            return new FindCommand(userInput);
        case "BYE":
            return new ByeCommand();
        case "HELP":
            return new HelpCommand();
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Processes the user input to extract description
     *
     * @param userInput Input provided by user
     * @return Task description for ToDo
     * @throws EmptyFieldException If task description is empty
     */
    public static String extractToDoInput(String userInput) throws EmptyFieldException {
        String taskDescription = userInput.substring(LENGTH_TODO).strip();

        if (taskDescription.isEmpty()) {
            throw new EmptyFieldException();
        }
        return taskDescription;
    }

    /**
     * Process the user input to extract description and deadline
     *
     * @param userInput Input provided by user
     * @return String array of description and deadline
     * @throws EmptyFieldException If description or deadline is empty
     */
    public static String[] extractDeadlineInput(String userInput) throws InvalidDateTimeException, EmptyFieldException {
        String taskInfo = userInput.substring(LENGTH_DEADLINE).strip();
        String[] taskInfoArr = taskInfo.split(DELIM_BY, LIMIT_ARRAY_DEADLINE);
        String[] taskInfoArrWithDateTime = new String[LENGTH_DEADLINE_ARRAY];

        try {
            String taskDescription = taskInfoArr[0].strip();
            String deadline = taskInfoArr[1].strip();
            boolean hasEmptyField = taskDescription.isEmpty() || deadline.isEmpty();

            if (hasEmptyField) {
                throw new EmptyFieldException();
            }

            String[] deadlineArr = processDeadline(deadline);
            String date = deadlineArr[0];
            String time = deadlineArr[1];

            taskInfoArrWithDateTime[0] = taskDescription;
            taskInfoArrWithDateTime[1] = date;
            taskInfoArrWithDateTime[2] = time;

            return taskInfoArrWithDateTime;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyFieldException();
        } catch (InvalidDateTimeException e) {
            throw new InvalidDateTimeException();
        }
    }

    /**
     * Process deadline into both date and time field and ensure they are of required format
     *
     * @param deadline Deadline field input by user
     * @return String array of field date and time respectively
     * @throws InvalidDateTimeException If date or time has invalid format
     */
    private static String[] processDeadline(String deadline) throws InvalidDateTimeException {
        String[] deadlineArr = deadline.split(DELIM_SPACE);
        String[] parsedDeadlineArr = new String[LENGTH_DEADLINE_ARRAY_PARSED];
        try {
            String date = deadlineArr[0];
            String parsedDate = String.valueOf(LocalDate.parse(date, DATE_FORMATTER));
            parsedDeadlineArr[0] = parsedDate;

            boolean hasTimeField = deadlineArr.length > 1;

            if (hasTimeField) {
                String time = deadlineArr[1];
                String parsedTime = String.valueOf(LocalTime.parse(time, TIME_FORMATTER));
                parsedDeadlineArr[1] = parsedTime;
            } else {
                String time = DEFAULT_TIME;
                parsedDeadlineArr[1] = time;
            }

            return parsedDeadlineArr;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    /**
     * Process the user input to extract description and time frame
     *
     * @param userInput Input provided by user
     * @return String array of description and time frame
     * @throws EmptyFieldException If description or time frame is empty
     */
    public static String[] extractEventInput(String userInput) throws InvalidDateTimeException, EmptyFieldException {
        String taskInfo = userInput.substring(LENGTH_EVENT).strip();
        String[] timeInfoArr = taskInfo.split(DELIM_AT, LIMIT_ARRAY_EVENT);
        String[] taskInfoArrWithDateTime = new String[LENGTH_EVENT_ARRAY];

        try {
            String taskDescription = timeInfoArr[0].strip();
            String timeFrame = timeInfoArr[1].strip();

            boolean hasEmptyField = taskDescription.isEmpty() || timeFrame.isEmpty();

            if (hasEmptyField) {
                throw new EmptyFieldException();
            }

            String[] timeFrameArr = processTimeFrame(timeFrame);
            String date = timeFrameArr[0];
            String time = timeFrameArr[1];
            String duration = timeFrameArr[2];

            taskInfoArrWithDateTime[0] = taskDescription;
            taskInfoArrWithDateTime[1] = date;
            taskInfoArrWithDateTime[2] = time;
            taskInfoArrWithDateTime[3] = duration;

            return taskInfoArrWithDateTime;
        } catch (ArrayIndexOutOfBoundsException | EmptyFieldException e) {
            throw new EmptyFieldException();
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    /**
     * Process time frame into both date, time and duration field and ensure they are of required format
     *
     * @param timeFrame Time frame field inputted by user
     * @return String array of field date and time respectively
     * @throws InvalidDateTimeException If date or time has invalid format
     * @throws EmptyFieldException      If time field is empty
     */
    private static String[] processTimeFrame(String timeFrame) throws InvalidDateTimeException, EmptyFieldException {
        String[] timeFrameArr = timeFrame.split(DELIM_SPACE);
        String[] parsedTimeFrameArr = new String[LENGTH_EVENT_ARRAY_PARSED];
        try {
            boolean hasDurationField = timeFrameArr.length == 3;

            String date = timeFrameArr[0];
            String parsedDate = String.valueOf(LocalDate.parse(date, DATE_FORMATTER));
            parsedTimeFrameArr[0] = parsedDate;

            String time = timeFrameArr[1];
            String parsedTime = String.valueOf(LocalTime.parse(time, TIME_FORMATTER));
            parsedTimeFrameArr[1] = parsedTime;

            if (hasDurationField) {
                String duration = timeFrameArr[2];
                String parsedDuration = String.valueOf(Integer.parseInt(duration));
                parsedTimeFrameArr[2] = parsedDuration;
            } else {
                parsedTimeFrameArr[2] = DEFAULT_DURATION;
            }

            return parsedTimeFrameArr;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyFieldException();
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    /**
     * Process user input to extract the object of the associated task number
     *
     * @param tasks     The list of tasks
     * @param userInput Input provided by user
     * @return Task object of the task that is done
     * @throws EmptyFieldException   If task number provided is empty
     * @throws InvalidFieldException If task number provided is invalid
     * @throws EmptyTaskException    If task list is empty
     */
    public static Task extractDoneInput(TaskList tasks, String userInput) throws EmptyFieldException,
            InvalidFieldException, EmptyTaskException {

        if (tasks.isEmptyTaskList()) {
            throw new EmptyTaskException();
        }

        String[] inputArr = userInput.split(DELIM_SPACE);
        try {
            String doneInput = inputArr[1];
            if (doneInput.isEmpty()) {
                throw new EmptyFieldException();
            }

            int taskNumber = Integer.parseInt(doneInput);
            if ((taskNumber > tasks.getTaskSize() || (taskNumber < 1) || inputArr.length != 2)) {
                throw new InvalidFieldException();
            }
            int taskNumberIndex = taskNumber - 1;

            return tasks.getCurrentTask(taskNumberIndex);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidFieldException();
        }
    }

    /**
     * Process user input to extract the deleted task number
     *
     * @param tasks     The list of tasks
     * @param userInput Input provided by the user
     * @return Task object of the deleted task
     * @throws EmptyFieldException   If task number provided is empty
     * @throws InvalidFieldException If task number provided is invalid
     * @throws EmptyTaskException    If task list is empty
     */
    public static Task extractDeleteInput(TaskList tasks, String userInput) throws EmptyFieldException,
            InvalidFieldException, EmptyTaskException {

        if (tasks.isEmptyTaskList()) {
            throw new EmptyTaskException();
        }

        String taskInput = userInput.substring(LENGTH_DELETE).strip();

        if (taskInput.isEmpty()) {
            throw new EmptyFieldException();
        }

        try {
            int taskNumber = Integer.parseInt(taskInput);

            if ((taskNumber > tasks.getTaskSize()) || (taskNumber < 1)) {
                throw new InvalidFieldException();
            }

            int taskIndex = taskNumber - 1;

            return tasks.getCurrentTask(taskIndex);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidFieldException();
        }
    }

    /**
     * Process the text input of the saved tasks and pass it into the current task list;
     *
     * @param tasks      TasksList object to add task to the task list
     * @param storedTask One line of the record in data/kate.txt
     * @throws FileCorruptedException If file content has been tampered cannot be parsed
     */
    public static void processData(TaskList tasks, String storedTask) throws FileCorruptedException {
        try {
            String[] storedArr = storedTask.split(DELIM_PIPE);
            String taskLabel = storedArr[0];
            boolean isDone = Boolean.parseBoolean(storedArr[1]);
            String description = storedArr[2];
            String date;
            String time;
            LocalDate parsedDate;
            LocalTime parsedTime;
            int parsedDuration;

            switch (taskLabel) {
            case "T":
                tasks.addToDoFromFile(description, isDone);
                break;
            case "D":
                date = storedArr[3];
                time = storedArr[4];
                parsedDate = LocalDate.parse(date);
                parsedTime = LocalTime.parse(time);
                tasks.addDeadlineFromFile(description, isDone, parsedDate, parsedTime);
                break;
            case "E":
                date = storedArr[3];
                time = storedArr[4];
                String duration = storedArr[5];
                parsedDate = LocalDate.parse(date);
                parsedTime = LocalTime.parse(time);
                parsedDuration = Integer.parseInt(duration);
                tasks.addEventFromFile(description, isDone, parsedDate, parsedTime, parsedDuration);
                break;
            default:
                throw new FileCorruptedException();
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new FileCorruptedException();
        }
    }

    /**
     * Process user input to extract keyword for filter
     *
     * @param userInput Input provided by user
     * @return Keyword for filtering
     * @throws EmptyFieldException If keyword is empty
     */
    public static String extractKeyword(String userInput) throws EmptyFieldException {
        String keyword = userInput.substring(LENGTH_FIND).strip();

        if (keyword.isEmpty()) {
            throw new EmptyFieldException();
        }
        return keyword;
    }
}
