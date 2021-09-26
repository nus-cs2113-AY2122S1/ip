package duke.parser;


import duke.exception.EmptyListException;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exception.InvalidTaskDescriptionException;
import duke.task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;


public class Parser {

    private static final String DATE_AND_TIME_FORMAT = "dd/MM/yyyy[ HH:mm]";
    private static final String PRINT_DATE_AND_TIME_FORMAT = "MMM dd yyyy HH:mm";

    public static String getCommand(String userInput) {
        String[] input = userInput.trim().toLowerCase().split(" ");
        return input[0];
    }

    public static void parseCommand(String userInput, ArrayList<Task> tasks) {
        try {
            String command = getCommand(userInput);
            switch (command) {
                // abstract variables later depending on which class to put under
            case ("bye"):
                Ui.printFarewellMessage();
                break;
            case ("list"):
                TaskList.requestList(tasks);
                break;
            case ("done"):
            case ("undo"):
                TaskList.changeDoneStatus(userInput, tasks);
                break;
            case ("todo"):
                TaskList.addTodo(userInput, tasks);
                break;
            case ("deadline"):
            case ("event"):
                TaskList.addDeadlineOrEvent(userInput, tasks);
                break;
            case ("delete"):
                TaskList.deleteTask(userInput, tasks);
                break;
            case ("find"):
                String filteredInput = getFindDescription(userInput);
                TaskList.findTasks(filteredInput, tasks);
                break;
            default:
                Ui.printErrorMessage();
                break;
            }
        } catch (InvalidTaskDescriptionException | EmptyListException e) {
            Ui.printHorizontalLine();
            System.out.println(e.getMessage());
            Ui.printHorizontalLine();
        }
    }

    public static String getTaskType(String userInput) {
        String[] description = userInput.trim().toLowerCase().split(" ");
        return description[0];
    }

    public static String getTodoDescription(String userInput) {
        int descriptionPosition = userInput.trim().indexOf(" ");
        return userInput.trim().substring(descriptionPosition);
    }

    public static int DeadlineOrEventDescriptionPosition(String userInput) {
        return userInput.trim().indexOf(" ");
    }

    public static int DeadlineOrEventTimePosition(String userInput) {
        return Math.max(userInput.trim().indexOf("/by"), userInput.trim().indexOf("/at"));
    }

    public static String getDeadlineOrEventDescription(String userInput) {
        int descriptionPosition = DeadlineOrEventDescriptionPosition(userInput);
        int timePosition = DeadlineOrEventTimePosition(userInput);
        return userInput.substring(descriptionPosition, timePosition);
    }

    public static String getDateAndTimeSubstring(String userInput) {
        int timePosition = DeadlineOrEventTimePosition(userInput);
        return userInput.substring(timePosition + 3).trim();
    }

    //https://stackoverflow.com/questions/48280447/java-8-datetimeformatter-with-optional-part
    public static LocalDateTime convertSubStringToDateAndTime(String input) {
        LocalDateTime dateAndTime = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_AND_TIME_FORMAT);
            TemporalAccessor temporalAccessor = formatter.parseBest(input, LocalDateTime::from, LocalDate::from);
            if (temporalAccessor instanceof LocalDateTime) {
                dateAndTime = (LocalDateTime) temporalAccessor;
            } else {
                dateAndTime = ((LocalDate) temporalAccessor).atStartOfDay();
            }
        } catch (DateTimeParseException e) {
            Ui.printHorizontalLine();
            System.out.println("Enter date and or time in this format: dd/MM/yyyy HH:mm (time is optional)");
            Ui.printHorizontalLine();
        }
        return dateAndTime;
    }

    public static String printDateAndTimeAsString(LocalDateTime dateAndTime) {
        return dateAndTime.format(DateTimeFormatter.ofPattern(PRINT_DATE_AND_TIME_FORMAT));
    }

    public static String storeDateAndTimeAsString(LocalDateTime dateAndTime) {
        return dateAndTime.format(DateTimeFormatter.ofPattern(DATE_AND_TIME_FORMAT));
    }

    public static int getTaskNumber(String userInput) {
        int taskNumberPosition = userInput.trim().indexOf(" ");
        return Integer.parseInt(userInput.trim().substring(taskNumberPosition + 1)) - 1;
    }

    public static boolean isValidTaskDescription(String userInput) {
        String[] description = userInput.trim().split(" ");
        return description.length > 1;
    }

    public static boolean isValidDeadlineOrEventDescription(String userInput, String description) {
        if (!isValidDeadlineFormat(userInput) && !isValidEventFormat(userInput)) {
            return false;
        } else if (description.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean isValidDeadlineFormat(String userInput) {
        return userInput.contains("/by");
    }

    public static boolean isValidEventFormat(String userInput) {
        return userInput.contains("/at");
    }

    public static String getFindDescription(String userInput) {
        return getTodoDescription(userInput);
    }

    public static boolean isDeleteAll(String userInput) {
        return userInput.trim().equalsIgnoreCase("delete all");
    }
}
