package ui;

import commands.CommandResult;
import task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import static common.Messages.GREETING;

public class Ui {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    public static final String INDENT = "\t\t";
    public static final String NEW_LINE = "\n\t";
    public static final String INDENTED_NEW_LINE = "\n\t\t";
    public static final String DIVIDER = "_______________________________";

    private final Scanner in;
    private final PrintStream out;

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public Ui() {
        this(System.in, System.out);
    }

    public String readCommand() {
        String input = in.nextLine();
        while (input.trim().isEmpty()) {
            input = in.nextLine();
        }
        return input;
    }


    public void showLoadingError() {
    }

    public void showError(String errorMessage) {
    }

    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(INDENT + m.replace("\n", NEW_LINE));
        }
    }

    public void showResultToUser(CommandResult result) {
        final ArrayList<Task> relevantTasks = result.getRelevantTasks();
        if (relevantTasks != null) {
            showToUser(getIndexedListViewOfTasks(relevantTasks));
        }
        showToUser(result.feedbackToUser, DIVIDER);
    }

    public static ArrayList<Task> filterTasksByString(ArrayList<Task> taskList, String filterString) {
        return (ArrayList<Task>) taskList.stream()
                .filter((t) -> t.getDescription().contains(filterString))
                .collect(Collectors.toList());
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

    public void showWelcome() {
        out.println(INDENT + LOGO.replace("\n",INDENTED_NEW_LINE));
        out.println(INDENT + GREETING);
    }

    public void showLine() {
        out.println(DIVIDER);
    }
}
