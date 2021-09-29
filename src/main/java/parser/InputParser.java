package parser;

import FridayExceptions.EmptyListException;
import FridayExceptions.MissingKeyWordException;
import FridayExceptions.MissingDateException;
import FridayExceptions.EmptyTaskNameException;
import FridayExceptions.InvalidIndexException;
import FridayExceptions.InvalidTaskIndexException;
import FridayExceptions.IncompleteCommandException;
import FridayExceptions.MissingQueryException;
import FridayExceptions.MissingIndexException;
import enums.Commands;
import enums.Errors;
import storage.UpdateData;
import ui.MessagePrinter;
import tasks.TaskList;

import java.util.Scanner;

public abstract class InputParser {
    // CONSTANTS for readability
    private static final String LIST = "list";
    private static final String BYE = "bye";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";
    private static final String DONE = "done";
    private static final String DEADLINE = "deadline";
    private static final String FIND = "find";
    private static final String BY= "/by";
    private static final String AT = "/at";
    private static final int TASK_INDEX = 1;
    private static final int TASKS_LOWER_BOUND = 0;
    private static final int TASKS_UPPER_BOUND = 99;

    // Functions to ease readability
    private static int taskNameStartIndex(String input) {
        return input.indexOf(" ") + 1;
    }

    private static int taskNameEndIndex(String input) {
        return input.indexOf("/");
    }

    private static int dateStartIndex(String input) {
        return input.indexOf("/") + 3;
    }

    /**
     * Main function managing all user inputs until program is terminated
     * via the "bye" command. Uses Command enums to break into switch cases.
     * Calls relevant functions updating data storage and tasks ArrayList
     */
    public static void parseUserInput() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine();
            Commands command = getCommand(userInput);

            // if bye, exit straight
            if (command == Commands.BYE) {
                return;
            }

