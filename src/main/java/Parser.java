import exceptions.EmptyDateError;
import exceptions.MissingFieldError;
import exceptions.InvalidDescriptionError;
import exceptions.MissingDescriptionError;
import exceptions.InvalidCommandError;
import todo.Deadline;
import todo.Event;
import todo.Task;
import todo.ToDo;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;

public class Parser extends Duke {

    public Parser(String filePath) {
        super(filePath);
    }

    /**
     * Reads user input and executes method to check validity of command.
     * Error messages are displayed if commands are invalid or format of command is incorrect.
     */
    protected static void readInput() {
        String line = "";
        Scanner in = new Scanner(System.in);
        while (!line.contains("bye") || in.hasNextLine()) {
            line = in.nextLine();
            assert line != null;
            printDivider();
            String[] words = line.split(" ", 2);
            try{
                checkValidCommand(words);
            } catch (InvalidCommandError e) {
                System.out.println("Invalid command. Try again!");
                Duke.printDivider();
            } catch (MissingFieldError e) {
                System.out.println("You are missing a field!");
                Duke.printDivider();
            }

        }
    }

    /**
     * Checks if command is a valid command in the program
     * @param fullCommand input which user has entered
     * @throws InvalidCommandError command does not exist in the program
     * @throws MissingFieldError full command is not of the correct format
     */
    protected static void checkValidCommand(String[] fullCommand) throws InvalidCommandError, MissingFieldError {
        String command = fullCommand[0];
        if(!commands.contains(command)){
            throw new InvalidCommandError();
        }
        executeCommand(fullCommand, command);
    }

    /**
     * Method executes command and checks if command format is correct.
     * If command format is incorrect, this method will throw a MissingFieldError.
     *
     * @param fullCommand input which user has entered
     * @param command string containing command which we want to execute
     * @throws MissingFieldError format of command is incorrect
     */
    protected static void executeCommand(String[] fullCommand, String command) throws MissingFieldError, IndexOutOfBoundsException {
        switch (command) {
            case "done": {
                try{
                    int currentIndex = checkTaskIndex(fullCommand);
                    TaskList.setTaskDone(currentIndex);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("This task index does not exist");
                    Duke.printDivider();
                } catch (MissingFieldError e) {
                    System.out.println("Missing fields!");
                    Duke.printDivider();
                }
                break;
            }
            case "list":
                TaskList.listAllTask();
                break;
            case "bye":
                Ui.showByeScreen();
                System.exit(0);
            case "save":
                try {
                    Storage.saveTasks(filePath);
                } catch (IOException e) {
                    System.out.println("Something went wrong!");
                    Duke.printDivider();
                }
                break;
            case "delete": {
                int currentIndex = checkTaskIndex(fullCommand);
                TaskList.removeTask(currentIndex);
                break;
            }
            case "find":
                String wordToFind = extractWordToFind(fullCommand);
                findTask(wordToFind);
                break;
            default:  //is a valid task
                checkValidInput(fullCommand);
                break;
        }
    }

    /**
     * Implements the find feature. Searches for word to see if it is in the tasks.
     * If it is, it prints the task along with its index.
     * If it is not, it prints a message to notify the user.
     *
     * @param wordToFind string containing the word that we want to search for
     */
    private static void findTask(String wordToFind) {
        boolean taskExists = false;
        String currentTask;
        for(int i = 0; i < Duke.tasks.size(); i++) {
            currentTask = Duke.tasks.get(i).toString();
            if(currentTask.contains(wordToFind)) {
                if(!taskExists) {
                    System.out.println("Here are the matching tasks in your list:");
                    taskExists = true;
                }
                System.out.println((i + 1) + ". " + currentTask);
                if(i + 1 == Duke.tasks.size()) {
                    printDivider();
                }
            }
        }

        if(!taskExists) {
            System.out.println("There are no tasks containing such words!");
        }

        printDivider();
    }

