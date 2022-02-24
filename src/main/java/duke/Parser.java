package duke;

import duke.exceptions.*;
import duke.task.*;

import java.io.*;
import java.time.format.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Contains the various actions taken when a user input a command to the program.
 * Allowed commands are <b>todo, deadline, event, list, done, delete, find, bye</b>.
 */
public class Parser {

    private static final String COMMAND_EXIT_WORD = "bye";
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_DONE_WORD = "done";
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_EVENT_WORD = "event";
    private static final String COMMAND_DELETE_WORD = "delete";
    private static final String COMMAND_FIND_WORD = "find";
    private static final String[] COMMAND_WORDS_LIST = {COMMAND_TODO_WORD, COMMAND_DEADLINE_WORD, COMMAND_EVENT_WORD,
            COMMAND_LIST_WORD, COMMAND_DONE_WORD, COMMAND_EXIT_WORD, COMMAND_DELETE_WORD, COMMAND_FIND_WORD};
    private static final String PATH = "data/duke.txt";
    private static final Scanner INPUT_COMMAND = new Scanner(System.in);
    private static TaskList taskList = new TaskList();
    private static Storage storage = new Storage();

    /**
     * Method to handle various exceptions in the program.
     * @param tasksArrayList
     * @throws IOException
     */
    public static void executeProgramWithErrorHandlings(ArrayList<Task> tasksArrayList) throws IOException {
        String inputLine = INPUT_COMMAND.nextLine();
        ;
        while (!inputLine.equals(COMMAND_EXIT_WORD)) {
            try {
                executeCommand(tasksArrayList, inputLine.trim());
                storage.writeToFile(PATH, tasksArrayList);
                inputLine = INPUT_COMMAND.nextLine();
            } catch (IllegalCommand e) {
                System.out.println("  OOPS! I'm sorry, but I don't know what that means :(");
                System.out.println("  I can only execute these commands: ");
                for (int i = 1; i <= COMMAND_WORDS_LIST.length; i++) {
                    System.out.println("  " + i + ". " + COMMAND_WORDS_LIST[i - 1]);
                }
                inputLine = INPUT_COMMAND.nextLine();
            } catch (EmptyDescription e) {
                System.out.println("  OOPS! The description cannot be empty and must be separated using white space!");
                System.out.println("  Please input again with the correct format!");
                inputLine = INPUT_COMMAND.nextLine();
            } catch (IllegalDeadlineInput e) {
                System.out.println("  OOPS! Please input again using this format:");
                System.out.println("  'deadline <description> /by <due date>'");
                inputLine = INPUT_COMMAND.nextLine();
            } catch (DateTimeParseException e) {
                System.out.println("  OOPS! Please input again the date using this format:");
                System.out.println("  '2021-10-01'");
                inputLine = INPUT_COMMAND.nextLine();
            } catch (IllegalEventInput e) {
                System.out.println("  OOPS! Please input again using this format:!");
                System.out.println("  'event <description> /at <event date>'");
                inputLine = INPUT_COMMAND.nextLine();
            } catch (EmptyTaskNumber e) {
                System.out.println("  OOPS! Please specify the task number!");
                System.out.println("  Example: 'done 1' or 'delete 2'");
                inputLine = INPUT_COMMAND.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("  OOPS! Please put only integer as the task number!");
                System.out.println("  Example: 'done 1'");
                inputLine = INPUT_COMMAND.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("  OOPS! You must have typed the wrong number. Please type in again correctly!");
                inputLine = INPUT_COMMAND.nextLine();
            } catch (IOException e) {
                System.out.println("  OOPS! There was an error updating the file in the storage.");
            }
        }
    }

    /**
     * Method to select which command should be executed based on the user input.
     * @param tasksArrayList
     * @param inputLine
     * @throws IllegalCommand
     * @throws EmptyDescription
     * @throws IllegalDeadlineInput
     * @throws IllegalEventInput
     * @throws EmptyTaskNumber
     */
    public static void executeCommand(ArrayList<Task> tasksArrayList, String inputLine) throws IllegalCommand, EmptyDescription, IllegalDeadlineInput, IllegalEventInput, EmptyTaskNumber {
        String firstWord;
        if (inputLine.contains(" ")) {
            firstWord = inputLine.split(" ")[0];
        } else {
            firstWord = inputLine;
        }
        switch (firstWord) {
        case COMMAND_TODO_WORD:
            if (!inputLine.contains(" ")) {
                throw new EmptyDescription();
            }
            taskList.addToDo(tasksArrayList, inputLine);
            break;
        case COMMAND_DEADLINE_WORD:
            if (!inputLine.contains(" /by ")) {
                throw new IllegalDeadlineInput();
            }
            taskList.addDeadline(tasksArrayList, inputLine);
            break;
        case COMMAND_EVENT_WORD:
            if (!inputLine.contains(" /at ")) {
                throw new IllegalEventInput();
            }
            taskList.addEvent(tasksArrayList, inputLine);
            break;
        case COMMAND_LIST_WORD:
            taskList.printTasks(tasksArrayList);
            break;
        case COMMAND_DONE_WORD:
            if (!inputLine.contains(" ")) {
                throw new EmptyTaskNumber();
            }
            taskList.markAsDone(tasksArrayList, inputLine);
            break;
        case COMMAND_DELETE_WORD:
            if (!inputLine.contains(" ")) {
                throw new EmptyTaskNumber();
            }
            taskList.deleteTask(tasksArrayList, inputLine);
            break;
        case COMMAND_FIND_WORD:
            if (!inputLine.contains(" ")) {
                throw new EmptyDescription();
            }
            taskList.findTask(tasksArrayList, inputLine);
            break;
        default:
            throw new IllegalCommand();
        }

    }

}
