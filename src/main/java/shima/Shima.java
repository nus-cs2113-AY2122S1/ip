package shima;

import shima.command.CommandLibrary;
import shima.design.Profile;
import shima.design.HelpMenu;
import shima.design.ToDoList;
import shima.design.Default;
import shima.exception.ShimaException;
import shima.storage.Storage;
import shima.task.action.AddTask;
import shima.task.action.DeleteTasks;
import shima.task.action.TaskDone;
import shima.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Shima {
    public static int longestTaskDescription = 0; //The length of the longest task description
    private final ArrayList<Task> tasks;

    public Shima() {
        showWelcomeScreen();
        tasks = new ArrayList<>();
        initiateToDoList(tasks);
    }

    public static void main(String[] args) {
        new Shima().runProgram();
    }

    /**
     * Starts reading input and execute actions accordingly
     */
    @SuppressWarnings("InfiniteLoopStatement")
    private void runProgram() {
        System.out.println("\nLet's start input your command:");
        while (true) {
            try {
                readCommand(tasks);
            } catch (ShimaException.CommandException ex) {
                Default.showMessage("Sorry, the command is invalid, I cant understand :(");
                System.out.println("\tTo seek for help, you can type the command \"help\" or \"view -h\"");
            } catch (IOException ex) {
                Default.showMessage("Unfortunately somethings have messed up, I have received this information:");
                ex.printStackTrace();
            }
        }
    }

    /**
     * Reads the saved to-do list at the start of the program
     *
     * @param tasks The array list that stores all the tasks
     */
    private static void initiateToDoList(ArrayList<Task> tasks) {
        try {
            Storage.readFromStorage(tasks);
        } catch (IOException ex) {
            System.out.println();
            Default.showMessage("Unfortunately somethings have messed up, I have received this information:");
            ex.printStackTrace();
        } catch (ShimaException.StorageException ex) {
            Storage.handleStorageError(tasks);
        }
    }

    /**
     * Displays all the welcome screens and designs when the program starts
     */
    private static void showWelcomeScreen() {
        Default.printLogo();
        Default.printWelcomeMessage();
        Default.printVersionDescription();
    }

    /**
     * Reads the input command entered by the user and handles each command
     *
     * @param tasks The array to store all the tasks required
     */
    private static void readCommand(ArrayList<Task> tasks) throws ShimaException.CommandException, IOException {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine().trim();
        String[] words = command.split(" ");
        String time = "";
        String taskName = "";
        switch (words[0]) {
        case "":
            Default.showMessage("Hmm... I can't understand empty string :(");
            break;
        case "view -p":
            Profile.printPersonality();
            break;
        case "bye":
        case "exit":
            Default.showMessage("Bye! Hope to see you again :D");
            Storage.updateStorage(tasks);
            System.exit(0);
            break;
        case "help":
        case "view -h":
            HelpMenu.printHelpMenu();
            break;
        case "list":
        case "ls":
            ToDoList.printToDoList(tasks, longestTaskDescription);
            break;
        case "delete":
            DeleteTasks.deleteTasks(tasks, words);
            break;
        case "todo":
            if (AddTask.isCorrectToDo(words)) {
                AddTask.createToDo(tasks, command, words);
            }
            break;
        case "event":
            command = command.replaceFirst(words[0], "").trim();
            time = command.substring(command.indexOf('/') + 1).trim();
            taskName = command.split("/", 2)[0].trim();
            if (AddTask.isCorrectEvent(command, words, time, taskName)) {
                AddTask.createEvent(tasks, time, taskName);
            }
            break;
        case "deadline":
            command = command.replaceFirst(words[0], "").trim();
            time = command.substring(command.indexOf('/') + 1).trim();
            taskName = command.split("/", 2)[0].trim();
            if (AddTask.isCorrectDeadline(command, words, time, taskName)) {
                AddTask.createDeadline(tasks, time, taskName);
            }
            break;
        case "done":
            TaskDone.handleTaskDone(tasks, words);
            break;
        default:
            throw new ShimaException.CommandException();
        }
    }
}
