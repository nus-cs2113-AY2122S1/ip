package duke;

import duke.design.Default;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.AddTask;
import duke.task.DeleteTasks;
import duke.task.TaskDone;
import duke.command.Command;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;

public class Duke {
    public static Scanner in = new Scanner(System.in);
    public static int longestTaskDescription = 0; //The length of the longest task description

    @SuppressWarnings("InfiniteLoopStatement") //Disables the warning for infinite loop
    public static void main(String[] args) {
        //Prints all the welcome screens
        Default.printLogo();
        Default.printWelcomeMessage();
        Default.printVersionDescription();
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Storage.readFromStorage(tasks);
        } catch (IOException ex) {
            Default.showMessage("Unfortunately somethings have messed up, I have received this information:");
            ex.printStackTrace();
        } catch (DukeException.StorageException ex) {
            Storage.handleStorageError(tasks);
        }
        System.out.println("\nLet's start input your command:");
        while (true) {
            try {
                readCommand(tasks);
            } catch (DukeException.CommandException ex) {
                Default.showMessage("Sorry, the command is invalid, I cant understand :(");
                System.out.println("\tTo seek for help, you can type the command \"help\" or \"view -h\"");
            } catch (IOException ex) {
                Default.showMessage("Unfortunately somethings have messed up, I have received this information:");
                ex.printStackTrace();
            }
        }
    }

    /**
     * Reads the input command entered by the user and handles each command
     *
     * @param tasks The array to store all the tasks required
     */
    private static void readCommand(ArrayList<Task> tasks) throws DukeException.CommandException, IOException {
        String command = in.nextLine().trim();
        String[] words = command.split(" ");
        if (Command.isCommandEmpty(command)) {
            System.out.println("\t(Empty) <- will not save to the list");
        } else if (Command.isCommandViewPersonality(command)) {
            Default.printPersonality();
        } else if (Command.isCommandExit(command)) {
            Default.showMessage("Bye! Hope to see you again :D");
            Storage.updateStorage(tasks);
            System.exit(0);
        } else if (Command.isCommandHelp(command)) {
            Default.printHelpMenu();
        } else if (Command.isCommandDelete(command)) {
            DeleteTasks.deleteTasks(tasks, words);
        } else {
            if (Command.isCommandList(command)) {
                Default.printToDoList(tasks, longestTaskDescription);
            } else if (Command.isCommandDone(words[0])) {
                TaskDone.handleTaskDone(tasks, words);
            } else if (Command.isCommandAddTask(words[0])) {
                AddTask.addTask(tasks, command, words);
            } else {
                throw new DukeException.CommandException();
            }
        }
    }
}

