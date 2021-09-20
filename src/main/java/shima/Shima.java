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

    public Shima(){
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
        case "event":
        case "deadline":
            AddTask.addTask(tasks, command, words);
            break;
        case "done":
            TaskDone.handleTaskDone(tasks, words);
            break;
        default:
            throw new ShimaException.CommandException();
        }
        /*
        if (CommandLibrary.isCommandEmpty(command)) {
            System.out.println("\t(Empty) <- will not save to the list");
        } else if (CommandLibrary.isCommandViewPersonality(command)) {
            Profile.printPersonality();
        } else if (CommandLibrary.isCommandExit(command)) {
            Default.showMessage("Bye! Hope to see you again :D");
            Storage.updateStorage(tasks);
            System.exit(0);
        } else if (CommandLibrary.isCommandHelp(command)) {
            HelpMenu.printHelpMenu();
        } else if (CommandLibrary.isCommandDelete(command)) {
            DeleteTasks.deleteTasks(tasks, words);
        } else {
            if (CommandLibrary.isCommandList(command)) {
                ToDoList.printToDoList(tasks, longestTaskDescription);
            } else if (CommandLibrary.isCommandDone(words[0])) {
                TaskDone.handleTaskDone(tasks, words);
            } else if (CommandLibrary.isCommandAddTask(words[0])) {
                AddTask.addTask(tasks, command, words);
            } else {
                throw new ShimaException.CommandException();
            }
        }
    }
}
