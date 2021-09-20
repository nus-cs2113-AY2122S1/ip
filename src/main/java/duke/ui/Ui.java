package duke.ui;

import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static final String NL = System.lineSeparator();
    private final String HELP_MESSAGE = "Valid Commands: " + NL
            + "todo {description of task} (eg. todo homework)" + NL
            + "event {description of event} /at {time of event} (eg. event party at/ 9am)" + NL
            + "deadline {description of task} /by {deadline of task}  (eg. deadline assignment /by 6pm)"
            + NL
            + "list" + NL
            + "agenda" + NL
            + "done {index number of task done}  (eg. done 1)" + NL
            + "delete {index number of task you want to delete}  (eg. delete 1)" + NL
            + "bye";
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String STARTING_MESSAGE = "Hello from" + NL
            + LOGO + NL
            + "Hello! I'm Duke" + NL
            + "What can I do for you?";
    private final String ENDING_MESSAGE = "Bye. Hope to see you again soon!";
    private final Scanner SCANNER = new Scanner(System.in);
    
    public String readCommand() {
        return SCANNER.nextLine().trim();
    }
    
    public void showMessage(String message) {
        final String HORIZONTAL_LINE = "____________________________________________________________";
        System.out.println(HORIZONTAL_LINE + NL + message + NL
                + HORIZONTAL_LINE);
    }

    public void showStartingOrEndingMessage(boolean isStart) {
        if (isStart) {
            showMessage(STARTING_MESSAGE);
        } else {
            showMessage(ENDING_MESSAGE);
        }
    }

    public void acknowledgeAddCommand(Task task, int numberOfTasks) {
        String acknowledgementMessage = "Understood, "
                + NL + task.toString()
                + NL + "has been added. You now have "
                + numberOfTasks + " " + "task(s) in the list";
        showMessage(acknowledgementMessage);
    }

    public void showTasks(ArrayList<Task> tasks) {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1).append(".");
            list.append(tasks.get(i).toString());
            if (i < tasks.size() - 1) {
                list.append(NL);
            }
        }
        showMessage(list.toString());
    }
    
    public void acknowledgeDoneCommand(Task taskDone) {
        showMessage("Good Job!! I've marked this task as done:" + NL
                + taskDone.toString());
    }
    
    public void acknowledgeRemoveCommand(Task removedTask, int numberOfTasks) {
        showMessage("I have removed the task: " + NL + removedTask.toString()
                + NL + "You now have " + numberOfTasks + " tasks remaining");
    }
    
    public void showAgenda(ArrayList<Task> tasks) {
        System.out.println("Today's Agenda : ");
        showTasks(tasks);
    }
    
    public void showSearchResults(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list: ");
        showTasks(tasks);
    }
    
    public void showHelpMessage() {
        showMessage(HELP_MESSAGE);
    }
    
    public void showError(DukeException de) {
        showMessage(de.getErrorMessage());
    }
    
    public void showLoadingMessage() {
        System.out.print("Loading data... ");
    }
    
    public void showDataNotFound() {
        System.out.println("No previous data found");
    }
    
    public void showSecurityPermissionError() {
        System.out.println("WARNING - Data Directory could not be created due to insufficient permissions");
    }
    
    public void showLoadingSuccessful() {
        System.out.println("Data loaded successfully");
    }
    
    public void showSaveError() {
        showMessage("WARNING - Error in saving data to file (IOException has occurred)");
    }
    
    public void showNumberFormatError() {
        showMessage("Please enter a valid number after 'done' or 'delete'");
    }
}
