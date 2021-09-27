package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Performs task list related functions
 */
public class TaskList {
    static ArrayList<Task> taskList = null;
    static int numberOfTasks = 0;
    static String type, status, description;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Handles add task functions and add todo, deadline or event.
     * For deadline and event, additional checks are performed to verify
     * correct format is followed for preposition and dates.
     *
     * @throws IOException If input information is invalid
     */
    static void addTask(String taskType, String taskDescription) throws IOException {

        if (taskDescription == null) {
            Ui.printDividerLine();
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            Ui.printDividerLine();
            return;
        }

        switch (taskType) {
        case Parser.TODO:
            taskList.add((new ToDo(taskDescription)));
            break;
        case Parser.DEADLINE:
            if (!taskDescription.contains("/by")) {
                Ui.printDividerLine();
                System.out.println("Missing /by time!");
                Ui.printDividerLine();
                return;
            }
            int byPos = taskDescription.indexOf("/by");
            try {
                LocalDateTime dateTime = Parser.parseDate(taskDescription.substring(byPos + 3).trim());
                taskList.add((new Deadline(taskDescription.substring(0, byPos), dateTime)));
            } catch (Exception a) {
                System.out.println("Invalid time format!");
                Ui.printDividerLine();
                return;
            }
            break;
        case Parser.EVENT:
            if (!taskDescription.contains("/at")) {
                Ui.printDividerLine();
                System.out.println("Missing /at time!");
                Ui.printDividerLine();
                return;
            }
            int atPos = taskDescription.indexOf("/at");
            try {
                LocalDateTime dateTime = Parser.parseDate(taskDescription.substring(atPos + 3).trim());
                taskList.add((new Event(taskDescription.substring(0, atPos), dateTime)));
            } catch (Exception a) {
                System.out.println("Invalid time format!");
                Ui.printDividerLine();
                return;
            }
            break;
        }

        Ui.printDividerLine();
        System.out.println("I have added this task:");
        type = TaskList.taskList.get(numberOfTasks).getType();
        status = TaskList.taskList.get(numberOfTasks).getStatusIcon();
        description = TaskList.taskList.get(numberOfTasks).getDescription();
        System.out.println("[" + type + "][" + status + "] " + description);
        numberOfTasks++;
        System.out.println("You have " + numberOfTasks + " task(s) in the list.");
        Ui.printDividerLine();
        Storage.writeToFile();
    }

    /**
     * Prints out the current list of tasks
     */
    static void displayList() {
        Ui.printDividerLine();
        if (numberOfTasks == 0) {
            System.out.println("to-do list is empty! add something");
            return;
        }
        System.out.println("The current to-do list is as follows:");
        int counter = 1;
        for (Task item : taskList) {
            System.out.println(counter + ". [" + item.getType() + "][" + item.getStatusIcon() + "] " + item.getDescription());
            counter++;
        }
        Ui.printDividerLine();
    }

    /**
     * Mark selected task as complete.
     */
    static void markTaskComplete(int taskNumber) {
        taskNumber--;
        taskList.get(taskNumber).markAsDone();
        Ui.printDividerLine();
        System.out.println("I have marked it as completed!");
        System.out.println(taskNumber + 1 + ". [" + taskList.get(taskNumber).getType() + "][" + taskList.get(taskNumber).getStatusIcon() + "] " + taskList.get(taskNumber).getDescription());
        Ui.printDividerLine();
        Storage.writeToFile();
    }

    /**
     * Delete selected task .
     */
    static void deleteTask(int taskNumber) {
        taskNumber--;
        taskList.remove(taskNumber);
        numberOfTasks--;
        Ui.printDividerLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  [" + taskList.get(taskNumber).getType() + "][" + taskList.get(taskNumber).getStatusIcon() + "] " + taskList.get(taskNumber).getDescription());
        Ui.printDividerLine();
        Storage.writeToFile();

    }

    /**
     * Print out tasks that occur before specified date.
     * Perform additional checks to verify time format is correct.
     */
    static void beforeDate(String dateString) {
        LocalDateTime dateTime;
        Ui.printDividerLine();
        try {
            dateTime = Parser.parseDate(dateString);
        } catch (Exception a) {
            System.out.println("Invalid time format!");
            Ui.printDividerLine();
            return;
        }
        System.out.println("The following tasks happen before " + Parser.dateStringOutput(dateTime) + ":");
        boolean isValidType;
        for (Task item : taskList) {
            isValidType = item.getType().equals("D") || item.getType().equals("E");
            if (isValidType && item.getDateTime().isBefore(dateTime)) {
                System.out.println("[" + item.getType() + "][" + item.getStatusIcon() + "] " + item.getDescription());
            }
        }
        Ui.printDividerLine();
    }

    /**
     * Print out tasks that occur after specified date.
     * Perform additional checks to verify time format is correct.
     */
    static void afterDate(String dateString) {
        LocalDateTime dateTime;
        Ui.printDividerLine();
        try {
            dateTime = Parser.parseDate(dateString);
        } catch (Exception a) {
            System.out.println("Invalid time format!");
            Ui.printDividerLine();
            return;
        }
        System.out.println("The following tasks happen after " + Parser.dateStringOutput(dateTime) + ":");
        boolean isValidType;
        for (Task item : taskList) {
            isValidType = item.getType().equals("D") || item.getType().equals("E");
            if (isValidType && item.getDateTime().isAfter(dateTime)) {
                System.out.println("[" + item.getType() + "][" + item.getStatusIcon() + "] " + item.getDescription());
            }
        }
        Ui.printDividerLine();
    }

    /**
     * Print out tasks that include the substring given
     */
    static void find(String keyword) {
        Ui.printDividerLine();
        System.out.println("Here are the matching tasks in your list:");
        for (Task item : taskList) {
            if (item.getDescription().contains(keyword)) {
                System.out.println("[" + item.getType() + "][" + item.getStatusIcon() + "] " + item.getDescription());
            }
        }
        Ui.printDividerLine();
    }


}