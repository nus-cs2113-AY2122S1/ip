package shima.parser;

import shima.Shima;
import shima.command.*;
import shima.design.Default;
import shima.design.Profile;
import shima.storage.Storage;
import shima.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public static final String EMPTY = "";
    public static final String VIEW_PERSONALITY = "view -p";
    public static final String BYE = "bye";
    public static final String EXIT = "exit";
    public static final String HELP = "help";
    public static final String LIST = "list";
    public static final String DELETE = "delete";
    public static final String TODO = "todo";
    public static final String EVENT = "event";
    public static final String DEADLINE = "deadline";
    public static final String DONE = "done";
    public static final String EMPTY_STRING_MSG = "Hmm... I can't understand empty string :(";
    public static final String INVALID_COMMAND_MSG = "Sorry, the command is invalid, I cant understand... " +
            "To seek for help, you can type the command \"help\" or \"view -h\"";

    /**
     * Reads the input command entered by the user and handles each command
     *
     * @param tasks The array to store all the tasks required
     */
    public static void readCommand(ArrayList<Task> tasks) throws IOException {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine().trim();
        String[] words = command.split(" ");
        switch (words[0].toLowerCase()) {
        case EMPTY:
            Default.showMessage(EMPTY_STRING_MSG);
            break;
        case VIEW_PERSONALITY:
            Profile.printPersonality();
            break;
        case BYE:
        case EXIT:
            Storage.updateStorage(tasks);
            ExitProgram.closeProgram();
            break;
        case HELP:
            HelpMenu.printHelpMenu();
            break;
        case LIST:
            ToDoList.printToDoList(tasks, Shima.longestTaskDescription);
            break;
        case DELETE:
            DeleteTask.deleteTasks(tasks, words);
            break;
        case TODO:
            AddTask.createToDo(tasks, command, words);
            break;
        case EVENT:
            AddTask.createEvent(tasks, command, words);
            break;
        case DEADLINE:
            AddTask.createDeadline(tasks, command, words);
            break;
        case DONE:
            DoneTask.handleTaskDone(tasks, words);
            break;
        default:
            Default.showMessage(INVALID_COMMAND_MSG);
        }
    }
}
