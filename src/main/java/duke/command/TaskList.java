package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    static ArrayList<Task> taskList = null;
    static int numberOfTasks = 0;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

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
            taskList.add((new Deadline(taskDescription)));
            break;
        case Parser.EVENT:
            if (!taskDescription.contains("/at")) {
                Ui.printDividerLine();
                System.out.println("Missing /at time!");
                Ui.printDividerLine();
                return;
            }
            taskList.add((new Event(taskDescription)));
            break;
        }

        Ui.printDividerLine();
        System.out.println("I have added this task:");
        System.out.println("[" + taskList.get(numberOfTasks).getType() + "][" + taskList.get(numberOfTasks).getStatusIcon() + "] " + taskList.get(numberOfTasks).getDescription());
        numberOfTasks++;
        System.out.println("You have " + numberOfTasks + " task(s) in the list.");
        Ui.printDividerLine();
        Storage.writeToFile();
    }

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

    static void markTaskComplete(String taskDescription) {
        int taskNumber;

        try {
            taskNumber = Integer.parseInt(taskDescription);
            if (taskNumber > numberOfTasks || taskNumber <= 0) {
                System.out.println("Error! This task does not exist!");
                return;
            }
            taskNumber--;
            taskList.get(taskNumber).markAsDone();
            Ui.printDividerLine();
            System.out.println("I have marked it as completed!");
            System.out.println(taskNumber + 1 + ". [" + taskList.get(taskNumber).getType() + "][" + taskList.get(taskNumber).getStatusIcon() + "] " + taskList.get(taskNumber).getDescription());
            Ui.printDividerLine();
        } catch (NumberFormatException e) {
            System.out.println("Error! This task does not exist!");
        }
        try {
            Storage.writeToFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    static void deleteTask(String taskDescription) {
        int taskNumber;

        try {
            taskNumber = Integer.parseInt(taskDescription);
            if (taskNumber > numberOfTasks || taskNumber <= 0) {
                System.out.println("Error! This task does not exist!");
                return;
            }
            taskNumber--;
            Ui.printDividerLine();
            System.out.println("Noted. I've removed this task: ");
            System.out.println("  [" + taskList.get(taskNumber).getType() + "][" + taskList.get(taskNumber).getStatusIcon() + "] " + taskList.get(taskNumber).getDescription());
            Ui.printDividerLine();
            taskList.remove(taskNumber);
            numberOfTasks--;
        } catch (NumberFormatException e) {
            System.out.println("Error! This task does not exist!");
        }
        try {
            Storage.writeToFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }
}