package duke;

import duke.task.*;

import java.time.*;
import java.util.*;

public class TaskList {
    private static Ui ui = new Ui();

    public static void deleteTask(ArrayList<Task> tasksArrayList, String inputLine) {
        int taskIndex = Integer.parseInt(inputLine.split("delete ")[1])-1;
        Task taskToDelete = tasksArrayList.get(taskIndex);
        System.out.println("  Noted. I've removed this task:");
        System.out.println("  " + tasksArrayList.get(taskIndex));
        tasksArrayList.remove(taskToDelete);
        System.out.printf("  Now you have %d tasks in the list. Anything else?\n", tasksArrayList.size());
    }

    public static void markAsDone(ArrayList<Task> tasksArrayList, String inputLine) {
        int taskCompletedIndex = Integer.parseInt(inputLine.split("done ")[1]) - 1;
        tasksArrayList.get(taskCompletedIndex).setDone(true);
        System.out.print("  Congratulations! You have completed the task:");
        System.out.println(" " + tasksArrayList.get(taskCompletedIndex));
    }

    public static void addEvent(ArrayList<Task> tasksArrayList, String inputLine) {
        String eventDescription = inputLine.split("event ")[1].split(" /at ")[0];
        LocalDate eventDate = LocalDate.parse(inputLine.split(" /at ")[1]);
        tasksArrayList.add(new Event(eventDescription, eventDate));
        ui.addedTaskMessage(tasksArrayList);
    }

    public static void addDeadline(ArrayList<Task> tasksArrayList, String inputLine) {
        String deadlineDescription = inputLine.split("deadline ")[1].split(" /by ")[0];
        LocalDate deadlineDate = LocalDate.parse(inputLine.split(" /by ")[1]);
        tasksArrayList.add(new Deadline(deadlineDescription, deadlineDate));
        ui.addedTaskMessage(tasksArrayList);
    }

    public static void addToDo(ArrayList<Task> tasksArrayList, String inputLine) {
        String toDoDescription = inputLine.split("todo ")[1];
        tasksArrayList.add(new ToDo(toDoDescription));
        ui.addedTaskMessage(tasksArrayList);
    }

    public static void printTasks(ArrayList<Task> tasksArrayList) {
        if (tasksArrayList.size() == 0) {
            System.out.println("  You haven't added any task yet.");
        } else {
            for (int i = 1; i < tasksArrayList.size()+1; i++) {
                System.out.println("  " + i + "." + tasksArrayList.get(i-1));
            }
        }
    }
}
