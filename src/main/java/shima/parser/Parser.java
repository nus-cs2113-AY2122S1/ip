package shima.parser;

import shima.command.AddDeadlineCommand;
import shima.command.AddEventCommand;
import shima.command.AddToDoCommand;
import shima.command.Command;
import shima.command.DeleteCommand;
import shima.command.DoneCommand;
import shima.command.ExitCommand;
import shima.command.FindCommand;
import shima.command.HelpCommand;
import shima.command.ListCommand;
import shima.command.DateCommand;
import shima.command.ViewPersonalityCommand;
import shima.design.UserInterface;
import shima.storage.Storage;


import shima.task.Deadline;
import shima.task.Task;
import shima.task.TaskList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Parser {
    public static final String COMMAND_PROFILE = "profile";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_DONE = "done";

    public static final String INVALID_COMMAND_MSG = "Sorry, the command is invalid, I cant understand... ";
    public static final String HELP_SUPPORT_MSG = "To seek for help, you can type the command \"help\" to view the help menu";
    public static final String EMPTY_TASK_MSG = "Sorry, the task is empty! I don't know how to record it :(";
    public static final String SLASH_MISSING_MSG = "Sorry, fail to create an Event, the time specific character '/' is missing";
    public static final String DASH_MISSING_MSG = "Sorry, fail to create an Event, the period specific character '-' is missing";
    public static final String EMPTY_TASK_INDEX_MSG = "Sorry, the input task number is missing, please try again! :(";
    public static final String EMPTY_KEYWORD_MSG = "Sorry, the keyword is empty! I do not know which task you are looking for :(";
    public static final String NO_MATCHING_TASK_MSG = "\tHmm... I do not find any task matches the keyword, please use another keyword!";
    public static final String EMPTY_DEADLINE_MSG = "Sorry, the deadline for the task is missing! I don't know how to memorize it:(";
    public static final String EMPTY_PERIOD_MSG = "Sorry, the date and period for the task is missing! I don't know how to memorize it :(";
    public static final String EXPIRED_DEADLINE_MSG = "Oops, the end date and time for the task is already expired, please give the task a new deadline!";
    public static final String INCORRECT_DATE_TIME_FORMAT_MSG1 = "Oh no! The date and time are incorrect!";
    public static final String INCORRECT_DATE_TIME_FORMAT_MSG2 = "The format of input must be yyyy:MM:dd HH:mm";
    public static final String INCORRECT_DATE_TIME_FORMAT_MSG3 = "Also, make sure that you have provide a valid date and time!";
    public static final String INVALID_DATE_TIME_MSG = "Sorry, the input after the backslash '/' or '/by' should only contains " +
            "date and time of format (yyyy-MM-dd HH:mm)";
    public static final String COMMAND_DATE = "date";
    public static final String WRONG_DATE_TIME_FORMAT_MSG = "Sorry, either the input date format is invalid or incorrect! Take note that it" +
            " should only contains date with format yyyy-MM-dd";
    public static final String INVALID_DATE_MSG = "Sorry, the input date should only contains yyyy-MM-dd";
    public static final String COMMAND_FIND = "find";


    /**
     * Reads the input command entered by the user and handle each command
     *
     * @param tasks   The list class object that stores all the tasks
     * @param storage The storage class object that used to save data
     * @param ui      The user interface class object used to display message box
     * @return Returns the respective command to each input accordingly
     * @throws IOException Throws this exception when there is error occurs when accessing external file
     */
    public static Command readCommand(TaskList tasks, Storage storage, UserInterface ui) throws IOException {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine().trim();
        String[] words = command.split(" ");
        switch (words[0].toLowerCase()) {
        case COMMAND_PROFILE:
            return new ViewPersonalityCommand();
        case COMMAND_HELP:
            return new HelpCommand();
        case COMMAND_TODO:
            return parseToDo(tasks, command, words, ui);
        case COMMAND_EVENT:
            return parseEvent(tasks, command, words, ui);
        case COMMAND_DEADLINE:
            return parseDeadline(tasks, command, words, ui);
        case COMMAND_LIST:
            return new ListCommand(tasks);
        case COMMAND_DONE:
            return parseDoneCommand(tasks, storage, words, ui);
        case COMMAND_DELETE:
            return new DeleteCommand(tasks, words);
        case COMMAND_DATE:
            return parseDateCommand(tasks, command, words, ui);
        case COMMAND_FIND:
            return parseFindCommand(tasks, command, words, ui);
        case COMMAND_BYE:
        case COMMAND_EXIT:
            return new ExitCommand(tasks, storage, ui);
        default:
            ui.showMessage(INVALID_COMMAND_MSG, HELP_SUPPORT_MSG);
            return new Command();
        }
    }

    /**
     * Checks if the find command syntax and keyword are valid
     *
     * @param tasks   The task list class object that stores all the tasks
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     * @param ui      The user interface class object used to display message box
     * @return Returns a new find command object if the syntax is correct, return empty command otherwise
     */
    private static Command parseFindCommand(TaskList tasks, String command, String[] words, UserInterface ui) {
        String keyword = command.replaceFirst(words[0], "").trim();
        if (keyword.isEmpty()) {
            ui.showMessage(EMPTY_KEYWORD_MSG);
            return new Command();
        }
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t : tasks.getTasks()) {
            if (t.toString().contains(keyword)) {
                matchingTasks.add(t);
            }
        }
        if (matchingTasks.isEmpty()) {
            ui.showMessage(NO_MATCHING_TASK_MSG);
            return new Command();
        }
        return new FindCommand(matchingTasks, ui);
    }

    /**
     * Checks if the input date is valid or in correct format
     *
     * @param tasks   The list class object that stores all the tasks
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     * @param ui      The user interface class object used to display message box
     * @return Returns the list date command object if the input command is valid, returns an empty command otherwise
     */
    private static Command parseDateCommand(TaskList tasks, String command, String[] words, UserInterface ui) {
        String inputDateString = command.replaceFirst(words[0], "").trim();
        try {
            inputDateString = padDate(inputDateString);
            LocalDate inputDate = LocalDate.parse(inputDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            ArrayList<Task> allDeadlines = (ArrayList<Task>) tasks.getTasks().stream().filter(t -> t instanceof Deadline).collect(Collectors.toList());
            ArrayList<Deadline> resultList = new ArrayList<>();
            for (Task t : allDeadlines) {
                Deadline deadline = (Deadline) t;
                if (isSameDate(inputDate, deadline)) {
                    resultList.add(deadline);
                }
            }
            return new DateCommand(resultList);
        } catch (DateTimeParseException dateTimeParseException) {
            ui.showMessage(WRONG_DATE_TIME_FORMAT_MSG);
            return new Command();
        } catch (NumberFormatException numberFormatException) {
            ui.showMessage(INVALID_DATE_MSG);
            return new Command();
        }
    }

    /**
     * Checks if the two dates are the same
     *
     * @param inputDate The user input date
     * @param deadline  The deadline stored in the tasks list class object
     * @return Returns true if the two dates are exactly the same, false otherwise
     */
    private static boolean isSameDate(LocalDate inputDate, Deadline deadline) {
        return deadline.getEndTime().getYear() == inputDate.getYear() &&
                deadline.getEndTime().getMonth() == inputDate.getMonth() &&
                deadline.getEndTime().getDayOfMonth() == inputDate.getDayOfMonth();
    }

    /**
     * Checks the syntax for the command to create a new task
     *
     * @param words The array of words that compose the input command
     * @param ui    The user interface class object used to display message box
     * @return Returns true if an instance of the subclass is created and successfully stored in the to-do list
     */
    private static boolean isCorrectToDo(String[] words, UserInterface ui) {
        if (words.length == 1) {
            ui.showMessage(EMPTY_TASK_MSG);
            return false;
        }
        return true;
    }

    /**
     * Checks the syntax for the command to create an 'Event' instance
     *
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     * @param ui      The user interface class object used to display message box
     * @return Returns true if an instance of the subclass Event is created and successfully stored in the to-do list
     */
    private static boolean isCorrectEvent(String command, String[] words, String taskName, String time, UserInterface ui) {
        if (words.length == 1 || taskName.isEmpty()) {
            ui.showMessage(EMPTY_TASK_MSG);
            return false;
        }
        if (time.isEmpty()) {
            ui.showMessage(EMPTY_PERIOD_MSG);
            return false;
        }
        if (!command.contains("/")) {
            ui.showMessage(SLASH_MISSING_MSG);
            return false;
        }
        if (!command.contains("-")) {
            ui.showMessage(DASH_MISSING_MSG);
            return false;
        }
        return true;
    }

    /**
     * Checks if the syntax for the deadline command is correct, and the date and time specified is in correct format and valid
     *
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     * @param ui      The user interface class object used to display message box
     * @return Returns true if the subclass Deadline is created and successfully stored in the to-do list
     */
    private static boolean isCorrectDeadline(String command, String[] words, String taskName, String time, UserInterface ui) {
        if (words.length == 1 || taskName.isEmpty()) {
            ui.showMessage(EMPTY_TASK_MSG);
            return false;
        }
        if (time.isEmpty()) {
            ui.showMessage(EMPTY_DEADLINE_MSG);
            return false;
        }
        if (!command.contains("/")) {
            ui.showMessage(SLASH_MISSING_MSG);
            return false;
        }
        try {
            //pad input time
            time = padCorrectDateTimeFormat(time);
            System.out.println(time);
            LocalDateTime inputTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime now = LocalDateTime.now();
            if (inputTime.isBefore(now)) {
                ui.showMessage(EXPIRED_DEADLINE_MSG);
                return false;
            }
            return true;
        } catch (DateTimeParseException dateTimeParseException) {
            ui.showMessage(INCORRECT_DATE_TIME_FORMAT_MSG1, INCORRECT_DATE_TIME_FORMAT_MSG2, INCORRECT_DATE_TIME_FORMAT_MSG3);
            return false;
        } catch (NumberFormatException numberFormatException) {
            ui.showMessage(INVALID_DATE_TIME_MSG);
            return false;
        }
    }

    /**
     * Pads the input date time so that it has the same DateTimeFormat as yyyy-MM-dd HH:mm
     *
     * @param time The input time
     * @return Returns the time string with correct date time format
     */
    private static String padCorrectDateTimeFormat(String time) {
        time = padDate(time);
        time = padTime(time, time.indexOf(" "));
        return time;
    }

    /**
     * Pads the input month/day with a leading '0' to match the format of LocalDateTime
     *
     * @param time The input time string
     * @return Returns the time string padded with leading '0' for month/day of end date
     */
    private static String padDate(String time) {
        int indexOfDash = time.indexOf("-");
        time = pad_0_ToMonth(time, indexOfDash);
        time = pad_0_ToDay(time);
        return time.trim();
    }

    /**
     * Pads a leading 0 to the day domain if the input day contains only single digit
     *
     * @param time The input time string which the month domain is padded with 0 already
     * @return Returns the time string padded with leading '0' (if needed) for day domain
     */
    private static String pad_0_ToDay(String time) {
        try {
            if (isSingleDigitForDay(time)) {
                time = time.substring(0, time.lastIndexOf("-") + 1) + "0" + time.substring(time.lastIndexOf("-") + 1);
            }
        } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
            time += "  ";
            if (Integer.parseInt(time.substring(time.lastIndexOf("-") + 1, time.lastIndexOf("-") + 3).trim()) < 10) {
                time = time.substring(0, time.lastIndexOf("-") + 1) + "0" + time.substring(time.lastIndexOf("-") + 1);
            }
        }
        return time;
    }

    /**
     * Checks the string that contains date if needed to pad a leading 0 to day domain
     * @param time The input time string
     * @return Returns true if the day domain of the date in the time string only contains single digit, false otherwise
     */
    private static boolean isSingleDigitForDay(String time) {
        boolean isPotentialSingleDigit = Integer.parseInt(time.substring(time.lastIndexOf("-") + 1, time.lastIndexOf("-") + 3).trim()) < 10;
        boolean isLeading0 = time.charAt(time.lastIndexOf("-")  + 1) == '0';
        return isPotentialSingleDigit && !isLeading0;
    }

    /**
     * Pads a leading 0 to the month domain if the input month contains only single digit
     *
     * @param time The input time string
     * @param indexOfDash The first occurence of the dash character which indicates the position of the month domain
     * @return Returns the time string padded with 0 in month domain
     */
    private static String pad_0_ToMonth(String time, int indexOfDash) {
        if (isSingleDigitForMonth(time, indexOfDash)) {
            time = time.substring(0, indexOfDash + 1) + "0" + time.substring(indexOfDash + 1);
        }
        return time;
    }

    private static boolean isSingleDigitForMonth(String time, int indexOfDash) {
        return time.charAt(indexOfDash + 2) == '-';
    }

    /**
     * Pads the input hour with a leading '0' if it contains only single digit
     *
     * @param time         The input time string
     * @param indexOfColon Returns the time string padded with leading '0' for hour of end time
     * @return Return the time string padded with leading '0' for hour of end time
     */
    private static String padTime(String time, int indexOfColon) {
        if (Integer.parseInt(time.substring(time.indexOf(":") - 2, time.indexOf(":")).trim()) < 10) {
            time = time.substring(0, indexOfColon + 1) + "0" + time.substring(indexOfColon + 1);
        }
        return time;
    }

    /**
     * Checks if the to-do command is correct and creates a new add to-do command to execute
     *
     * @param tasks   The list class object that stores all the tasks
     * @param command The user input command
     * @param words   The array of words that compose the command
     * @param ui      The user interface class object used to display message box
     * @return Returns the add command object if the input command is valid, returns empty command object otherwise
     */
    private static Command parseToDo(TaskList tasks, String command, String[] words, UserInterface ui) {
        if (isCorrectToDo(words, ui)) {
            return new AddToDoCommand(tasks, command, words);
        }
        return new Command();
    }

    /**
     * Checks if the deadline command is correct and creates a new add deadline command to execute
     *
     * @param tasks   The list class object that stores all the tasks
     * @param command The user input command
     * @param words   The array of words that compose the command
     * @param ui      The user interface class object used to display message box
     * @return Returns the add command object if the input command is valid, returns empty command object otherwise
     */
    private static Command parseDeadline(TaskList tasks, String command, String[] words, UserInterface ui) {
        command = command.replaceFirst(words[0], "").trim();
        String time = command.substring(command.indexOf('/') + 1).trim();
        String taskName = command.split("/", 2)[0].trim();
        if (time.toLowerCase().startsWith("by")) {
            time = time.replaceFirst("(?i)by", "").trim();
        }
        if (isCorrectDeadline(command, words, taskName, time, ui)) {
            time = padCorrectDateTimeFormat(time);
            return new AddDeadlineCommand(tasks, command, words, time);
        }
        return new Command();
    }

    /**
     * Checks if the event command is correct and creates a new add event command to execute
     *
     * @param tasks   The list class object that stores all the tasks
     * @param command The user input command
     * @param words   The array of words that compose the command
     * @param ui      The user interface class object used to display message box
     * @return Returns the add command object if the input command is valid, returns empty command object otherwise
     */
    private static Command parseEvent(TaskList tasks, String command, String[] words, UserInterface ui) {
        command = command.replaceFirst(words[0], "").trim();
        String time = command.substring(command.indexOf('/') + 1).trim();
        String taskName = command.split("/", 2)[0].trim();
        if (time.toLowerCase().startsWith("at")) {
            time = time.replaceFirst("(?i)at", "").trim();
        }
        if (isCorrectEvent(command, words, taskName, time, ui)) {
            return new AddEventCommand(tasks, command, words, taskName, time);
        }
        return new Command();
    }

    /**
     * Checks if the done command is correct and creates a new done command to execute
     *
     * @param tasks   The list class object that stores all the tasks
     * @param storage The storage class object used to save data
     * @param words   The array of words that compose the command
     * @param ui      The user interface class object used to display message box
     * @return Returns the done command object if the input command is valid, returns an empty command otherwise
     */
    private static Command parseDoneCommand(TaskList tasks, Storage storage, String[] words, UserInterface ui) {
        if (words.length == 1) {
            ui.showMessage(EMPTY_TASK_INDEX_MSG);
            return new Command();
        }
        return new DoneCommand(tasks, storage, words);
    }
}
