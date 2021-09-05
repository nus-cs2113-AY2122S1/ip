package duke;

import duke.exception.DukeException;
import duke.task.Deadlines;
import duke.task.Task;
import duke.task.ToDos;
import duke.text.Text;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String userCommand; //store user input
        Task[] taskList = new Task[100]; //store all the task from user input
        int listSize = 0;

        printIntroduction();

        Scanner in = new Scanner(System.in);
        userCommand = in.nextLine(); //user input

        while (!userCommand.equalsIgnoreCase("bye")) { //exit command is bye
            if (userCommand.equalsIgnoreCase("list")) { //prints list of task
                printList(taskList, listSize);
            }
            else if (userCommand.equalsIgnoreCase("help")) {
                printHelpList();
            }
            else if (userCommand.toLowerCase().contains("done ")) { //check user input to see if task completed
                markCompletedTask(userCommand, taskList);
            }
            else if (isValid(userCommand.toLowerCase())){
                String[] taskFromCommand = userCommand.split(" ", 2);
                try {
                    if (taskFromCommand.length <= 1) {
                        throw new DukeException();
                    }
                    addTask(userCommand.toLowerCase(), taskList, listSize);
                    printAddedTask(taskList[listSize], listSize);
                    listSize++; //updates the size of list
                }
                catch (DukeException e) {
                    printEmptyTaskMessage(taskFromCommand[0]);
                }
            }
            else {
                printUnknownCommandMessage();
            }
            userCommand = in.nextLine();
        }
        printBye();
    }

    private static void printIntroduction() {
        System.out.println(Text.LINE);
        System.out.print(Text.LOGO);
        System.out.println(Text.LINE);
        System.out.println(Text.HELLO_MESSAGE);
        System.out.println(Text.LINE);
    }

    private static void printList(Task[] taskList, int listSize) {
        System.out.println(Text.LINE);
        System.out.println(Text.LIST_MESSAGE);
        for (int i = 0; i < listSize; i++) {
            System.out.print(taskList[i].getTaskNumber());
            System.out.println("." + taskList[i]);
        }
        System.out.println(Text.LINE);
    }

    private static void printHelpList() {
        System.out.println(Text.LINE);
        System.out.println(Text.HELP_LIST);
        System.out.println(Text.LINE);
    }

    private static void markCompletedTask(String userCommand, Task[] taskList) {
        String[] taskToMark = userCommand.split(" ");
        int taskNumberToMark;
        try {
            if (taskToMark.length <= 1) {
                throw new DukeException();
            }
            else {
                for (int i = 1; i <taskToMark.length; i++) {
                    taskNumberToMark = Integer.parseInt(taskToMark[i]);
                    taskList[taskNumberToMark - 1].markDone();
                    printCompletedTaskMessage(taskList[taskNumberToMark - 1]);
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

    private static void printCompletedTaskMessage(Task task) {
        System.out.println(Text.LINE);
        System.out.println(Text.TASK_MARKED);
        System.out.println(" " + task);
        System.out.println(Text.LINE);
    }

    private static boolean isValid(String userCommand) {
        return userCommand.contains(Text.TODO) || userCommand.contains(Text.DEADLINE) || userCommand.contains(Text.EVENT);
    }

    private static void addTask(String userCommand, Task[] taskList, int listSize) {
        String[] splitTaskString = userCommand.split(" ", 2);
        String[] taskNameAndDueDate;
        switch (splitTaskString[0]) {
        case Text.TODO:
            taskList[listSize] = new ToDos(splitTaskString[1]);
            break;
        case Text.DEADLINE:
            taskNameAndDueDate = splitTaskString[1].split("/by ", 2);
            taskList[listSize] = new Deadlines(taskNameAndDueDate[0], taskNameAndDueDate[1]);
            break;
        case Text.EVENT:
            taskNameAndDueDate = splitTaskString[1].split("/at ", 2);
            taskList[listSize] = new Deadlines(taskNameAndDueDate[0], taskNameAndDueDate[1]);
            break;
        }
    }

    private static void printAddedTask(Task task, int listSize) {
        System.out.println(Text.LINE);
        System.out.println("Alright! Added to the list:");
        System.out.println(" " + task);
        task.setTaskNumber(listSize + 1); //update the task number
        System.out.print("You currently have ");
        System.out.print(listSize + 1);
        System.out.println(" task recorded in your list.");
        System.out.println(Text.LINE);
    }

    private static void printEmptyTaskMessage(String s) {
        System.out.println(Text.LINE);
        System.out.println("Error404!!! " + s + " task description should not be empty!");
        System.out.println(Text.LINE);
    }

    private static void printUnknownCommandMessage() {
        System.out.println(Text.LINE);
        System.out.println(Text.UNKNOWN_COMMAND);
        System.out.println(Text.LINE);
    }

    private static void printBye() {
        System.out.println(Text.LINE);
        System.out.println(Text.GOODBYE_MESSAGE);
        System.out.println(Text.LINE);
    }
}