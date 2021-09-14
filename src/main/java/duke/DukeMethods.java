package duke;

import duke.exception.DukeException;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;
import duke.text.Text;

import java.util.ArrayList;

public class DukeMethods {

    public static void printIntroduction() {
        System.out.println(Text.LINE);
        System.out.print(Text.LOGO);
        System.out.println(Text.LINE);
        System.out.println(Text.HELLO_MESSAGE);
        System.out.println(Text.LINE);
    }

    public static void printList(ArrayList<Task> taskList) {
        int taskNumber = 1;
        System.out.println(Text.LINE);
        System.out.println(Text.LIST_MESSAGE);
        for (Task t : taskList) {
            System.out.println(taskNumber + "." + t);
            taskNumber++;
        }
        System.out.println(Text.LINE);
    }

    public static void printHelpList() {
        System.out.println(Text.LINE);
        System.out.println(Text.HELP_LIST);
        System.out.println(Text.LINE);
    }

    public static void markCompletedTask(String userCommand, ArrayList<Task> taskList) {
        String[] taskToMark = userCommand.split(" ");
        int taskNumberToMark;
        try {
            if (taskToMark.length <= 1) {
                throw new DukeException();
            }
            else {
                for (int i = 1; i < taskToMark.length; i++) {
                    taskNumberToMark = Integer.parseInt(taskToMark[i]);
                    taskList.get(taskNumberToMark - 1).markDone();
                    printCompletedTaskMessage(taskList.get(taskNumberToMark - 1));
                }
            }
        }
        catch (DukeException e) {
            System.out.println(Text.LINE);
            System.out.println(Text.NO_TASK_NUMBER);
            System.out.println(Text.LINE);
        }
        catch (NumberFormatException e) {
            System.out.println(Text.LINE);
            System.out.println(Text.NO_VALID_NUMBER);
            System.out.println(Text.LINE);
        }
        catch (RuntimeException e) {
            System.out.println(Text.LINE);
            System.out.println(Text.TASK_NUMBER_NOT_FOUND);
            System.out.println(Text.LINE);
        }
    }

    public static void deleteAndPrintTask(String userCommand, ArrayList<Task> taskList) {
        String[] taskToDelete = userCommand.split(" ");
        int taskNumberToDelete;
        try {
            if (taskToDelete.length <= 1) {
                throw new DukeException();
            }
            else {
                taskNumberToDelete = Integer.parseInt(taskToDelete[1]);
                printTaskDeletedMessage(taskList.get(taskNumberToDelete - 1), taskList);
                taskList.remove(taskNumberToDelete - 1);
                System.out.println("You currently have " + taskList.size() + " left in the list.");
                System.out.println(Text.LINE);
            }
        }
        catch (DukeException e) {
            System.out.println(Text.LINE);
            System.out.println(Text.NO_TASK_NUMBER);
            System.out.println(Text.LINE);
        }
        catch (NumberFormatException e) {
            System.out.println(Text.LINE);
            System.out.println(Text.NO_VALID_NUMBER);
            System.out.println(Text.LINE);
        }
        catch (RuntimeException e) {
            System.out.println(Text.LINE);
            System.out.println(Text.TASK_NUMBER_NOT_FOUND);
            System.out.println(Text.LINE);
        }
    }

    public static void printTaskDeletedMessage(Task task, ArrayList<Task> taskList) {
        System.out.println(Text.LINE);
        System.out.println(Text.TASK_DELETED + "\n  " + task);
    }

    public static void printCompletedTaskMessage(Task task) {
        System.out.println(Text.LINE);
        System.out.println(Text.TASK_MARKED + "\n  " + task);
        System.out.println(Text.LINE);
    }

    public static boolean isValid(String userCommand) {
        return userCommand.contains(Text.TODO) || userCommand.contains(Text.DEADLINE) || userCommand.contains(Text.EVENT);
    }

    public static void addTask(String userCommand, ArrayList<Task> taskList) {
        String[] splitTaskString = userCommand.split(" ", 2);
        String[] taskNameAndDueDate;
        switch (splitTaskString[0]) {
        case Text.TODO:
            taskList.add(new ToDos(splitTaskString[1]));
            break;
        case Text.DEADLINE:

            taskNameAndDueDate = splitTaskString[1].split(" /by ", 2);
            taskList.add(new Deadlines(taskNameAndDueDate[0], taskNameAndDueDate[1]));
            break;
        case Text.EVENT:
            taskNameAndDueDate = splitTaskString[1].split(" /at ", 2);
            taskList.add(new Events(taskNameAndDueDate[0], taskNameAndDueDate[1]));
            break;
        }
    }

    public static void addAndPrintTask(String userCommand, ArrayList<Task> taskList) {
        String[] taskFromCommand = userCommand.split(" ", 2);
        try {
            if (taskFromCommand.length <= 1) {
                throw new DukeException();
            }
            addTask(userCommand.toLowerCase(), taskList);
            printAddedTask(taskList.get(taskList.size() - 1), taskList);
        }
        catch (DukeException e) {
            printEmptyTaskMessage(taskFromCommand[0]);
        }
    }

    public static void printAddedTask(Task task, ArrayList<Task> taskList) {
        System.out.println(Text.LINE);
        System.out.println("Alright! Added to the list:\n" + "  " + task);
        System.out.println("You currently have " + taskList.size() + " task recorded in your list.");
        System.out.println(Text.LINE);
    }

    public static void printEmptyTaskMessage(String s) {
        System.out.println(Text.LINE);
        System.out.println("Error404!!! " + s + " task description should not be empty!");
        System.out.println(Text.LINE);
    }

    public static void printUnknownCommandMessage() {
        System.out.println(Text.LINE);
        System.out.println(Text.UNKNOWN_COMMAND);
        System.out.println(Text.LINE);
    }

    public static void printBye() {
        System.out.println(Text.LINE);
        System.out.println(Text.GOODBYE_MESSAGE);
        System.out.println(Text.LINE);
    }
}