            try {
                switch (command) {
                case INVALID:
                    MessagePrinter.invalidCommand();
                    break;
                case LIST:
                    TaskList.getList();
                    break;
                case TODO:
                    UpdateData.updateList(TaskList.addToDo(userInput, false, false));
                    break;
                case DEADLINE:
                    UpdateData.updateList(TaskList.addDeadline(userInput, false, false));
                    break;
                case EVENT:
                    UpdateData.updateList(TaskList.addEvent(userInput, false, false));
                    break;
                case DELETE:
                    TaskList.deleteTask(userInput);
                    break;
                case FIND:
                    TaskList.findTasks(userInput);
                    break;
                case DONE:
                    TaskList.markAsDone(userInput);
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
                MessagePrinter.outOfBoundsTaskIndex();
            } catch (InvalidIndexException e) {
                MessagePrinter.invalidIndex();
            } catch (InvalidTaskIndexException e) {
                MessagePrinter.invalidTaskIndex();
            } catch (EmptyTaskNameException e) {
                MessagePrinter.emptyTaskName();
            } catch (EmptyListException e) {
                MessagePrinter.emptyListMessage();
            } catch (IncompleteCommandException e) {
                MessagePrinter.incompleteCommand();
            } catch (MissingKeyWordException e) {
                MessagePrinter.missingKeyWord(e.getKeyword());
            } catch (MissingDateException e) {
                MessagePrinter.missingDate(e.getType());
            } catch(MissingIndexException e) {
                MessagePrinter.missingIndex(e.getCommand());
            } catch(MissingQueryException e) {
                MessagePrinter.missingQuery();
            }
        }
    }

    /**
     * Function returning Enum of respective commands back to function on top which
     * will invoke corresponding functions based on enum
     * @param input String of user input to parse
     * @return Command enum
     */
    public static Commands getCommand(String input) {
        // checks if it contains list or bye
        if (input.equals(LIST)) {
            return Commands.LIST;
        }

        if (input.equals(BYE)) {
            return Commands.BYE;
        }

        if (input.startsWith(TODO)) {
            return Commands.TODO;
        }

        if (input.startsWith(DEADLINE)) {
            return Commands.DEADLINE;
        }

        if (input.startsWith(EVENT)) {
            return Commands.EVENT;
        }

        if (input.startsWith(DELETE)) {
            return Commands.DELETE;
        }

        if (input.startsWith(DONE)) {
            return Commands.DONE;
        }

        if (input.startsWith(FIND)) {
            return Commands.FIND;
        }

        return Commands.INVALID;
    }

    /**
     * Function isolating taskName for deadline and event commands from user.
     * Parses from end of command (deadline, event) to start of /by or /at flag
     * @param input Full user input to parse
     * @return String of full task name
     */
    public static String getTaskName(String input) {
        return input.substring(taskNameStartIndex(input), taskNameEndIndex(input)).trim();
    }

    /**
     * Function isolating dates for deadline and event commands from user.
     * Parses from end of /by or /at flags to end of userInput
     * @param input Full user input to parse
     * @return String of date input by user
     */
    public static String getDate(String input) {
        return input.substring(dateStartIndex(input)).trim();
    }

    /**
     * Gets integer indicated by user after done / delete command
     * Integer input by user starts from 1; minus 1 to convert to 0 index number
     * @param input String of user input to parse
     * @return integer indicating index of task to mark as done or delete
     */
    public static int getTaskIndex(String input) {
        return Integer.parseInt(input.substring(input.indexOf(" ") + 1)) - 1;
    }

    /**
     * Gets query indicated by user after find command
     * @param input String of user input to parse
     * @return String containing query by user to search in tasks ArrayList
     */
    public static String getQuery(String input) {
        return input.substring(input.indexOf(" ") + 1).trim();
    }

    /**
     * Check validity of Deadline command
     * @param input String of user input to parse and check
     * @return Enum of corresponding Error that will result in an exception being thrown in TaskList
     */
    public static Errors checkDeadlineCommand(String input) {
        // Remove all extra spaces in the middle; only one space between each word
        String trimmedInput = input.replaceAll("\\s+", " ");
        String[] splitString = trimmedInput.split("\\s");
        // if no specifications at all i.e. length of split string == 1 WORKS
        if (splitString.length <= 1) {
            return Errors.INCOMPLETE_COMMAND;
        }
        // check if it contains BY WORKS
        if (!trimmedInput.contains(BY)) {
            return Errors.MISSING_BY;
        }
        // check if it contains a task name
        // If string right after command name is /by, user did not provide a task name
        if (splitString[1].equalsIgnoreCase(BY)) {
            return Errors.MISSING_TASK;
        }
        // check if there is a date after /by i.e. if last element is by WORKS
        if (input.trim().endsWith(BY)) {
            return Errors.MISSING_DEADLINE;
        }
        return Errors.NONE;
    }

    /**
     * Check validity of Event command
     * @param input String of user input to parse and check
     * @return Enum of corresponding Error that will result in an exception being thrown in TaskList
     */
    public static Errors checkEventCommand(String input) {
        // Remove all extra spaces in the middle; only one space between each word
        String trimmedInput = input.replaceAll("\\s+", " ");
        String[] splitString = trimmedInput.split("\\s");
        // if no specifications at all i.e length of split string == 1
        if (splitString.length <= 1) {
            return Errors.INCOMPLETE_COMMAND;
        }
        // check if it contains AT
        if (!trimmedInput.contains(AT)) {
            return Errors.MISSING_AT;
        }
        // check if it contains a task name
        // If string right after command name is /at, user did not provide a task name
        if (splitString[1].equalsIgnoreCase(AT)) {
            return Errors.MISSING_TASK;
        }
        // check if there is a date after /at i.e. if last element is at
        if (trimmedInput.trim().endsWith(AT)) {
            return Errors.MISSING_EVENT;
        }
        return Errors.NONE;
    }

    /**
     * Check validity of delete and done commands
     * @param input String of user input to parse and check
     * @return Enum of corresponding Error that will result in an exception being thrown in TaskList
     */
    public static Errors checkDeleteAndDoneCommand(String input) {
        String[] splitString = input.split("\\s");
        if (splitString.length <= 1) {
            return Errors.MISSING_INDEX;
        }
        int taskIndex;
        // Check if user input an integer for index
        try {
            taskIndex = Integer.parseInt(splitString[TASK_INDEX]);
        } catch (NumberFormatException e) {
            return Errors.INVALID_INDEX;
        }
        if (taskIndex < TASKS_LOWER_BOUND || taskIndex > TASKS_UPPER_BOUND)  {
            return Errors.OUT_OF_BOUNDS_INDEX;
        }
        return Errors.NONE;
    }

    /**
     * Check validity of find command
     * @param input String of user input to parse and check
     * @return Enum of corresponding Error that will result in an exception being thrown in TaskList
     */
    public static Errors checkFindCommand(String input) {
        String[] splitString = input.split("\\s");
        if (splitString.length <= 1) {
            return Errors.MISSING_QUERY;
        }
        return Errors.NONE;
    }
}
