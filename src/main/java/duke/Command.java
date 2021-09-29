package duke;

import duke.data.TaskList;
import duke.exception.DukeInvalidInputException;
import duke.exception.DukeParameterException;
import duke.exception.DukeTaskNotFoundException;
import duke.exception.DukeTimeFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * This class contains static methods that handle all the various commands Duke is capable of executing
 */
public class Command {
    /**
     * This function prints the list of tasks present. If empty, shows a prompt saying no tasks to list
     */
    public static void executeList() {
        ArrayList<Task> taskList = TaskList.getTaskList();
        if (taskList.size() == 0) {
            Ui.printEmptyListMessage();
        } else {
            Ui.printDivider();
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + 1 + "." + taskList.get(i));
            }
            Ui.printDivider();
        }
    }



    /**
     * Adds a given task of either type todo, deadline or event to the task list. If the input is missing parameters shows an error message
     * @param arguments an arrayList containing the user input string broken into its components
     * @throws DukeParameterException if any parameters are missing in the command
     * @throws DukeTimeFormatException if the time given when adding a deadline is not of the correct format
     */
    public static void executeAdd(ArrayList<String> arguments) throws DukeParameterException, DukeTimeFormatException {
        ArrayList<Task> taskList = TaskList.getTaskList();
        try {
            switch (arguments.get(0)) {
            case "todo":
                taskList.add(new ToDo(arguments.get(1)));
                break;
            case "deadline":
                taskList.add(new Deadline(arguments.get(1), arguments.get(2)));
                break;
            case "event":
                taskList.add(new Event(arguments.get(1), arguments.get(2)));
                break;
            default:
                break;
            }
            Ui.printAddTaskMessage();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeParameterException();
        } catch (DateTimeParseException ex) {
            throw new DukeTimeFormatException();
        }
    }

    /**
     * Gets text input from file, parses it into appropriate task and adds to the task list
     * @param inputFromFile the text input from the file
     */
    public static void executeLoad(String inputFromFile) {
        String[] arguments = inputFromFile.split("\\|");
        ArrayList<Task> taskList = TaskList.getTaskList();
        switch (arguments[0]) {
        case "T":
            if (arguments[1].equals("false")) {
                taskList.add(new ToDo(arguments[2]));
            } else {
                taskList.add(new ToDo(arguments[2], true));
            }
            break;
        case "D":
            if (arguments[1].equals("false")) {
                taskList.add(new Deadline(arguments[2], arguments[3]));
            } else {
                taskList.add(new Deadline(arguments[2], arguments[3], true));
            }
            break;
        case "E":
            if (arguments[1].equals("false")) {
                taskList.add(new Event(arguments[2], arguments[3]));
            } else {
                taskList.add(new Event(arguments[2], arguments[3], true));
            }
            break;
        default:
        }

    }

    /**
     * Sets a given task as done based on the task index in the list.
     * If the index doesn't exist or invalid inputs are given shows error message
     * @param index a string that is the index of the task in the task list that is to be marked as done
     * @throws DukeInvalidInputException if given input string cannot be parsed into an integer
     * @throws DukeTaskNotFoundException if given index doesn't exist in task list
     */
    public static void executeDone(String index) throws DukeInvalidInputException, DukeTaskNotFoundException {
        try {
            ArrayList<Task> taskList = TaskList.getTaskList();
            int taskIndex = Integer.parseInt(index) - 1;
            taskList.get(taskIndex).setDone();
            Ui.printDivider();
            System.out.println("Nice I've marked this task as done:");
            System.out.println(taskList.get(taskIndex));
            Ui.printDivider();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeTaskNotFoundException();
        } catch (NumberFormatException e) {
            throw new DukeInvalidInputException();
        }
    }

    /**
     * Deletes a given task as done based on the task index in the list.
     * If the index doesn't exist or invalid inputs are given shows error message
     * @param index a string that is the index of the task in the task list which is to be deleted
     * @throws DukeInvalidInputException if given input string cannot be parsed into an integer
     * @throws DukeTaskNotFoundException if given index doesn't exist in task list
     */
    public static void executeDelete(String index) throws DukeInvalidInputException, DukeTaskNotFoundException {
        try {
            ArrayList<Task> taskList = TaskList.getTaskList();
            int taskIndex = Integer.parseInt(index) - 1;
            taskList.get(taskIndex);
            Ui.printDivider();
            System.out.println("Noted. I've removed this task: ");
            System.out.println(taskList.get(taskIndex));
            taskList.remove(taskIndex);
            System.out.println("You now have " + taskList.size() + " items in the list.");
            Ui.printDivider();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeTaskNotFoundException();
        } catch (NumberFormatException e) {
            throw new DukeInvalidInputException();
        }
    }

    /**
     * Finds which tasks in the task list contain the given input string and displays to user.
     * If no tasks are found shows a prompt saying list of tasks found is empty
     * @param patternToSearch a string that is the user input to search for
     * @throws DukeParameterException if input is missing the required search terms
     */
    public static void executeFind(String patternToSearch) throws DukeParameterException {
        ArrayList<Task> taskList = TaskList.getTaskList();
        ArrayList<Task> foundTasks = new ArrayList<>();
        try {
            for (Task task : taskList) {
                if (task.getDataForFind().toLowerCase().contains(patternToSearch.toLowerCase(Locale.ROOT))) {
                    foundTasks.add(task);
                }
            }
            if(foundTasks.isEmpty()) {
                Ui.printEmptyListMessage();
            } else {
                Ui.printDivider();
                for (int i = 0; i < foundTasks.size(); i++) {
                    System.out.println(i + 1 + "." + foundTasks.get(i));
                }
                Ui.printDivider();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeParameterException();
        }
    }

}
