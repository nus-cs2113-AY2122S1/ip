package parser;
import FridayExceptions.EmptyListException;
import FridayExceptions.MissingKeyWordException;
import FridayExceptions.MissingDateException;
import FridayExceptions.EmptyTaskNameException;
import FridayExceptions.InvalidTaskIndexException;
import FridayExceptions.IncompleteCommandException;
import enums.Commands;
import enums.Errors;
import storage.UpdateData;
import ui.MessagePrinter;
import tasks.TaskList;

import java.util.Scanner;

public abstract class InputParser {
    // constant flags
    private static final String LIST = "list";
    private static final String BYE = "bye";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";
    private static final String DONE = "done";
    private static final String DEADLINE = "deadline";
    private static final String BY= "/by";
    private static final String AT = "/at";

    /**
     * Main function managing all user inputs until program is terminated
     * via the "bye" command.
     * Shift inside InputParser
     */
    public static void parseUserInput() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine();

            // change to switch case based on enum
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
                case DONE:
                    TaskList.markAsDone(userInput);
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
                MessagePrinter.outOfBoundsTaskIndex();
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
            }
        }
    }

    // isolate out task name from user input
    public static String getTaskName(String input) {
        return input.substring(input.indexOf(" ") + 1, input.indexOf("/")).trim();
    }

    // get date for deadline / event from user input
    public static String getDate(String input) {
        return input.substring(input.indexOf("/") + 3).trim();
    }

    // get index of task in tasks array to mark as done
    public static int getTaskIndex(String input) {
        return Integer.parseInt(input.substring(input.indexOf(" ") + 1)) - 1;
    }

    // command parser handling all commands; returns enum of commands
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

        return Commands.INVALID;
    }

    // check validity of Deadline command; return string of error to throw in TaskManager
    public static Errors checkDeadlineCommand(String input) {
        String[] splitString = input.split("\\s");
        // if no specifications at all i.e length of split string == 1 WORKS
        if (splitString.length <= 1) {
            return Errors.INCOMPLETE_COMMAND;
        }
        // check if it contains BY WORKS
        if (!input.contains(BY)) {
            return Errors.MISSING_BY;
        }

        // check if it contains a task name
        if (splitString.length <= 2) {
            return Errors.MISSING_TASK;
        }
        // check if there is a date after /by i.e. if last element is by WORKS
        if (input.endsWith(BY)) {
            return Errors.MISSING_DEADLINE;
        }
        return Errors.NONE;
    }

    // check validity of Event command
    public static Errors checkEventCommand(String input) {
        String[] splitString = input.split("\\s");
        // if no specifications at all i.e length of split string == 1
        if (splitString.length <= 1) {
            return Errors.INCOMPLETE_COMMAND;
        }
        // check if it contains AT
        if (!input.contains(AT)) {
            return Errors.MISSING_AT;
        }
        // check if it contains a task name
        if (splitString.length <= 3) {
            return Errors.MISSING_TASK;
        }
        // check if there is a date after /by i.e. if last element is by
        if (input.endsWith(AT)) {
            return Errors.MISSING_EVENT;
        }
        return Errors.NONE;
    }
}
