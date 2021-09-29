package ui;


import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.ClearCommand;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.ListCommand;
import commands.MarkAsDoneCommand;
import commands.CommandResult;

import task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static common.Messages.MESSAGE_LOADING_ERROR;

public class Ui {
    private static final String LOGO = " \n____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Why are you here again. What do you want";

    private static final String INDENT = "\t";
    private static final String NEW_LINE = "\n\t";
    private static final String DIVIDER = "_______________________________";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a");

    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /**
     * User guide for all the commands.
     */
    public static final String USER_GUIDE = "----------------USER GUIDE----------------\n"
            + "\nAll parameters are compulsory, unless otherwise stated. "
            +"Else, command entered is invalid.\n"
            + "\n" + AddTodoCommand.MESSAGE_USAGE
            + "\n" + AddEventCommand.MESSAGE_USAGE
            + "\n" + AddDeadlineCommand.MESSAGE_USAGE
            + "\n" + ClearCommand.MESSAGE_USAGE
            + "\n" + DeleteCommand.MESSAGE_USAGE
            + "\n" + ExitCommand.MESSAGE_USAGE
            + "\n" + FindCommand.MESSAGE_USAGE
            + "\n" + HelpCommand.MESSAGE_USAGE
            + "\n" + ListCommand.MESSAGE_USAGE
            + "\n" + MarkAsDoneCommand.MESSAGE_USAGE;


    private final Scanner in;
    private final PrintStream out;

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Reads in raw user input.
     * @return user input string
     */
    public String readCommand() {
        String input = in.nextLine();
        while (input.trim().isEmpty()) {
            input = in.nextLine();
        }
        return input;
    }

    /**
     * Shows relevant tasks in an indexed list as well as a message.
     * @param result command result
     */
    public void showResultToUser(CommandResult result) {
        final ArrayList<Task> relevantTasks = result.getRelevantTasks();
        if (relevantTasks != null) {
            showToUser(getIndexedListViewOfTasks(relevantTasks));
        }
        showToUser(result.feedbackToUser);
    }

    private String[] getIndexedListViewOfTasks(ArrayList<Task> relevantTasks) {
        final ArrayList<String> taskStringsList = new ArrayList<>();
        int displayIndex = DISPLAYED_INDEX_OFFSET;
        for (Task task : relevantTasks) {
            taskStringsList.add(displayIndex + ". " + task.toString() + "\n");
            displayIndex++;
        }
        return taskStringsList.toArray(new String[0]);
    }

    private void showToUser(String... message) {
        for (String m : message) {
            out.println(INDENT + m.replace("\n", NEW_LINE));
        }
    }

    /**
     * Shows welcome page.
     */
    public void showWelcome() {
        showToUser(LOGO,GREETING,DIVIDER);
    }

    /**
     * Shows a line.
     */
    public void showLine() {
        out.println(DIVIDER);
    }

    /**
     * Shows a loading error message.
     */
    public void showLoadingError() {
        showError(MESSAGE_LOADING_ERROR);
    }

    /**
     * Shows error message.
     * @param message error message
     */
    public void showError(String message) {
        showToUser(message);
    }
}