    /**
     * Catches different errors when the full command is split into
     * @param fullCommand input which user has entered
     */
    protected static void checkValidInput(String[] fullCommand) {
        try{
            splitInput(fullCommand);
        } catch (MissingDescriptionError e) {
            System.out.println("Missing description");
            Duke.printDivider();
        } catch (InvalidDescriptionError e) {
            System.out.println("Description cannot be whitespace");
            Duke.printDivider();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Something went wrong! Please try again");
            Duke.printDivider();
        } catch (EmptyDateError e) {
            System.out.println("Date cannot be empty");
            Duke.printDivider();
        }
    }

    /**
     * Splits full command into type of task, description of task and date of task (if applicable).
     *
     * @param fullCommand input which user has entered
     * @throws MissingDescriptionError description for the task is missing
     * @throws InvalidDescriptionError description for task is all whitespace
     * @throws EmptyDateError  date
     */
    protected static void splitInput(String[] fullCommand)
            throws MissingDescriptionError, InvalidDescriptionError, EmptyDateError {
        String task;
        String date = null;
        String type = fullCommand[0];
        checkDescription(fullCommand);
        String[] taskDescription = splitString(fullCommand[1], "/");
        if(TaskList.checkEmptyDate(taskDescription) && !type.equals("todo")) {
            throw new EmptyDateError();
        }
        if(type.equals("todo")){ //todo command
            task = fullCommand[1];
        } else { //have action and date
            task = taskDescription[0].trim();
            date = taskDescription[1].split(" ", 2)[1];
        }
        executeTask(task, date, type);
    }

    /**
     * Method executes command to add task depending on the type of task.
     * If the type of the task does not match available types, an error message will be shown
     * @param task description of the task
     * @param date date to complete the task by
     * @param type type of task
     */
    protected static void executeTask(String task, String date,
                                      String type) {
        switch (type) {
            case "deadline":
                TaskList.addDeadline(task, date);
                break;
            case "event":
                TaskList.addEvent(task, date);
                break;
            case "todo":
                TaskList.addToDo(task);
                break;
            default:  //invalid command
                System.out.println("Invalid type, try again.");
                Duke.printDivider();
                break;
        }
    }

    /**
     * Checks if the full command is of the appropiate format.
     * Returns the index of the task which the command is to be carried out on
     *
     * @param fullCommand input which user has entered
     * @return index of the task to which command is to carry out on
     * @throws MissingFieldError format of command is incorrect
     */
    protected static int checkTaskIndex(String[] fullCommand) throws MissingFieldError, IndexOutOfBoundsException {
        if(fullCommand.length < 2) {
            throw new MissingFieldError();
        } else {
            return Integer.parseInt(fullCommand[1]);
        }
    }

    /**
     * Checks if full command contains the description of the task.
     * Throws an error message if full command does not contain the description of the task,
     * or the description of the task is whitespace.
     * @param fullCommand input which user has entered
     * @throws MissingDescriptionError description is completely missing
     * @throws InvalidDescriptionError description is whitespace
     */
    protected static void checkDescription(String[] fullCommand) throws MissingDescriptionError, InvalidDescriptionError {
        if(fullCommand.length <= 1) {
            throw new MissingDescriptionError();
        } else if(checkInvalidDescription(fullCommand[1])) {
            throw new InvalidDescriptionError(); //check that description is not just whitespaces
        }
    }

    /**
     * Returns the description of the task without any white spaces at the start and the end
     *
     * @param currentTask task which we want to extract the description from
     * @return String containing the description of currentTask
     */
    protected static String extractTask(Task currentTask) {
        String taskWithDate;
        String[] taskDescription;
        taskWithDate = currentTask.toString().split(" ", 2)[1];
        taskDescription = taskWithDate.split("\\(", 2);
        return taskDescription[0].trim();
    }

    protected static Boolean checkInvalidDescription(String word) {
        return word.trim().length() == 0;
    }

    protected static String[] splitString(String word, String s) {
        return word.split(s, 2);
    }


    private static String extractWordToFind(String[] fullCommand) throws MissingFieldError {
        if (fullCommand.length <= 1) {
            throw new MissingFieldError();
        } else {
            return fullCommand[1];
        }
    }
}
